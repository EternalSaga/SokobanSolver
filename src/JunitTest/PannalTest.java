package JunitTest;


import java.awt.EventQueue;

import uk.ac.ncl.csc8005.team3.GUI.BoardPanel;
import uk.ac.ncl.csc8005.team3.GUI.FileFolder;
import uk.ac.ncl.csc8005.team3.GUI.GameWindow;
import uk.ac.ncl.csc8005.team3.coreEngine.Board;
import uk.ac.ncl.csc8005.team3.coreEngine.IOMethods;

public class PannalTest {
	public static void main(String[] args) {
		IOMethods io = new IOMethods();
	//	Board level1 = io.loadBoardFromFile("resources/levelColletction/level1.txt");

		
		EventQueue.invokeLater(new Runnable() {
			
			@Override
			public void run() {
				GameWindow GW = new GameWindow(new FileFolder("resources/"));
				GW.start();
			}
		});
	}
}
