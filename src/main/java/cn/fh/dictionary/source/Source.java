package cn.fh.dictionary.source;

import java.net.URL;

public interface Source {
	URL getUrl();
	void addQueryString(String key, String value);
}
