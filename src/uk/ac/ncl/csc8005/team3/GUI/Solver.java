package sokoban_new;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

public class Solver extends JFrame {

	private JPanel contentPane;

	/**
	 * Launch the application.
	 */
	

	/**
	 * Create the frame.
	 */
	public Solver() {
		
	}
		public void start(){
			initialize();
		}
		
		private void initialize() {
		
			setBounds(100, 100, 450, 300);
			contentPane = new JPanel();
			contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
			contentPane.setLayout(new BorderLayout(0, 0));
			setContentPane(contentPane);
			setVisible(true);

			
		
	}

}
