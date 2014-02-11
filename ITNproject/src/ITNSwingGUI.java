import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

@SuppressWarnings("serial")
public class ITNSwingGUI extends JFrame implements ActionListener {
	JPanel mypanel;
	JButton mybutton;
	JLabel mylabel;
	
	public ITNSwingGUI(){
		mypanel = new JPanel();
		
		mybutton = new JButton("OK");
		mybutton.addActionListener(this);
		
		mylabel = new JLabel();
		
		mypanel.add(mybutton);
		mypanel.add(mylabel);
		this.add(mypanel);
	}


public static void main(String[] args){
	ITNSwingGUI first = new ITNSwingGUI();
	first.setTitle("First try");
	first.setSize(300,200);
	first.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	first.setVisible(true);
}
@Override
public void actionPerformed(ActionEvent event){
	if(event.getSource()==mybutton)
	{
		if(mylabel.getText() == "My button clicked."){
			mylabel.setText("Yes, it's still my button.");
		}
		else{
			mylabel.setText("My button clicked.");
		}
		
	}
}
}
