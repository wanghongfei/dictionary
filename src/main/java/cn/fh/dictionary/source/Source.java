package cn.fh.dictionary.source;

import java.net.URL;

/**
 * 数据来源接口
 * @author whf
 *
 */
public interface Source {
	URL getUrl();
	String getQueryString();
	void addQueryString(String key, String value);
}
