package JunitTest;

import uk.ac.ncl.csc8005.team3.algorithm.AStarSearch;
import uk.ac.ncl.csc8005.team3.algorithm.Heuristics;
import uk.ac.ncl.csc8005.team3.block.BlockAttribute;
import uk.ac.ncl.csc8005.team3.coreEngine.Board;
import uk.ac.ncl.csc8005.team3.coreEngine.IOMethods;

public class AlgrorithemTest {
	public static void main(String[] args) {
		IOMethods io = new IOMethods();
		Board board;
		board = io.loadBoardFromFile("resources\\level1.txt");
		Heuristics h = new Heuristics(board.getGoals(), 'm');
		AStarSearch as = new AStarSearch(h);
		System.out.println(as.prioritySearch(board));
	}
}
