package cn.fh.dictionary.source;

import java.util.ArrayList;
import java.util.List;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import cn.fh.dictionary.word.Explaination;
import cn.fh.dictionary.word.Sentence;

public class YoudaoParser implements Parser {
	private String html;
	
	public YoudaoParser() {
		
	}

	public YoudaoParser(String html) {
		this.html = html;
	}

	@Override
	public void setHtml(String html) {
		this.html = html;
	}

	@Override
	public List<Explaination> getExplainationList() {
		return parse();
	}
	
	protected List<Explaination> parse() {
		Document doc = Jsoup.parse(this.html);

		//得到包含释意html代码的最顶层结点
		Elements elems = doc.select("#collinsResult .ol li");

		List<Explaination> expList = new ArrayList<>();
		for (Element tag : elems) {
			makeExplain(tag);
		}
		
		return expList;
	}
	
	private Explaination makeExplain(Element tag) {
		Explaination exp = new Explaination();

		// 解析例句
		List<Sentence> senList = new ArrayList<>();
		Elements exampleListTags = tag.select(".exampleLists");
		for (Element exampleTag : exampleListTags) {
			String english = exampleTag.select(".examples").get(0).text();
			String chinese = exampleTag.select(".examples").get(1).text();
			
			senList.add(new Sentence(english, chinese));
		}
		exp.setSentenceList(senList);
		
		// 解析词性
		Element meanningElem = tag.select(".collinsMajorTrans p").first();
		String attr = meanningElem.child(0).text();
		exp.setAttribute(attr);
		
		// 解析释意
		// 过虑掉无用标签
		meanningElem.children().removeIf( (elem) -> {
			return elem.tagName().equals("b") || elem.className().equals("additional");
		});
		exp.setEngExplaination(meanningElem.text());

		return exp;
	}

}
