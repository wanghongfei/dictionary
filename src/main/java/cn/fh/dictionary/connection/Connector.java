package cn.fh.dictionary.connection;

import cn.fh.dictionary.source.Parser;
import cn.fh.dictionary.source.Source;
import cn.fh.dictionary.word.Word;

public interface Connector {
	void setSource(Source source);
	void setParser(Parser parser);
	void setWord(String word);
	Word fetchResult();
}
