import java.awt.Component;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.ImageIcon;
import javax.swing.JButton;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;


public class MainScreen {

	private JFrame frame;

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
		
		JButton btnKitty = new JButton("Kitty1");
		btnKitty.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				showKittenNoise();
			}
		});
		btnKitty.setBounds(235, 11, 89, 23);
		frame.getContentPane().add(btnKitty);
		
		JButton btnKitties = new JButton("Kitties 2");
		btnKitties.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				showKittenComparison();
			}
		});
		btnKitties.setBounds(449, 11, 89, 23);
		frame.getContentPane().add(btnKitties);
		
		JButton btnKeyboard = new JButton("Keyboard");
		btnKeyboard.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				showKeyboard();
			}
		});
		btnKeyboard.setBounds(40, 11, 89, 23);
		frame.getContentPane().add(btnKeyboard);
		
		JLabel LabelKeyboard = new JLabel("Hand Placement");
		LabelKeyboard.setIcon(new ImageIcon("images/keyboardFingerPlacement.jpg"));
		LabelKeyboard.setBounds(40, 72, 504, 426);
		frame.getContentPane().add(LabelKeyboard);
		
		JLabel LabelCompKittenNoise = new JLabel("Yellow Kitten under noise");
		LabelCompKittenNoise.setIcon(new ImageIcon("images/YellowKitten.jpg"));
		LabelCompKittenNoise.setBounds(40, 58, 504, 426);
		LabelCompKittenNoise.setVisible(false);
		frame.getContentPane().add(LabelCompKittenNoise);
		
		JLabel LabelCompKitten1 = new JLabel("Gray Kitten");
		LabelCompKitten1.setIcon(new ImageIcon("images/GrayKitten.jpg"));
		LabelCompKitten1.setBounds(40, 196, 256, 333);
		LabelCompKitten1.setVisible(false);
		frame.getContentPane().add(LabelCompKitten1);
		
		JLabel LabelCompKitten2 = new JLabel("Other Yellow Kitten");
		LabelCompKitten2.setIcon(new ImageIcon("images/YellowKittyTwo.jpg"));
		LabelCompKitten2.setBounds(306, 196, 256, 333);
		LabelCompKitten2.setVisible(false);
		frame.getContentPane().add(LabelCompKitten2);
	}
	
	//http://forums.codeguru.com/showthread.php?39273-Display-Hide-Swing-Components used for reference for all 3 show commands.
	private void showKeyboard(){ //Show Tutorial Image I
		Component[] components = frame.getContentPane().getComponents();
		components[3].setVisible(true);
		components[4].setVisible(false);
		components[5].setVisible(false);
		components[6].setVisible(false);
	}
	
	private void showKittenNoise(){ //Show Tutorial Image II
		Component[] components = frame.getContentPane().getComponents();
		components[3].setVisible(false);
		components[4].setVisible(true);
		components[5].setVisible(false);
		components[6].setVisible(false);
	}
	
	private void showKittenComparison(){ //Show Tutorial Image III
		Component[] components = frame.getContentPane().getComponents();
		components[3].setVisible(false);
		components[4].setVisible(false);
		components[5].setVisible(true);
		components[6].setVisible(true);
	}
}

