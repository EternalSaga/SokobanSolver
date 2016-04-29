package uk.ac.ncl.csc8005.team3.GUI;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import uk.ac.ncl.csc8005.team3.algorithm.AStarSearch;
import uk.ac.ncl.csc8005.team3.algorithm.Heuristics;
import uk.ac.ncl.csc8005.team3.coreEngine.Board;

public class About extends JFrame {

	private JPanel contentPane;
	private Board board;
	

	/**
	 * Create the frame.
	 */
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