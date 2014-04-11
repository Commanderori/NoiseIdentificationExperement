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
	parentDirecty.mkdir(); 
	hash = "Result";
	fileName = hash + ".txt";
	file = new File(parentDir, fileName);
	file.createNewFile();
	
	out = new BufferedWriter(new FileWriter(fileName));
	
	trialNumber = new trialNumber[100];
	timeTakentoChooseImage = new timeTaketoChooseImage[100];
	choseCorrectlyOrIncorrectly = new choseCorrectlyOrIncorrectly[100];
	levelOfNoise = new levelOfNoise[100]
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
	
	switche(noise) {
		
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
	
	timeTakenToChooseImage[whichTrial] = timer;

}

public void packageChoiceAccuracy (boolean correct){
	
	choseCorrectlyOrIncorrectly[whichTrial] = correct;

}
	
public void packageResults (){
	
	int j = 1;
	for(i=0; i<whichTrial; i++){
		out.write
	}
			

}

}