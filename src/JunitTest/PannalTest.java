package JunitTest;

import uk.ac.ncl.csc8005.team3.GUI.BoardPanel;
import uk.ac.ncl.csc8005.team3.coreEngine.Board;
import uk.ac.ncl.csc8005.team3.coreEngine.IOMethods;

public class PannalTest {
	public static void main(String[] args) {
		IOMethods io = new IOMethods();
		Board level1 = io.loadBoardFromFile("resources/level1.txt");
		BoardPanel boardPanel1 = new BoardPanel(level1);
	}
}
