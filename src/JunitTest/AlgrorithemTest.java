package JunitTest;


import uk.ac.ncl.csc8005.team3.algorithm.Heuristics;
import uk.ac.ncl.csc8005.team3.algorithm.SokobanSolver;
import uk.ac.ncl.csc8005.team3.block.BlockAttribute;
import uk.ac.ncl.csc8005.team3.coreEngine.Board;
import uk.ac.ncl.csc8005.team3.coreEngine.IOMethods;

public class AlgrorithemTest {
	public static void main(String[] args) {
		IOMethods io = new IOMethods();
		Board board;
		board = io.loadBoardFromFile("resources/levelCollection/level1.txt");
		SokobanSolver SS = new SokobanSolver();
		SS.loadFile(board, 'm');
		System.out.println(SS.solve('b'));
	}
}
