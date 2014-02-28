import java.awt.Component;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.Timer;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.SwingConstants;

import java.awt.Font;


public class MainScreen implements KeyListener {

	private JFrame frame;
	private int TutorialPosition = 0;
	private int TimerCounter = 0;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					MainScreen window = new MainScreen();
					window.frame.setVisible(true);
				} catch (Exception e) {
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
		frame = new JFrame();
		frame.setBounds(100, 100, 600, 600);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		JLabel LabelKeyboard = new JLabel(" ");
		LabelKeyboard.setIcon(new ImageIcon("images/keyboardFingerPlacement.jpg"));
		LabelKeyboard.setBounds(40, 72, 504, 426);
		frame.getContentPane().add(LabelKeyboard);
		
		JLabel LabelCompKittenNoise = new JLabel(" ");
		LabelCompKittenNoise.setIcon(new ImageIcon("images/YellowKitten.jpg"));
		LabelCompKittenNoise.setBounds(40, 58, 504, 426);
		LabelCompKittenNoise.setVisible(false);
		frame.getContentPane().add(LabelCompKittenNoise);
		
		JLabel LabelCompKitten1 = new JLabel(" ");
		LabelCompKitten1.setIcon(new ImageIcon("images/GrayKitten.jpg"));
		LabelCompKitten1.setBounds(40, 151, 256, 333);
		LabelCompKitten1.setVisible(false);
		frame.getContentPane().add(LabelCompKitten1);
		
		JLabel LabelCompKitten2 = new JLabel(" ");
		LabelCompKitten2.setIcon(new ImageIcon("images/YellowKittyTwo.jpg"));
		LabelCompKitten2.setBounds(306, 151, 256, 333);
		LabelCompKitten2.setVisible(false);
		frame.getContentPane().add(LabelCompKitten2);
		
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
		
		JLabel labelStartTimer = new JLabel("1");
		labelStartTimer.setFont(new Font("Times New Roman", Font.BOLD, 15));
		labelStartTimer.setHorizontalAlignment(SwingConstants.CENTER);
		labelStartTimer.setBounds(250, 264, 92, 30);
		labelStartTimer.setVisible(false);
		
		frame.getContentPane().add(labelStartTimer);
		frame.addKeyListener(this);
	}
	
	//http://forums.codeguru.com/showthread.php?39273-Display-Hide-Swing-Components used for reference for these show commands.
	private void showKittenNoise(){ //Show Tutorial Image II
		Component[] components = frame.getContentPane().getComponents();
		components[0].setVisible(false);
		components[1].setVisible(true);
		components[2].setVisible(false);
		components[3].setVisible(false);
	}
	
	private void showKittenComparison(){ //Show Tutorial Image III
		Component[] components = frame.getContentPane().getComponents();
		components[0].setVisible(false);
		components[1].setVisible(false);
		components[2].setVisible(true);
		components[3].setVisible(true);
	}
	
	private void hideAll(){ //hide all of the tutorial images.
		Component[] components = frame.getContentPane().getComponents();
		components[0].setVisible(false);
		components[1].setVisible(false);
		components[2].setVisible(false);
		components[3].setVisible(false);
		components[5].setVisible(false);
	}
	
	
	@Override
	public void keyTyped(KeyEvent inputKey){//required to implement KeyListener
		
	}

	@Override
	public void keyPressed(KeyEvent inputKey) {//also required to implement KeyListener
		final Component[] components = frame.getContentPane().getComponents();//tells the event what components (objects on the screen) exist.
		if (inputKey.getKeyChar() == ' '){
			if (TutorialPosition == 0){
				((JLabel) components[4]).setText("This is an example of the image covered in noise you will see."); // you must tell the program that the component it is talking to is a JLabel or it wont work.
				((JLabel) components[5]).setText("Press space bar to continue.");
				showKittenNoise();
				TutorialPosition += 1;
			}
			
			else if (TutorialPosition == 1){
				showKittenComparison();
				((JLabel) components[4]).setText(" Press F for the left or J for the right image. chose the one closest to the initial image.");
				((JLabel) components[5]).setText("Please press the j key to continue");
				TutorialPosition += 1;
			}
			
			//once the tutorial is completed, begin the experement.
			else if (TutorialPosition == 3){ // this is set under inputkey j
				
			}
			
			//this else catches extra spacebar keypresses. There is no reaction here.
			else{
				
			}
		}
		else if (inputKey.getKeyChar() == 'j'){
			if (TutorialPosition == 2 ){
				((JLabel) components[4]).setText("Very good. Now press the space bar to begin the experement.");
				hideAll();
				TutorialPosition+= 1;
			}
		}
		else if (inputKey.getKeyChar() == 'f'){
			if(TutorialPosition == 2){
				((JLabel) components[4]).setText("That was the f key. The Gray kitten does not match the first yellow kitten.");
			}
		}
		
	}

	@Override
	public void keyReleased(KeyEvent inputKey) {//also required to implement KeyListener
		// TODO Auto-generated method stub
		
	}
}



