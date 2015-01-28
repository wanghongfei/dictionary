package cn.fh.dictionary.source;

import java.util.AbstractMap.SimpleEntry;
import java.util.ArrayList;
import java.util.List;
import java.util.Map.Entry;

public class YoudaoSource implements Source {
	
	private String url = "http://dict.youdao.com/search";
	private List<Entry<String, String>> queryStringList;
	
	public YoudaoSource() {
		queryStringList = new ArrayList<>();
	}

	@Override
	public String getUrl() {
		return url;
	}

	@Override
	public void addQueryString(String key, String value) {
		this.queryStringList.add(new SimpleEntry<String, String>(key, value));
	}

	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder(url);
		sb.append(toQueryString());
		
		return sb.toString();
	}
	
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
