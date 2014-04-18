//Rob Hicks and Cody Woodard.
//CS 4444
// identification through noise psychology experiment.

//This file packages all of the required information into a text file called "results.txt" for easy return to the experimenter.

import java.io.*;


public class ResultPackager {
	
	double[] timeTakentoChooseImage;
	boolean[] choseCorrectlyOrIncorrectly;
	int[] levelOfNoise;
	int whichTrial = -1;
	FileWriter fw;

public ResultPackager (){
	timeTakentoChooseImage = new double[100];
	choseCorrectlyOrIncorrectly = new boolean[100];
	levelOfNoise = new int[100];
	whichTrial = 0;
}



public void nextTrial(){
	
	whichTrial++;
}

/*
 * For packageNoiseLevel, the variable noise is the standard deviation chosen to create a specific level of noise.  The case numbers (5, 15, 25, etc.) are the standard deviation so when this function
 * is called, you MUST pass in one of those noise levels (5, 15, 25, etc.)  Otherwise, none of the cases will fire and Invalid Noise Level will print out.
 */

public void packageNoiseLevel(int noise){
	boolean problem = false;
	
	switch(noise) {
		
		case 25: noise = 1;
				break;
		case 55: noise = 2;
				break;
		case 75: noise = 3;
				break;
		case 125: noise = 4;
				break;
		case 200: noise = 5;
				break;
		case 300: noise = 6;
				break;
		case 400: noise = 7;
				break;
		case 550: noise = 8;
				break;
		case 750: noise = 9;
				break;
		case 950: noise = 10;
				break;
		default: System.out.println("Invalid Noise level");
				problem = true;
				break;
	}
	
	if (problem == true)
		System.out.println("Warning: Noise Level NOT recorded due to invalid noise level selection");
	else
		levelOfNoise[whichTrial] = noise;
}

public void packageTimeTaken (double timer){
	
	timeTakentoChooseImage[whichTrial] = timer;

}

public void packageChoiceAccuracy (boolean correct){
	
	choseCorrectlyOrIncorrectly[whichTrial] = correct;
	/*if (choseCorrectlyOrIncorrectly[whichTrial] == true)
		System.out.println("Successful!  They were: correct.");
	else 
		System.out.println("Successful!  They were: incorrect.");*/
}
	
public void packageResults (){ 
	
	nextTrial();
	int j = 1;
	int i = 0;
	
	FileWriter fw = null;
	try {
		fw = new FileWriter("Results.txt", false);
	} catch (IOException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	
	PrintWriter pw = new PrintWriter(fw, true);
	
	
	
	
	for (i=1; i<whichTrial-1; i++){
		
		pw.print("Trial: " + j+"  ");
		
		if (choseCorrectlyOrIncorrectly[i] == true)
			pw.print("Correct  ");
		else
			pw.print("Incorrect");
		
		pw.print(" Noise Level:" + levelOfNoise[i] +"  ");
		pw.println("Time Taken:"+ timeTakentoChooseImage[i]+"  ");
		
		j++;
	}
	
	pw.close();
			

}

}