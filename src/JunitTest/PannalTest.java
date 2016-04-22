package JunitTest;

import java.awt.Event;
import java.awt.EventQueue;

import uk.ac.ncl.csc8005.team3.GUI.BoardPanel;
import uk.ac.ncl.csc8005.team3.GUI.GameWindow;
import uk.ac.ncl.csc8005.team3.coreEngine.Board;
import uk.ac.ncl.csc8005.team3.coreEngine.IOMethods;

public class PannalTest {
	public static void main(String[] args) {
		IOMethods io = new IOMethods();
		Board level1 = io.loadBoardFromFile("resources/level1.txt");
		BoardPanel boardPanel1 = new BoardPanel(level1);
		
		EventQueue.invokeLater(new Runnable() {
			
			@Override
			public void run() {
				GameWindow GW = new GameWindow();
				
			}
		});
	}
}
