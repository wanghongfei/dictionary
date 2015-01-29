package cn.fh.dictionary.source;

public abstract class AbstractRestfulBasedSource implements Source {
	/**
	 * 可发起GET请求的地址
	 */
	private String urlString;
	private String word;
	
	protected AbstractRestfulBasedSource(String urlString) {
		this.urlString = urlString;
	}

	@Override
	public String getUrl() {
		return urlString + "/" + word;
	}

	@Override
	public String getQueryString() {
		return null;
	}

	@Override
	public void setWord(String word) {
		this.word = word;
	}
}
