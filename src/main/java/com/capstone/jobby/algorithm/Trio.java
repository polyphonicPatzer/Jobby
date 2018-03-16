package com.capstone.jobby.algorithm;

public class Trio {

	public final Integer questionID;
	public final Integer questionAnswer;
	public final Integer questionWeight;
	
	public Trio(Integer question, Integer answer, Integer weight) {
		questionID = question;
		questionAnswer = answer;
		questionWeight = weight;
	}
	
	public Integer getQuestionID() {
		return questionID;
	}
	
	public Integer getQuestionAnswer() {
		return questionAnswer;
	}
	
	public Integer getQuestionWeight() {
		return questionWeight;
	}
	
}
