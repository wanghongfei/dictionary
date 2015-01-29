package cn.fh.dictionary.source;

import java.util.ArrayList;
import java.util.List;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import cn.fh.dictionary.word.Explaination;

public class KingsoftParser implements Parser {
	private String html;

	@Override
	public void setHtml(String html) {
		this.html = html;
	}

	@Override
	public List<Explaination> getExplainationList() {
		return parse();
	}
	
	private List<Explaination> parse() {
		Document doc = Jsoup.parse(this.html);
		Elements tags = doc.select(".group_pos").first().select("p");

		List<Explaination> expList = new ArrayList<>();
		for (Element meaningTag : tags) {
			String attr = meaningTag.select(".fl").text();

			Elements meaningTags = meaningTag.select(".label_list");
			StringBuilder sb = new StringBuilder();
			for (Element meanTag : meaningTags) {
				sb.append(meanTag.text());
			}
			expList.add(new Explaination(attr, "", sb.toString(), null));
			
		}

		
		return expList;
	}

}
