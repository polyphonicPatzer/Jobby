package com.capstone.jobby.algorithm;

import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
public class WeightedChoiceAlgorithm {

	static ArrayList<Trio> companyIdealMatchArray = new ArrayList<Trio>();
	static ArrayList<Pair> candidateScoreArray = new ArrayList<Pair>();

	//ALGORITHM FORMULA: 100 * ( 1 - ( Candidate Score   /   Worst Potential Total Score ) )
	//Candidate Score = The difference between the Companies score and what the candidate 
	//Worst Potential Total Score = The difference between the company answer given and the least appealing answer multiplied by the weighting (scaling factor) for every question. (look at Ex1, and Ex2 for what the least appealing answer criteria is)
	
	//Ex1: The company scores/answers a strongly agree (5). The least appealing answer given would be a strongly disagree (1). (since |5-1| = 4)
	//Ex2: The company scores/answers a disagree (2). The least appealing answer given would be a strongly agree (5). (since |2-5| = 3)
	//The Worst Potential Score for each question is summed to get The Worst Potential Total Score
	//So this algorithm is giving us a number out of 100 (a percentage) which tells us how closely the candidates answer's were to the companies answer's.

	
	//COMPUTES THE WORST POTENTIAL SCORE TO USE IT AS A BENCHMARK

	//Implementation:
	// Use WeightedChoiceAlgorithm, takes 2 arrays of company/
	//1. Make 10 jobs
	//2. Delete all candidates
	//3. Scores should be calculated on account/job creation
	
	public static int getCompanyBenchMarkScore(ArrayList<Trio> QuestionAnswerWeight) {
		int maxCompanyScore = 0;
		int maxToAdd = 0;
		for(Trio question : QuestionAnswerWeight) {
			int questionAnswer = question.getQuestionAnswer();
			int questionWeight = question.getQuestionWeight();
			int currentPotentialMaxScore = (5 - questionAnswer) * questionWeight;
			int currentPotentialMaxScore2 = Math.abs(1 - questionAnswer) * questionWeight;
			if(currentPotentialMaxScore > currentPotentialMaxScore2) {
				maxToAdd = currentPotentialMaxScore;
			}
			else if(currentPotentialMaxScore <= currentPotentialMaxScore2) {
				maxToAdd = currentPotentialMaxScore2;
			}
			maxCompanyScore = maxCompanyScore + maxToAdd;
		}
		return maxCompanyScore;
	}
	
	
	//COMPUTES THE DIFFERENCE BETWEEN THE CANDIDATE'S SCORE AND THE COMPANIES IDEAL SCORE
	
	public static int getCandidateScore(ArrayList<Trio> company, ArrayList<Pair> candidate) {
		int candidateScore = 0;
		if (company.size() == 0 || candidate.size() == 0){
			return -1;
		}
		for(int i = 0; i<20; i++){
			int companyAnswer = company.get(i).getQuestionAnswer();
			int candidateAnswer = candidate.get(i).getAnswer();
			int differenceBetweenScores = Math.abs(companyAnswer - candidateAnswer);
			int weight = company.get(i).getQuestionWeight();
			int questionScore = weight * differenceBetweenScores;
			candidateScore = candidateScore + questionScore;
		}
		return candidateScore;
	}
	
	
	//TAKES THE DIFFERENT SCORES COMPUTED AND SUBSTITUTES THEM INTO THE FORMULA USED FOR THE ALGORITHM
	
	public static Double weightedChoiceAlgorithm(ArrayList<Trio> companyIdealMatchArray, ArrayList<Pair> candidateScoreArray) {
		double finalCompanyScore = getCompanyBenchMarkScore(companyIdealMatchArray);
		double finalCandidateScore = getCandidateScore(companyIdealMatchArray, candidateScoreArray);
		if (finalCandidateScore == -1){ return (double)0; }
		double calculateScore = 100 * (1 - (finalCandidateScore/finalCompanyScore));
		return calculateScore;
	}
	
	
	
	
	//@ @ @ @ @ @ @ @ @ @ @ @ @ @ @ @ @ @ @ @ @ @ @ @ @ @ @ @ @ @ @ @ @ @ @ @ @ @ @ @ @ @ @ @ @ @ @ @ @ @ @ @ @ @ @ @ @ @
	//METHODS USED FOR TESTING
	//USED FOR TESTING DIFFERENT INPUTS AND SIMULATED EXAMPLES
	//TESTED CANDIDATE SCORE
	//TESTED COMPANY BEST MATCHING SCORE
	//@ @ @ @ @ @ @ @ @ @ @ @ @ @ @ @ @ @ @ @ @ @ @ @ @ @ @ @ @ @ @ @ @ @ @ @ @ @ @ @ @ @ @ @ @ @ @ @ @ @ @ @ @ @ @ @ @ @ 
	
	
	
