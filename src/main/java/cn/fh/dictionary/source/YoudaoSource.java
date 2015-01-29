package cn.fh.dictionary.source;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.AbstractMap.SimpleEntry;
import java.util.ArrayList;
import java.util.List;
import java.util.Map.Entry;

/**
 * 有道词典数据源
 * @author whf
 *
 */
public class YoudaoSource implements Source {
	
	/**
	 * 可发起GET请求的地址
	 */
	private String urlString = "http://dict.youdao.com/search";
	/**
	 * 查询参数
	 */
	private List<Entry<String, String>> queryStringList;
	private final String WORD_KEY = "q";
	
	public YoudaoSource() {
		queryStringList = new ArrayList<>();
		addQueryString("keyfrom", "dict.top");
		addQueryString("le", "eng");
		

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
