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
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.concurrent.TimeUnit;


public class MainScreen implements KeyListener, ActionListener {//extends ImageHandler

	private JFrame frame;
	private int TutorialPosition = 0;
	private int TrialCounter = 0;
	private int NumberOfFolders = 1;
	private char[] ChoiceArray;
	private Boolean noiseOrComp = true; 
	private ImageHandler[] imageObjects;
	private String[] configReadIn;
	private Timer tickover; //multiple use timer, I think.

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
		//this creates things that need to be created.
		imageObjects = new ImageHandler[NumberOfFolders];
		for (int createArray = 0; createArray < NumberOfFolders; createArray++){// this for loop initilizes all of the objects within the array.
			imageObjects[createArray] = new ImageHandler();
		}
		ChoiceArray = new char[50];
		configReadIn = new String[1000];
		
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
		
		JLabel labelInstruction = new JLabel("Please place you hands on the keyboard as shown below.");
		labelInstruction.setFont(new Font("Times New Roman", Font.BOLD, 14));
		labelInstruction.setHorizontalAlignment(SwingConstants.CENTER);
		labelInstruction.setBounds(10, 11, 564, 30);
		frame.getContentPane().add(labelInstruction);
		
		JLabel lableNextKey = new JLabel("Once you have done so, Press the space bar.");
		lableNextKey.setFont(new Font("Times New Roman", Font.BOLD, 14));
		lableNextKey.setHorizontalAlignment(SwingConstants.CENTER);
		lableNextKey.setBounds(10, 521, 564, 30);
		frame.getContentPane().add(lableNextKey);
		
		JLabel lblNoiseconditionimg = new JLabel("NoiseConditionImg");
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
	
	
	//http://forums.codeguru.com/showthread.php?39273-Display-Hide-Swing-Components used for reference for these show commands.
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
	
	private void hideAll(){ //hide all of the tutorial images.
		Component[] components = frame.getContentPane().getComponents();
		components[0].setVisible(false);
		components[1].setVisible(false);
		components[2].setVisible(false);
		components[3].setVisible(false);
		components[4].setVisible(false);
		components[6].setVisible(false);
	}
	private void swapComparisonForNoise(){
		Component[] components = frame.getContentPane().getComponents();
		if (noiseOrComp == true){ // this shows the noise image after getting a random one.
			System.out.print("showing noise");
			try {
				((JLabel) components[7]).setIcon( new ImageIcon(ImageIO.read( new File(imageObjects[0].pickRandomNoiseCond()) ) ) );
			} 
			catch (IOException e) {
				e.printStackTrace();
			}
			((JLabel) components[7]).setVisible(true);
			((JLabel) components[8]).setVisible(false);
			((JLabel) components[9]).setVisible(false);
			noiseOrComp = false;
		}
		
		
		else if (noiseOrComp == false) { // this shows the comparison images after getting random ones.
System.out.print("showing comp.");
			String A1 = imageObjects[0].pickRandomCompCond1();
			String B1 = imageObjects[0].pickRandomCompCond2();
System.out.print("Random1 is " +A1 + " and Random 2 is " +B1 + "\n");
			if ((int)(Math.random()*2)== 1){
				((JLabel) components[8]).setIcon( new ImageIcon(A1) );
				((JLabel) components[9]).setIcon( new ImageIcon(B1) );
			}
			else {
				((JLabel) components[8]).setIcon( new ImageIcon(B1) );
				((JLabel) components[9]).setIcon( new ImageIcon(A1) );
			}
			((JLabel) components[7]).setVisible(false);
			((JLabel) components[8]).setVisible(true);
			((JLabel) components[9]).setVisible(true);
			noiseOrComp = true;
		}
	}
	
	
	@Override
	public void keyTyped(KeyEvent inputKey){//required to implement KeyListener
		
	}
	
