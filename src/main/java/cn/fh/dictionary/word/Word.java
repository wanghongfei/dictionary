package cn.fh.dictionary.word;

import java.util.List;

public class Word {
	private String word;
	private List<Explaination> explainList;
	
	public Word() {
		
	}

	public Word(String word, List<Explaination> explainList) {
		this.word = word;
		this.explainList = explainList;
	}
	
	
	
	public String getWord() {
		return word;
	}
	public void setWord(String word) {
		this.word = word;
	}
	public List<Explaination> getExplainList() {
		return explainList;
	}
	public void setExplainList(List<Explaination> explainList) {
		this.explainList = explainList;
	}
	
	
}
