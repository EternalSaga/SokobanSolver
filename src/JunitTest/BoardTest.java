package JunitTest;

import java.awt.event.KeyEvent;

//import static org.junit.Assert.*;

import org.junit.Test;

import uk.ac.ncl.csc8005.team3.coreEngine.Board;
import uk.ac.ncl.csc8005.team3.coreEngine.BoardController;
import uk.ac.ncl.csc8005.team3.coreEngine.IOMethods;


public class BoardTest {

	@Test
	public void test() {
		IOMethods io = new IOMethods();
		Board board;
		board = io.loadBoardFromFile("resources/levelCollection/level2.txt");
		System.out.println(board.getNumberOfBoxesOnGoal());
		System.out.println(board.getBlockAttribute(4, 1));
		board.setNumberOfGoals();
		System.out.println(board.getNumberOfGoals());
	}


}
