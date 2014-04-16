
public class ImageHandler {
	private int numberOfCompConditions=0;
	private int numberOfNoiseConditions=0;
	private String[] noiseConditionArray;
	private String[] comparisonConditionArray1;
	private String[] comparisonConditionArray2;
	private String foulderName;
	
	public ImageHandler(){
		Setup();
	}
	
	private void Setup(){
		numberOfCompConditions = 0;
		numberOfNoiseConditions = 0;
		noiseConditionArray = new String[10];
		comparisonConditionArray1 = new String[10];
		comparisonConditionArray2 = new String[10];
		foulderName = "blank";
	}
	
	public void setNONC(int NONC){//sets the number of noise conditions. (should always be 2)
		numberOfNoiseConditions = NONC;
	}
	
	public void setNOCC(int NOCC){//the number of comparison conditions
		numberOfCompConditions = NOCC;
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
	
	public String pickRandomNoiseCond(){
		int WIP = (int)(Math.random()*numberOfNoiseConditions);
		System.out.print("Showing Image "+WIP + "\n");
		return noiseConditionArray[WIP];
	}
	
	public String pickRandomCompCond1(){
		return comparisonConditionArray1[(int)(Math.random()*numberOfCompConditions)];
	}
	
	public String pickRandomCompCond2(){
		return comparisonConditionArray2[(int)(Math.random()*numberOfCompConditions)];
	}
	 
}

/*
Example of the configuration file.

For this program, a configuration file is used in order to tell it where each of the different images it is supposed to use is located, and how they are associated
with each other. This file is located inside the images folder itself, not in any of the subdirectories.

For example, inside the image folder if you have a folder called "set1" that contains two test images and 4 comparison images for each of them.
your configuration file would look like this:

Test:
set1/yourtest1.jpg
set1/yourtest2.jpg
Imageset1:
set1/yourcomparison1.jpg
set1/yourcomparison2.jpg
set1/yourcomparison3.jpg
set1/yourcomparison4.jpg
Imageset2:
set1/yourcomparison1.jpg
set1/yourcomparison2.jpg
set1/yourcomparison3.jpg
set1/yourcomparison4.jpg
end:

If you have multiple foulders in your images directory, the config file would look like this:
Test:
set1/yourtest1.jpg
set1/yourtest2.jpg
Imageset1:
set1/yourcomparison1.jpg
set1/yourcomparison2.jpg
set1/yourcomparison3.jpg
set1/yourcomparison4.jpg
Imageset2:
set1/yourcomparison1.jpg
set1/yourcomparison2.jpg
set1/yourcomparison3.jpg
set1/yourcomparison4.jpg
end:

Test:
set2/yourtest1.jpg
set2/yourtest2.jpg
Imageset1:
set2/yourcomparison1.jpg
set2/yourcomparison2.jpg
set2/yourcomparison3.jpg
set2/yourcomparison4.jpg
Imageset2:
set2/yourcomparison1.jpg
set2/yourcomparison2.jpg
set2/yourcomparison3.jpg
set2/yourcomparison4.jpg
end:

.
.
.

and so on. Set2 is the name of the inside the images foulder, and the "yourcomparison/test.jpg" is the name of the image itself.
*/