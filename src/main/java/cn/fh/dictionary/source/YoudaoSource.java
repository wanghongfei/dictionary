package cn.fh.dictionary.source;


/**
 * 有道词典数据源
 * @author whf
 *
 */
public class YoudaoSource extends AbstractQueryBasedSource {
	public YoudaoSource() {
		super("http://dict.youdao.com/search", "q");

		addQueryString("keyfrom", "dict.top");
		addQueryString("le", "eng");
	}
}
