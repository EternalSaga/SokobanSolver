/**
 * This class is to show the information of the whole project.
 * @author Qijing Yu
 * 
 */
package uk.ac.ncl.csc8005.team3.GUI;

import java.awt.BorderLayout;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import uk.ac.ncl.csc8005.team3.coreEngine.Board;

public class About extends JFrame {

	private JPanel contentPane;
	
	public About() {
		
	}
		public void start(){
			initialize();
		}
		
		private void initialize() {
		
			setBounds(100, 100, 450, 300);
			
			contentPane = new JPanel();			
			
			JLabel label = new JLabel("<html>2015-2016 CSC8005 team3's group project</html>");
			label.setSize(50, 150);
			
			getContentPane().add(label, BorderLayout.NORTH);
			pack();
			setVisible(true);

}

}
