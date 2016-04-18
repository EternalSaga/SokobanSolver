package JunitTest;

import static org.junit.Assert.*;

import org.junit.Test;

import uk.ac.ncl.csc8005.team3.coreEngine.Board;
import uk.ac.ncl.csc8005.team3.coreEngine.IOMethods;

public class BoardTest {

	@Test
	public void test() {
		IOMethods io = new IOMethods();
		Board board;
		board = io.loadBoardFromFile("C:\\Users\\RobinLew\\OneDrive\\JAVA&Android_Workplace\\SokobanSolver\\bin\\JunitTest\\level1.txt");
		assertEquals("player",board.getBlockAttribute(1, 1).getAttribute());
	}

}
