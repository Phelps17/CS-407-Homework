package com.cs407.tylerphelps.cs407hw1;

public class QuestionTwo {

	private int userAnswer;
	
	public QuestionTwo() {
		this.userAnswer = -1;
	}

	public int getUserAnswer() {
		return userAnswer;
	}

	public void setUserAnswer(int userAnswer) {
		this.userAnswer = userAnswer;
	}
	
	public boolean gradeQuestion() {
		if(this.userAnswer == 2) {
			return true;
		}

		return false;
	}
}