	/*
	public static void printInputSimulation(ArrayList<Trio> arrayOfTriosToPrint) {
		if(arrayOfTriosToPrint == candidateScoreArray) {
			System.out.println("Qualifications Listed By Candidate: ");
			System.out.println("====================================");
		}
		else if(arrayOfTriosToPrint == companyIdealMatchArray) {
			System.out.println("Perfect Fit Qualifications By Company: ");
			System.out.println("====================================");
		}
		
		for(Trio trio : arrayOfTriosToPrint) {
			System.out.print("Question ID: " +trio.getQuestionID());
			System.out.print(" , Question Answer : " + trio.getQuestionAnswer());
			System.out.println(" , Question Weighting : " + trio.getQuestionWeight());
		}	
		System.out.println("");
	}
	
	
	public static void printCalculatedScore() {
		int companyScore = getCompanyBenchMarkScore(companyIdealMatchArray);
		int candidateScore = getCandidateScore(companyIdealMatchArray, candidateScoreArray);
		System.out.println("Candidate Score");
		System.out.println("----------------");
		System.out.println("Worst Potential Score");
		System.out.println("");
		System.out.println(candidateScore);
		System.out.println("---");
		System.out.println(companyScore);
		System.out.println("");
	}

	
	public static void runTestScenario() {
		Trio cand1 = new Trio(111,2,2);
		Trio cand2 = new Trio(222,4,0);
		Trio cand3 = new Trio(333,3,1);
		Trio cand4 = new Trio(444,3,2);
		Trio cand5 = new Trio(555,3,1);
		Trio cand6 = new Trio(666,5,1);
		Trio cand7 = new Trio(777,5,0);
		Trio cand8 = new Trio(888,1,2);
		Trio cand9 = new Trio(999,2,0);
		Trio cand10 = new Trio(101,4,0);
		candidateScoreArray.add(cand1);
		candidateScoreArray.add(cand2);
		candidateScoreArray.add(cand3);
		candidateScoreArray.add(cand4);
		candidateScoreArray.add(cand5);
		candidateScoreArray.add(cand6);
		candidateScoreArray.add(cand7);
		candidateScoreArray.add(cand8);
		candidateScoreArray.add(cand9);
		candidateScoreArray.add(cand10);

		Trio comp1 = new Trio(1,2,2);
		Trio comp2 = new Trio(2,5,0);
		Trio comp3 = new Trio(3,5,1);
		Trio comp4 = new Trio(4,3,2);
		Trio comp5 = new Trio(5,2,1);
		Trio comp6 = new Trio(6,1,1);
		Trio comp7 = new Trio(7,2,0);
		Trio comp8 = new Trio(8,4,2);
		Trio comp9 = new Trio(9,3,0);
		Trio comp10 = new Trio(10,5,0);
		companyIdealMatchArray.add(comp1);
		companyIdealMatchArray.add(comp2);
		companyIdealMatchArray.add(comp3);
		companyIdealMatchArray.add(comp4);
		companyIdealMatchArray.add(comp5);
		companyIdealMatchArray.add(comp6);
		companyIdealMatchArray.add(comp7);
		companyIdealMatchArray.add(comp8);
		companyIdealMatchArray.add(comp9);
		companyIdealMatchArray.add(comp10);
	
		printInputSimulation(candidateScoreArray);
		printInputSimulation(companyIdealMatchArray);
		printCalculatedScore();
		//System.out.println("Matching Percent:" + Math.round(weightedChoiceAlgorithm()) +"%");
	}
	
	
	public static void main(String[] args) {
		runTestScenario();
	}
	*/

}
