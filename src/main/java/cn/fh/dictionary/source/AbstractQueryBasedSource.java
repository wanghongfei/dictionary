package cn.fh.dictionary.source;

import java.util.AbstractMap.SimpleEntry;
import java.util.ArrayList;
import java.util.List;
import java.util.Map.Entry;

/**
 * 基于query string的单词查询网站从此类承继
 * @author whf
 *
 */
public abstract class AbstractQueryBasedSource implements Source {

	/**
	 * 可发起GET请求的地址
	 */
	private String urlString;
	/**
	 * 查询参数
	 */
	private List<Entry<String, String>> queryStringList;
	private String WORD_KEY = "q";
	
	protected AbstractQueryBasedSource(String urlString, String wordKey) {
		this.queryStringList = new ArrayList<>();
		this.urlString = urlString;
		this.WORD_KEY = wordKey;
	}

	
	@Override
	public String getUrl() {
		return this.urlString + getQueryString();
	}
	
	@Override
	public void setWord(String word) {
		// 先删除之前的word
		this.queryStringList.removeIf((entry) -> {
			return entry.getKey().equals(WORD_KEY);
		});
		
		// 添加新word
		this.queryStringList.add(new SimpleEntry<String, String>(WORD_KEY, word));
	}

	protected void addQueryString(String key, String value) {
		this.queryStringList.add(new SimpleEntry<String, String>(key, value));
	}

	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder(urlString);
		sb.append(getQueryString());
		
		return sb.toString();
	}
	
	/**
	 * 构造查询字符串.如: "?name=Neo&age=18"
	 * @return
	 */
	@Override
	public String getQueryString() {
		StringBuilder sb = new StringBuilder("?");

		int len = this.queryStringList.size();
		for (int ix = 0 ; ix < len ; ++ix) {
			String key = this.queryStringList.get(ix).getKey();
			String val = this.queryStringList.get(ix).getValue();
			
			sb.append(key).append("=").append(val);
			if (ix != len - 1) {
				sb.append("&");
			}
		}
		
		return sb.toString();
	}
}
