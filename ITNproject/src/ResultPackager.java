import java.io.*;


public class ResultPackager {
	
	double[] timeTakentoChooseImage;
	boolean[] choseCorrectlyOrIncorrectly;
	int[] levelOfNoise;
	
	int whichTrial;
	BufferedWriter out;
	
	final File parentDirectory;
	final String hash;
	final String fileName;
	final File file;
	
public ResultPackager (){
	
	parentDirectory = new File("Result");
	parentDirectory.mkdir(); 
	hash = "Result";
	fileName = hash + ".txt";
	file = new File(parentDirectory, fileName);
	
	try {
		file.createNewFile();
	} catch (IOException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	
	try {
		out = new BufferedWriter(new FileWriter(fileName));
	} catch (IOException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	
	
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
		
		case 5: noise = 1;
				break;
		case 15: noise = 2;
				break;
		case 25: noise = 3;
				break;
		case 35: noise = 4;
				break;
		case 45: noise = 5;
				break;
		case 65: noise = 6;
				break;
		case 80: noise = 7;
				break;
		case 90: noise = 8;
				break;
		case 105: noise = 9;
				break;
		case 115: noise = 10;
				break;
		default: System.out.println("Invalid Noise level");
				problem = true;
				break;
	}
	
	levelOfNoise[whichTrial] = noise;
}

public void packageTimeTaken (double timer){
	
	timeTakentoChooseImage[whichTrial] = timer;

}

public void packageChoiceAccuracy (boolean correct){
	
	choseCorrectlyOrIncorrectly[whichTrial] = correct;

}
	
public void packageResults (){
	
	nextTrial();
	int j = 1;
	int i = 0;
	
	for(i=0; i<whichTrial; i++){
		try {
		out.write("Trial" + j);
		if (choseCorrectlyOrIncorrectly[i] = true){
			out.write("Correct\n");
		}
		else {
			out.write("Incorrect\n");
		}
		out.write("Time Taken: "+ timeTakentoChooseImage[i] + "\n");
		out.newLine();
		j++;
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
			

}

}