package JunitTest;

import java.io.FileNotFoundException;

import uk.ac.ncl.csc8005.team3.block.BlockAttribute;
import uk.ac.ncl.csc8005.team3.coreEngine.Board;
import uk.ac.ncl.csc8005.team3.coreEngine.IOMethods;

public class IOtest {
	public static void main(String[] args) throws FileNotFoundException {
		IOMethods io = new IOMethods();
		Board board;
		board = io.loadBoardFromFile("resources\\level1.txt");
		System.out.println(board.getBlockAttribute(1, 1)==BlockAttribute.FLOOR);
	}
}
