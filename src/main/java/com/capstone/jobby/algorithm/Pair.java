package com.capstone.jobby.algorithm;

public class Pair {
	
	public final Integer skillID;
	public final Integer skillAnswer;
	
	public Pair(Integer id, Integer answer) {
		skillID = id;
		skillAnswer = answer;
	}
	
	public Integer getQuestionID() {
		return skillID;
	}
	
	public Integer getAnswer() {
		return skillAnswer;
	}
	
}
