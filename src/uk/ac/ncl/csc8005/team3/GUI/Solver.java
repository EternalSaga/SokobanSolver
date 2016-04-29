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

public class Solver extends JFrame {

	private JPanel contentPane;
	private Board board;
	

	/**
	 * Create the frame.
	 */
	public Solver(Board board) {
		this.board = board;
	}
		public void start(){
			initialize();
		}
		
		private void initialize() {
		
			setBounds(100, 100, 450, 300);
			
			contentPane = new JPanel();			
			Heuristics h = new Heuristics(board.getGoals(), 'm');
			AStarSearch as = new AStarSearch(h);
			JLabel label1 = new JLabel("The solution for this level is:");
			label1.setSize(50, 150);
			JLabel label2 = new JLabel(as.prioritySearch(board));
			label2.setBounds(50,50,225,150);
			getContentPane().add(label1, BorderLayout.NORTH);
			getContentPane().add(label2, BorderLayout.CENTER);
			pack();
			setVisible(true);
	}

}
