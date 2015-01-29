package cn.fh.dictionary.word;

import java.util.List;

public class Explaination {
	private String attribute;
	private String engExplaination;
	private String ChinExplaination;
	private List<Sentence> sentenceList;
	
	public Explaination() {
	
	}

	public Explaination(String attribute, String engExplaination,
			String chinExplaination, List<Sentence> sentenceList) {
		this.attribute = attribute;
		this.engExplaination = engExplaination;
		ChinExplaination = chinExplaination;
		this.sentenceList = sentenceList;
	}
	public String getAttribute() {
		return attribute;
	}
	public void setAttribute(String attribute) {
		this.attribute = attribute;
	}
	public String getEngExplaination() {
		return engExplaination;
	}
	public void setEngExplaination(String engExplaination) {
		this.engExplaination = engExplaination;
	}
	public String getChinExplaination() {
		return ChinExplaination;
	}
	public void setChinExplaination(String chinExplaination) {
		ChinExplaination = chinExplaination;
	}
	public List<Sentence> getSentenceList() {
		return sentenceList;
	}
	public void setSentenceList(List<Sentence> sentenceList) {
		this.sentenceList = sentenceList;
	}
}
