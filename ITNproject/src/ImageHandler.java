//Rob Hicks
//CS 4444
//identification through noise psychology experiment.

//this class file is what allows the main program to store all of the locations of the images it will use.

public class ImageHandler {
	private int numberOfCompConditions1;
	private int numberOfCompConditions2;
	private int numberOfNoiseConditions;
	private String[] noiseConditionArray;
	private String[] comparisonConditionArray1;
	private String[] comparisonConditionArray2;
	public Boolean isA1 = true;
	public Boolean isJ = true;
	
	public ImageHandler(){
		Setup();
	}
	
	private void Setup(){ // initilizes all of the values.
		numberOfCompConditions1 = 20;
		numberOfCompConditions2 = 20;
		numberOfNoiseConditions = 2;
		noiseConditionArray = new String[10];
		comparisonConditionArray1 = new String[10];
		comparisonConditionArray2 = new String[10];
	}
	
	public void setNONC(int NONC){//sets the number of noise conditions. (should always be 2, function is for later expansion)
		numberOfNoiseConditions = NONC;
	}
	
	public void setNOCC1(int NOCC){//the number of comparison conditions
		numberOfCompConditions1 = NOCC;
	}
	public void setNOCC2(int NOCC){//the number of comparison conditions
		numberOfCompConditions2 = NOCC;
	}
	
	public void insertNoiseCond(int location, String input){
		noiseConditionArray[location] = input;
	}

	public void insertCompCond1(int location, String input){
		comparisonConditionArray1[location] = input;
	}
	
	public void insertCompCond2(int location, String input){
		comparisonConditionArray2[location] = input;
	}
	
	public String pickRandomNoiseCond(){//picks a random noise condition from the array, and then sets then bool that tells
										//the program which one was picked.
		int WIP = (int)(Math.random()*numberOfNoiseConditions);
		System.out.print("Showing Image "+WIP + "\n");
		if (WIP == 0){
			isA1 = true;
		}
		else {
			isA1 = false;
		}
		return noiseConditionArray[WIP];
	}
	
	public String pickRandomCompCond1(){
		return comparisonConditionArray1[(int)(Math.random()*numberOfCompConditions1)];
	}
	
	public String pickRandomCompCond2(){
		return comparisonConditionArray2[(int)(Math.random()*numberOfCompConditions2)];
	}
	 
}

/*
Example of the configuration file.

For this program, a configuration file is used in order to tell it where each of the different images it is supposed to use is located, and how they are associated
with each other. This file is located inside the images folder itself, not in any of the subdirectories.

For example, inside the image folder if you have a folder called "set1" that contains two test images 
and 4 comparison images for each of them. your configuration file would look like this:
Trials:
75

Test:
images/set1/yourtest1.jpg
images/set1/yourtest2.jpg
Imageset1:
images/set1/yourcomparison1.jpg
images/set1/yourcomparison2.jpg
images/set1/yourcomparison3.jpg
images/set1/yourcomparison4.jpg
Imageset2:
images/set1/yourcomparison1.jpg
images/set1/yourcomparison2.jpg
images/set1/yourcomparison3.jpg
images/set1/yourcomparison4.jpg
end:

If you have multiple foulders in your images directory, the config file would look like this:
Test:
images/set1/yourtest1.jpg
images/set1/yourtest2.jpg
Imageset1:
images/set1/yourcomparison1.jpg
images/set1/yourcomparison2.jpg
images/set1/yourcomparison3.jpg
images/set1/yourcomparison4.jpg
Imageset2:
images/set1/yourcomparison1.jpg
images/set1/yourcomparison2.jpg
images/set1/yourcomparison3.jpg
images/set1/yourcomparison4.jpg

Test:
images/set2/yourtest1.jpg
images/set2/yourtest2.jpg
Imageset1:
images/set2/yourcomparison1.jpg
images/set2/yourcomparison2.jpg
images/set2/yourcomparison3.jpg
images/set2/yourcomparison4.jpg
Imageset2:
images/set2/yourcomparison1.jpg
images/set2/yourcomparison2.jpg
images/set2/yourcomparison3.jpg
images/set2/yourcomparison4.jpg
end:

.
.
.

and so on. Set2 is the name of the inside the images foulder, and the "yourcomparison/test.jpg" is the name of the image itself.
*/