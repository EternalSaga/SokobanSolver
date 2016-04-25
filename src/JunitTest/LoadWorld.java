/**
 * 
 */
package JunitTest;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.util.ArrayList;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;

import uk.ac.ncl.csc8005.team3.GUI.BoardPanel;
import uk.ac.ncl.csc8005.team3.coreEngine.Board;
import uk.ac.ncl.csc8005.team3.coreEngine.IOMethods;

/**
 * @author yqjapple
 *
 */

/**
 * Launch the application.
 */

public class LoadWorld {

	
		public static void main(String[] args) {
			// test boardPanel 
			IOMethods io = new IOMethods();
			Board level = io.loadBoardFromFile("res/levelCollection/level.txt");
			BoardPanel boardPanel = new BoardPanel(level);
			System.out.print(level.getBlockAttribute(2, 3));
			
		    JFrame f1 = new JFrame();
			f1.getContentPane().add(boardPanel, BorderLayout.CENTER);	
			f1.setVisible(true);
			f1.pack();
			f1.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
			
			// comment out line 28-37 and remove comment mark of line 41 - 43
			// you can then get a GameWindow test
			//FileFolder f = new FileFolder("res/levelCollection");	
	        //GameWindow w = new GameWindow(f);
		   // w.start();
		}

	

}
