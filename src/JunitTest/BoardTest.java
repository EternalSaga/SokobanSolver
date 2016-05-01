package JunitTest;

//import static org.junit.Assert.*;

import org.junit.Test;

import uk.ac.ncl.csc8005.team3.coreEngine.Board;
import uk.ac.ncl.csc8005.team3.coreEngine.IOMethods;

public class BoardTest {

	@Test
	public void test() {
		IOMethods io = new IOMethods();
		Board board;
		board = io.loadBoardFromFile("resources/levelCollection/forTest.txt");
		System.out.println(board.getBlockAttribute(4, 1));
	}

}
