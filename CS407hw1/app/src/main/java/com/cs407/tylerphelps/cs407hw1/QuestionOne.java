package com.cs407.tylerphelps.cs407hw1;

public class QuestionOne{

	private String userAnswer;
	
	public QuestionOne() {
		this.userAnswer = ""; // no answer at first
	}

	public String getUserAnswer() {
		return userAnswer;
	}

	public void setUserAnswer(String userAnswer) {
		if (userAnswer == null) {
			this.userAnswer = "";
		}
		else {
			this.userAnswer = userAnswer;
		}
	}
	
	public boolean gradeQuestion() {
		if(this.userAnswer.toLowerCase().equals("elephant")){
			return true;
		}
		return false;
	}
}
