package cn.fh.dictionary.source;

/**
 * 金山在线查单词数据源
 * @author whf
 *
 */
public class KingsoftSource extends AbstractRestfulBasedSource {
	public KingsoftSource() {
		super("http://www.iciba.com");
	}
}
