package JunitTest;

import java.io.FileNotFoundException;
import java.util.Iterator;
import java.util.Map;
import java.util.TreeMap;

import uk.ac.ncl.csc8005.team3.block.BlockAttribute;
import uk.ac.ncl.csc8005.team3.block.Coordinate;
import uk.ac.ncl.csc8005.team3.coreEngine.Board;
import uk.ac.ncl.csc8005.team3.coreEngine.IOMethods;

public class IOtest {
	public static void main(String[] args) throws FileNotFoundException {
		IOMethods io = new IOMethods();
		Board board;
		board = io.loadBoardFromFile("resources/levelCollection/level1.txt");

		Map<Coordinate, BlockAttribute> map = board.getTreeMap();
		Iterator it = map.entrySet().iterator();
		int num =0;
		while(it.hasNext()){
			Map.Entry ent = (Map.Entry) it.next();
			if(ent.getValue()==BlockAttribute.PLAYER)
				num++;
		}
		System.out.println(num);
		System.out.println(board.getBlockAttribute(5, 5).toString());	
		}
}