//Robert Hicks and Cody Woodard.
//Winter 2014, Transylvania University.
//Object identification through noise, programmed for Dr. Bethany Jurs.

import java.awt.Component;
import java.awt.EventQueue;

import javax.imageio.ImageIO;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.ImageIcon;
import javax.swing.Timer;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.SwingConstants;

import java.awt.Font;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;


public class MainScreen implements KeyListener, ActionListener {//extends ImageHandler

	private JFrame frame;
	private int TutorialPosition = 0;
	private int TrialCounter = 1;
	private int NumberOfFolders = 100; // this line will need need to be edited if there are more than 100 folders.
	private long systemTimeValueToFindTimeTaken;
	private char[] ChoiceArray;
	private double[] timeTakenArray;
	private Boolean noiseOrComp = true;
	private Boolean takeBreak = false;
	private int numberOfFoldersInUse = -1; 
	private int folderToUse;
	private int configTracker = 0;
	private Boolean firstRun = true;
	private ResultPackager ResPack;
	private NoiseGenerator NoiseGen;
	private int numberOfTrials;
	private int currentTrial = 0;
	
	
	private ImageHandler[] imageObjects;
	private String[] configReadIn;
	private int[] STDDArray;
	
	private Timer tickover; //multiple use timer
	private int tickoverLocationCount = 0;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {// this is the "main program", basically where java will start.
		EventQueue.invokeLater(new Runnable() {
			public void run() {//this creates the window that you see, containing the program.
				try {
					MainScreen window = new MainScreen();
					window.frame.setVisible(true);//setvisible is required to actually make it display. Otherwise you get a black box.
				} catch (Exception e) {//required error checking.
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public MainScreen() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		imageObjects = new ImageHandler[NumberOfFolders];		
		//this creates the array of objects required to hold the file system.
		for (int createArray = 0; createArray < NumberOfFolders; createArray++){// this for loop initilizes all of the objects within the array.
			imageObjects[createArray] = new ImageHandler();
		}
		ChoiceArray = new char[1000];
		timeTakenArray = new double [1000];
		configReadIn = new String[1000];
		ResPack = new ResultPackager();
		NoiseGen = new NoiseGenerator();
		
		STDDArray = new int[10]; // array of values that can be passed to the gausian noise generator.
		
		STDDArray[0] = 25;
		STDDArray[1] = 55;
		STDDArray[2] = 75;
		STDDArray[3] = 125;
		STDDArray[4] = 200;
		STDDArray[5] = 300;
		STDDArray[6] = 400;
		STDDArray[7] = 550;
		STDDArray[8] = 750;
		STDDArray[9] = 950;
		
		
		
		frame = new JFrame();
		frame.setBounds(100, 100, 600, 600);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		JLabel labelNoiseOverlay = new JLabel("");
		labelNoiseOverlay.setIcon(new ImageIcon("images/BR1.png"));
		labelNoiseOverlay.setHorizontalAlignment(SwingConstants.CENTER);
		labelNoiseOverlay.setBounds(40, 58, 504, 426);
		labelNoiseOverlay.setVisible(false);
		
		JLabel LabelKeyboard = new JLabel(" ");
		LabelKeyboard.setIcon(new ImageIcon("images/keyboardFingerPlacement.jpg"));
		LabelKeyboard.setBounds(40, 72, 504, 426);
		frame.getContentPane().add(LabelKeyboard);
		frame.getContentPane().add(labelNoiseOverlay);
		
		JLabel LabelNoiseCondition = new JLabel(" ");
		LabelNoiseCondition.setIcon(new ImageIcon("images/YellowKitten.jpg"));
		LabelNoiseCondition.setBounds(40, 58, 504, 426);
		LabelNoiseCondition.setVisible(false);
		frame.getContentPane().add(LabelNoiseCondition);
		
		JLabel LabelCompConditionOne = new JLabel(" ");
		LabelCompConditionOne.setIcon(new ImageIcon("images/GrayKitten.jpg"));
		LabelCompConditionOne.setBounds(40, 151, 256, 333);
		LabelCompConditionOne.setVisible(false);
		frame.getContentPane().add(LabelCompConditionOne);
		
		JLabel LabelCompConditionTwo = new JLabel(" ");
		LabelCompConditionTwo.setIcon(new ImageIcon("images/YellowKittyTwo.jpg"));
		LabelCompConditionTwo.setBounds(306, 151, 256, 333);
		LabelCompConditionTwo.setVisible(false);
		frame.getContentPane().add(LabelCompConditionTwo);
		
		JLabel labelInstruction = new JLabel("Please place your hands on the keyboard as shown below.");
		labelInstruction.setFont(new Font("Times New Roman", Font.BOLD, 14));
		labelInstruction.setHorizontalAlignment(SwingConstants.CENTER);
		labelInstruction.setBounds(10, 11, 564, 30);
		frame.getContentPane().add(labelInstruction);
		
		JLabel lableNextKey = new JLabel("Once you have done so, Press the space bar.");
		lableNextKey.setFont(new Font("Times New Roman", Font.BOLD, 14));
		lableNextKey.setHorizontalAlignment(SwingConstants.CENTER);
		lableNextKey.setBounds(10, 521, 564, 30);
		frame.getContentPane().add(lableNextKey);
		
		JLabel lblNoiseconditionimg = new JLabel("<html>You have reached a trial breakpoint.\r\nPress the Spacebar to Continue</html>");
		lblNoiseconditionimg.setIcon(new ImageIcon("C:\\Users\\Rob\\Desktop\\Sliding panel\\commonmask300high\\Pair 10\\MCRP003.JPG"));
		lblNoiseconditionimg.setBounds(184, 135, 218, 300);
		lblNoiseconditionimg.setVisible(false);
		frame.getContentPane().add(lblNoiseconditionimg);
		
		JLabel lblCompCond1 = new JLabel("CompCond1");
		lblCompCond1.setIcon(new ImageIcon("C:\\Users\\Rob\\Desktop\\Sliding panel\\commonmask300high\\Pair 10\\MCLP004.JPG"));
		lblCompCond1.setBounds(20, 135, 218, 300);
		lblCompCond1.setVisible(false);
		frame.getContentPane().add(lblCompCond1);
		
		JLabel lblCompCond2 = new JLabel("New label");
		lblCompCond2.setIcon(new ImageIcon("C:\\Users\\Rob\\Desktop\\Sliding panel\\commonmask300high\\Pair 10\\MCLP005Test.jpg"));
		lblCompCond2.setBounds(348, 135, 216, 300);
		lblCompCond2.setVisible(false);
		frame.getContentPane().add(lblCompCond2);
		
		frame.addKeyListener(this);
		loadFileToArr();
	}
	
	
	private void showKittenNoise(){ //Show Tutorial Image II
		Component[] components = frame.getContentPane().getComponents();
		components[0].setVisible(false);//noise overlay
		components[1].setVisible(true);//keyboard image
		components[2].setVisible(true);//kitten with noise
		components[3].setVisible(false);//comparison kitten 1
		components[4].setVisible(false);//comparison kitten 2.
		
	}
	
	private void showKittenComparison(){ //Show Tutorial Image III
		Component[] components = frame.getContentPane().getComponents();
		components[0].setVisible(false);
		components[1].setVisible(false);
		components[2].setVisible(false);
		components[3].setVisible(true);
		components[4].setVisible(true);
	}
	
	private void hideAll(){ //hide all of the content of the frame.
		Component[] components = frame.getContentPane().getComponents();
		components[0].setVisible(false);
		components[1].setVisible(false);
		components[2].setVisible(false);
		components[3].setVisible(false);
		components[4].setVisible(false);
		components[6].setVisible(false);
		components[7].setVisible(false);
		components[8].setVisible(false);
		components[9].setVisible(false);
	}
	
	private void hideNoise(){ 
		Component[] components = frame.getContentPane().getComponents();
		components[7].setVisible(false); 
	}
	
	private void hideComparison(){
		Component[] components = frame.getContentPane().getComponents();
		components[8].setVisible(false);
		components[9].setVisible(false);
	}
	
	private void showEndOfTrial(){ // this is called once all of the trials have been completed. It outputs the results file.
		ResPack.packageResults();
		Component[] components = frame.getContentPane().getComponents();
		components[6].setVisible(true);
		((JLabel) components[6]).setText("You have completed the Experiment. Please send the results file in.");
	}
	
	//this function is used by the program to swap between the noise condition and the comparison
	//conditions. It picks a random image/s to display, and then shows it/them.
	private void swapComparisonForNoise(){
		Component[] components = frame.getContentPane().getComponents();
		
		//this if shows the noise and noise condition.
		if (noiseOrComp == true && (numberOfTrials != currentTrial-1)){
			//this sets one of the images to the noisecomp.
			folderToUse = (int)(Math.random()*numberOfFoldersInUse);
			File noiseCompFile = new File(imageObjects[folderToUse].pickRandomNoiseCond());
			int STDDChoice = (int)(Math.random()*9);
			ResPack.packageNoiseLevel(STDDArray[STDDChoice]);
			((JLabel) components[7]).setIcon( new ImageIcon(NoiseGen.addNoiseToImage(noiseCompFile, STDDArray[STDDChoice])));
			((JLabel) components[7]).setVisible(true);
			((JLabel) components[8]).setVisible(false);
			((JLabel) components[9]).setVisible(false);
			noiseOrComp = false;
		}
		
		// this shows the comparison conditions. The side the conditions are on is randomized.
		else if (noiseOrComp == false && (numberOfTrials != currentTrial-1)) { //comp[8] is left
			//System.out.print("showing comp.\n");
			String A1 = imageObjects[folderToUse].pickRandomCompCond1();
			String B1 = imageObjects[folderToUse].pickRandomCompCond2();
			System.out.print("Random1 is " +A1 + " and Random 2 is " +B1 + "\n");
			if ((int)(Math.random()*2)== 1){
				((JLabel) components[8]).setIcon( new ImageIcon(A1) );
				imageObjects[folderToUse].isJ = false;
				((JLabel) components[9]).setIcon( new ImageIcon(B1) );
			}
			else {
				((JLabel) components[8]).setIcon( new ImageIcon(B1) );
				imageObjects[folderToUse].isJ = true;
				((JLabel) components[9]).setIcon( new ImageIcon(A1) );
			}
			((JLabel) components[7]).setVisible(false);
			((JLabel) components[8]).setVisible(true);
			((JLabel) components[9]).setVisible(true);
			noiseOrComp = true;
		}
	}
	
	@Override
	//This function allows the program to register key events from the keyboard.
	//it only cares about spacebar, j, and f currently, because they are the only keys used.
	//see the individual if statements for description of their functions.
	public void keyPressed(KeyEvent inputKey) {
		//variable declarations
		final Component[] components = frame.getContentPane().getComponents();//tells the event what components (objects on the screen) exist.
		long systemTimeTaken;
		
		//The spacebar is used for the tutorials, And to continue after you reach a breakpoint.
		if (inputKey.getKeyChar() == ' '){
			if (TutorialPosition == 0){
				((JLabel) components[5]).setText("This is an example of the image covered in noise you will see."); // you must tell the program that the component it is talking to is a JLabel or it wont work.
				((JLabel) components[6]).setText("Press space bar to continue.");
				showKittenNoise();
				TutorialPosition += 1;
			}
			
			else if (TutorialPosition == 1){
				showKittenComparison();
				((JLabel) components[5]).setText(" Press F for the left or J for the right image. chose the one closest to the initial image.");
				((JLabel) components[6]).setText("Please press the j key to continue");
				TutorialPosition += 1;
			}
			
			//once the tutorial is completed, begin the experiment.
			else if (TutorialPosition == 3){
				components[7].setVisible(true);
				((JLabel) components[7]).paintImmediately(0,0,218,300);//this line is required to make the label show.
				components[5].setVisible(false);
				components[7].setVisible(false);
				startTrial();
				TutorialPosition +=1;	
			}
			else if (TutorialPosition == 4 && takeBreak == true){
				startTrial();
			}
			else if (tickoverLocationCount == -1){
				System.exit(0);
			}
		}
		
		//if the key pressed was j, then do...
		else if (inputKey.getKeyChar() == 'j'){
			
			//the participant pressed the correct key in the tutorial, advance.
			if (TutorialPosition == 2 ){
				((JLabel) components[5]).setText("Very good. Now press the space bar to begin the experiment.");
				hideAll();
				TutorialPosition+= 1;
			}
			
			//the participant pressed j in the experiment. Assuming that it is at the right
			//section of the experiment, that is the comparison images having been displayed, then
			//it selects the right hand image. correct/incorrect and current time are recorded.
			else if ((TutorialPosition == 4) && (tickoverLocationCount > 2) && (takeBreak == false)){//the 4 means you aren't in the tutorial anymore.
				systemTimeTaken = System.currentTimeMillis();
				System.out.print(ResPack.whichTrial + " is whichtrial and time taken is " +(findTimeTaken(systemTimeTaken) / 1000) );
				ResPack.packageTimeTaken(findTimeTaken(systemTimeTaken) / 1000);
				if (imageObjects[folderToUse].isJ == true){
					ChoiceArray[TrialCounter] = 'C';
					ResPack.packageChoiceAccuracy(true);
				}
				else{
					ChoiceArray[TrialCounter] = 'I';
					ResPack.packageChoiceAccuracy(false);
				}
				System.out.print("ChoiceArray " + ChoiceArray[TrialCounter] + " And TimeTaken " + timeTakenArray[TrialCounter] + "\n");
				tickover.stop();
				tickoverLocationCount = 4;
				startTrial();
				TrialCounter+=1;
			}
		}
		
		//if the key that the participant pressed was f, then do...
		else if (inputKey.getKeyChar() == 'f'){
			
			//the participant pressed the wrong key in the tutorial.
			if(TutorialPosition == 2){
				((JLabel) components[5]).setText("That was the f key. The Gray kitten does not match the first yellow kitten.");
			}
			
			//the participant pressed f in the experiment. Assuming that it is at the right
			//section of the experiment, the comparison images having been displayed, then it selects
			//the left hand image. correct/incorrect and current time are recorded.
			else if ((TutorialPosition == 4) && (tickoverLocationCount > 2) && (takeBreak == false)){
				systemTimeTaken = System.currentTimeMillis();
				timeTakenArray[TrialCounter] = findTimeTaken(systemTimeTaken) / 1000;
				if (imageObjects[folderToUse].isJ == true){
					ChoiceArray[TrialCounter] = 'I';
					ResPack.packageChoiceAccuracy(false);
				}
				else{
					ChoiceArray[TrialCounter] = 'C';
					ResPack.packageChoiceAccuracy(true);
				}
				System.out.print("ChoiceArray " + ChoiceArray[TrialCounter] + " And TimeTaken " + timeTakenArray[TrialCounter] + "\n");
				tickover.stop();
				tickoverLocationCount = 4;
				startTrial();
				TrialCounter+=1;
			}
		}
	}

	// helper function for the above, converts the long system time to a double indicating how long you took
	private double findTimeTaken (long time){
		ResPack.packageTimeTaken((time - systemTimeValueToFindTimeTaken));
		return (double)(time - systemTimeValueToFindTimeTaken);
		
	}
	
	//this function starts a new trial by instantly calling the timer function. It also checks to see
	//if you have done some multiple of fifty trials, and if you have gives you a break.
	//The break will last until you press the spacebar.
	private void startTrial(){
		Component[] components = frame.getContentPane().getComponents();
		if ((TrialCounter % 50 == 0) && (takeBreak == false)){
			//System.out.print("TrialCounter in startTrial = " +TrialCounter + "\n");
			hideComparison();
			components[7].setVisible(true);
			((JLabel) components[7]).setIcon(null);
			takeBreak = true;
		}
		else{
			takeBreak = false;
			tickover = new Timer(0, this);
			tickover.setRepeats(false);
			tickover.start(); 
		}
	}
	
	@Override
	//this actionPerformed is linked to the timer created within it. It is pulsed when you hit
	//the spacebar at the end of the tutorial, then handles itself except for when the participant
	// has pressed J or F, when it gets another pulse. (pulse being an instant call without a delay)
	public void actionPerformed(ActionEvent arg0) { // this is the call that the timer makes.
		int delayBetweenImages = 0;
		
		//This if shows the comparison image and the noise overlay on top of it, then sets a timer so
		//it is only displayed for one second.
		if (numberOfTrials < currentTrial){
			hideAll();
			tickoverLocationCount = -1;
		}
		
		if (tickoverLocationCount == 0){
			ResPack.nextTrial();
			currentTrial++;
			//System.out.print("TrialCounter in timer = " +TrialCounter + "\n");
			swapComparisonForNoise();
			tickover = new Timer(1000, this);
			tickover.setRepeats(false);
			tickover.start(); 
			tickoverLocationCount += 1;
		}
		
		//this if hides the comparison image and the noise filter for between 250 and 300 milliseconds.
		else if (tickoverLocationCount == 1){
			hideNoise();
			delayBetweenImages = (int)(Math.random()*50)+250;
			tickover = new Timer(delayBetweenImages, this);
			tickover.setRepeats(false);
			tickover.start(); 
			tickoverLocationCount += 1;
		}
		
		//this if shows the two comparison images, and then counts up to 5 seconds.
		else if (tickoverLocationCount == 2){
			systemTimeValueToFindTimeTaken = System.currentTimeMillis();
			swapComparisonForNoise();
			tickover = new Timer(5000, this);
			tickover.setRepeats(false);
			tickover.start(); 
			tickoverLocationCount += 1;
		}
		
		//this if only is reached if it takes more than 5 seconds for the participant to select a
		//comparison image. It hides the comparison images and then keeps counting, waiting for a
		//participant to select either J or F. It sets the fiveSecondsPassed boolean to true so that
		//the F and J key receptors know to add 5 times the number of times this is reached
		//to whatever the time taken was.
		else if (tickoverLocationCount == 3){
			hideComparison();
			tickover = new Timer(5000, this);
			tickover.setRepeats(false);
			tickover.start(); 
		}
		else if (tickoverLocationCount == 4){
			hideComparison();
			delayBetweenImages = (int)(Math.random()*50)+250;
			tickover = new Timer(delayBetweenImages, this);
			tickover.setRepeats(false);
			tickover.start();
			tickoverLocationCount = 0;
		}
		else if (tickoverLocationCount == -1){
			showEndOfTrial();
		}
	}
	
	//These two functions serve no purpose for the program, they are only here because
	//implementing KeyListener requires it.
	@Override
	public void keyReleased(KeyEvent inputKey) {/*functiongoeshere*/}
	@Override
	public void keyTyped(KeyEvent inputKey){/*functiongoeshere*/}
	
	
	//This function pulls in the configuration file, loads it into an array, then passes it to the fill array function
	//in order to pull in all of the images. if the config file doesn't exist, the program wont run.
	private void loadFileToArr(){
		if (firstRun == true){
			//System.out.print(configTracker);
			String path = "images/config.txt";
				FileInputStream configFile = null;
			try {
				configFile = new FileInputStream(path);
			} catch (FileNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				FileWriter fw = null;
				try {
					fw = new FileWriter("Errors.txt", false);
				} catch (IOException F) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				PrintWriter pw = new PrintWriter(fw, true);
				pw.println("Error, file " + path + " does not exist.");
				pw.close();
				System.exit(0);
			}
			BufferedReader in = new BufferedReader(new InputStreamReader(configFile));
		
			try {
				while (in.ready()) { 
					configReadIn[configTracker] = in.readLine(); 
					//System.out.println(configReadIn[configTracker]);
					configTracker++;
				}
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			firstRun = false;
		}
		fillArrays(configTracker);
	}
	
	//this file uses the array from the previous function in order to pull in all of the images listed in the config file,
	//as well as how many trials the experementer has decided on. 
	private void fillArrays(int arrToFill){ // this should also be in imagehandler
		String input = "null";
		int typeOfInput = 0;
		int CRIcount = 0;//config read in count
		int arrCount = 0;
		numberOfFoldersInUse = -1;
		typeOfInput = 0;
		CRIcount = 0;
		arrCount = 0;
		FileWriter fw = null;
			try {
				fw = new FileWriter("Errors.txt", false);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			PrintWriter pw = new PrintWriter(fw, true);
		boolean wasError = false;
		
		
		input = configReadIn[CRIcount];
		File testForExistance = new File(input);
		while (!(input.startsWith("End:"))){
			//System.out.print(input + "\n");
			
			if(input.startsWith("Test")){
				if(typeOfInput == 3){
					imageObjects[numberOfFoldersInUse].setNOCC2(arrCount);
				}
				numberOfFoldersInUse += 1;
				//System.out.println(input + " inside");
				typeOfInput = 1;
				arrCount=0;
			}
			else if (input.startsWith("ImageSet1")){
				typeOfInput = 2;
				arrCount=0;
			}
			else if(input.startsWith("ImageSet2")){
				imageObjects[numberOfFoldersInUse].setNOCC1(arrCount);
				typeOfInput = 3;
				arrCount=0;
			}
			else if (input.startsWith("Trials")){
				CRIcount++;
				input = configReadIn[CRIcount];
				numberOfTrials = Integer.parseInt(input);
				System.out.print(numberOfTrials);
				
			}
			else if (typeOfInput == 1 &&input.startsWith("image") && testForExistance.exists()){
				System.out.print ("Arrcount is " +arrCount+" and CRI is " +configReadIn[CRIcount]+"\n");
				imageObjects[numberOfFoldersInUse].insertNoiseCond(arrCount, configReadIn[CRIcount]);
				arrCount++;
			}
			else if (typeOfInput == 2 && input.startsWith("image") && testForExistance.exists()){
				System.out.print ("Arrcount is " +arrCount+" and CRI is " +configReadIn[CRIcount]+"\n");
				imageObjects[numberOfFoldersInUse].insertCompCond1(arrCount, configReadIn[CRIcount]);
				arrCount++;
			}			
			else if (typeOfInput == 3 && input.startsWith("image") && testForExistance.exists()){
				System.out.print ("Arrcount is " +arrCount+" and CRI is " +configReadIn[CRIcount]+"\n");
				imageObjects[numberOfFoldersInUse].insertCompCond2(arrCount, configReadIn[CRIcount]);
				arrCount++;
			}
			else if (input.startsWith("image") && !testForExistance.exists()){
				pw.println("Error, file " + configReadIn[CRIcount] + " does not exist.");
				wasError = true;
			}
			CRIcount ++;
			input = configReadIn[CRIcount];
			testForExistance = new File(input);
		}
		imageObjects[numberOfFoldersInUse].setNOCC2(arrCount);
		numberOfFoldersInUse += 1;
		pw.close();
		if (wasError==true){
			System.exit(0);
		}
	}

}
