/**
 * LevelComplete class shows a dialog window to tell users they have completed the level
 * and ask they whether they want to continue to play the game.
 * @author: Qijing Yu
 */ 
package uk.ac.ncl.csc8005.team3.GUI;

import java.awt.BorderLayout;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;



public class LevelComplete extends JFrame {

	private JDialog d;
	private JOptionPane o;
	private FileFolder f;

	public LevelComplete(FileFolder f) {
		d = new JDialog();
		o = new JOptionPane();
		this.f = f;
	
	}
	
	public void start(){
		initialize();
	}
	
	public void initialize(){
		
        	d.setDefaultLookAndFeelDecorated(true);
		int response = o.showConfirmDialog(null, "Do you wish to continue?", "Wow! Level Completed!", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
		if (response == o.NO_OPTION){
			System.exit(0);} 
		else{
			return;
		}
		getContentPane().setLayout(new BorderLayout(0, 0));
		getContentPane().add(d,BorderLayout.CENTER);
		pack();
		setVisible(true);
		

	}
}
