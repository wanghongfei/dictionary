package cn.fh.dictionary.connection;

import java.util.List;

import cn.fh.dictionary.source.Source;
import cn.fh.dictionary.word.Explaination;

public class YoudaoConnector implements Connector {
	private Source source;
	
	public YoudaoConnector(Source s) {
		this.source = s;
	}

	@Override
	public List<Explaination> fetchResult() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void setSource(Source source) {
		this.source = source;
	}

}
