/**
 * Solver class shows the solution to the level which the user is playing. 
 * @author: Qijing Yu
 */ 

package uk.ac.ncl.csc8005.team3.GUI;

import java.awt.BorderLayout;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

import uk.ac.ncl.csc8005.team3.algorithm.SokobanSolver;
import uk.ac.ncl.csc8005.team3.coreEngine.Board;
import uk.ac.ncl.csc8005.team3.coreEngine.IOMethods;

public class Solver extends JFrame {

	private JPanel contentPane;
	private Board board;
	
	public Solver(Board board) {
		this.board = board;
		
	}
		public void start(){
			initialize();
		}
		
		private void initialize() {
		
			setBounds(100, 100, 450, 300);
			
			contentPane = new JPanel();		

			SokobanSolver SS = new SokobanSolver();
			SS.loadFile(board, 'm');
			
			JLabel label1 = new JLabel("The solution for this level is:");
			label1.setSize(50, 150);
			JLabel label2 = new JLabel(SS.solve('a'));
			label2.setBounds(50,50,225,150);
			getContentPane().add(label1, BorderLayout.NORTH);
			getContentPane().add(label2, BorderLayout.CENTER);
			pack();
			setVisible(true);
	}

}
