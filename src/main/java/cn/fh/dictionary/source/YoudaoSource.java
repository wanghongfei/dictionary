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
	private URL url;
	/**
	 * 查询参数
	 */
	private List<Entry<String, String>> queryStringList;
	
	public YoudaoSource() {
		queryStringList = new ArrayList<>();
		try {
			url = new URL(urlString);
		} catch (MalformedURLException e) {
			System.out.println("invalid url:" + urlString);
			System.exit(0);
		}
	}

	@Override
	public URL getUrl() {
		return url;
	}

	@Override
	public void addQueryString(String key, String value) {
		this.queryStringList.add(new SimpleEntry<String, String>(key, value));
	}

	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder(urlString);
		sb.append(toQueryString());
		
		return sb.toString();
	}
	
	/**
	 * 构造查询字符串.如: "?name=Neo&age=18"
	 * @return
	 */
	private String toQueryString() {
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
