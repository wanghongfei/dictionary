package cn.fh.dictionary.source;

import java.util.List;

import cn.fh.dictionary.word.Explaination;

public interface Parser {
	void setHtml(String html);
	List<Explaination> getExplainationList();
}