	@Override
	public void keyPressed(KeyEvent inputKey) {//also required to implement KeyListener
		final Component[] components = frame.getContentPane().getComponents();//tells the event what components (objects on the screen) exist.
		
		
//The spacebar is used for the tutorials, but not in the program itself (yet).
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
				try {
					((JLabel) components[7]).setIcon( new ImageIcon(ImageIO.read( new File(imageObjects[0].pickRandomNoiseCond()) ) ) );
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
			
			//once the tutorial is completed, begin the experement.
			else if (TutorialPosition == 3){ // this is set under inputkey j
				components[7].setVisible(true);
				((JLabel) components[7]).paintImmediately(0,0,218,300);//this line is required to make the label show before the delay.
				components[5].setVisible(false);
				components[7].setVisible(false);
				tickover = new Timer(0, this);
				tickover.setInitialDelay(0);
				tickover.setRepeats(false);
				tickover.start(); 
				TutorialPosition +=1;
				
			}
			
			//this else catches extra spacebar keypresses. There is no reaction here.
			else{
				//this is for testing purposes.
			}
		}
		else if (inputKey.getKeyChar() == 'j'){
			if (TutorialPosition == 2 ){
				((JLabel) components[5]).setText("Very good. Now press the space bar to begin the experement.");
				hideAll();
				TutorialPosition+= 1;
			}
			else if (TutorialPosition == 4){//the 4 means you aren't in the tutorial anymore.
				ChoiceArray[TrialCounter] = 'j';
				System.out.print("ChoiceArray " + ChoiceArray[TrialCounter] + "\n");				
				/*String imageName = "images/YellowKitten.jpg";
				try {
					((JLabel) components[7]).setIcon( new ImageIcon(ImageIO.read( new File(imageName) ) ) );
				} catch (IOException e) {
					e.printStackTrace();
				}//http://stackoverflow.com/questions/1567445/how-to-change-icon-of-a-jlabel
				swapComparisonForNoise();*/
				TrialCounter+=1;
			}
		}
		else if (inputKey.getKeyChar() == 'f'){
			if(TutorialPosition == 2){
				((JLabel) components[5]).setText("That was the f key. The Gray kitten does not match the first yellow kitten.");
			}
			else if (TutorialPosition == 4){//the 4 means you aren't in the tutorial anymore.
				ChoiceArray[TrialCounter] = 'f';
				System.out.print("ChoiceArray " + ChoiceArray[TrialCounter] + "\n");
				/*String imageName = "images/GrayKitten.jpg";
				try {
					((JLabel) components[7]).setIcon( new ImageIcon(ImageIO.read( new File(imageName) ) ) );
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}//http://stackoverflow.com/questions/1567445/how-to-change-icon-of-a-jlabel
				swapComparisonForNoise();*/
				TrialCounter+=1;
			}
		}
		
	}

	@Override
	public void keyReleased(KeyEvent inputKey) {//also required to implement KeyListener
		
	}
	
	private void loadFileToArr(){ // you MUST add error catching here. this should be in imagehandler
		int configTracker = 0;
		String path = "images/config.txt";
		FileInputStream configFile = null;
		try {
			configFile = new FileInputStream(path);
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
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
		fillArrays(configTracker);
	}
	
	private void fillArrays(int arrToFill){ // this should also be in imagehandler
		String input;
		int typeOfInput = 0;
		int CRIcount = 0;//config read in count
		int arrCount = 0;
		imageObjects[0].setNONC(2);
		imageObjects[0].setNOCC(3);
		input = configReadIn[CRIcount];
		while (!(input.startsWith("End:"))){
			if(input.startsWith("Test")){
				System.out.println(input + " inside");
				typeOfInput = 1;
				arrCount=0;
			}
			else if (input.startsWith("ImageSet1")){
				typeOfInput = 2;
				arrCount=0;
			}
			else if(input.startsWith("ImageSet2")){
				typeOfInput = 3;
				arrCount=0;
			}			
			else if (typeOfInput == 1){
				imageObjects[0].insertNoiseCond(arrCount, configReadIn[CRIcount]);
				arrCount++;
			}
			else if (typeOfInput == 2){
				imageObjects[0].insertCompCond1(arrCount, configReadIn[CRIcount]);
				arrCount++;
			}			
			else if (typeOfInput == 3){
				imageObjects[0].insertCompCond2(arrCount, configReadIn[CRIcount]);
				arrCount++;
			}
			CRIcount ++;
			input = configReadIn[CRIcount];
		}
	}

	@Override
	public void actionPerformed(ActionEvent arg0) { // this is the call that the timer makes.
		int delayBetweenImages = 1000;
		swapComparisonForNoise();
		delayBetweenImages += (int)(Math.random()*50)+250;
		tickover = new Timer(delayBetweenImages, this);
		//tickover.setInitialDelay(0);
		tickover.setRepeats(false);
		tickover.start(); 
	}	
}



