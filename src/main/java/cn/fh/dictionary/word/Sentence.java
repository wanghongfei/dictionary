package cn.fh.dictionary.word;

public class Sentence {
	private String english;
	private String chinese;
	
	

	public Sentence(String english, String chinese) {
		this.english = english;
		this.chinese = chinese;
	}
	public String getEnglish() {
		return english;
	}
	public void setEnglish(String english) {
		this.english = english;
	}
	public String getChinese() {
		return chinese;
	}
	public void setChinese(String chinese) {
		this.chinese = chinese;
	}
}
