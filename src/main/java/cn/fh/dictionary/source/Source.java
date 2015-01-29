package cn.fh.dictionary.source;

import java.net.URL;

/**
 * 数据来源接口
 * @author whf
 *
 */
public interface Source {
	String getUrl();
	String getQueryString();
	void setWord(String word);
	//void addQueryString(String key, String value);
}
