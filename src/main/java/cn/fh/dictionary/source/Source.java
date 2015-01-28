package cn.fh.dictionary.source;

public interface Source {
	String getUrl();
	void addQueryString(String key, String value);
}
