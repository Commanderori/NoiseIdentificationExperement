
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