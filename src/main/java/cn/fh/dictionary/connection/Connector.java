package cn.fh.dictionary.connection;

import java.util.List;

import cn.fh.dictionary.source.Source;
import cn.fh.dictionary.word.Explaination;

public interface Connector {
	void setSource(Source source);
	List<Explaination> fetchResult();
}
