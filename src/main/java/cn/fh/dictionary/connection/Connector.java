package cn.fh.dictionary.connection;

import java.util.List;

import cn.fh.dictionary.source.Parser;
import cn.fh.dictionary.source.Source;
import cn.fh.dictionary.word.Explaination;

public interface Connector {
	void setSource(Source source);
	void setParser(Parser parser);
	List<Explaination> fetchResult();
}
