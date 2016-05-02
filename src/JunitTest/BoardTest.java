package JunitTest;

import static org.junit.Assert.*;

//import static org.junit.Assert.*;

import org.junit.Test;

import uk.ac.ncl.csc8005.team3.block.BlockAttribute;
import uk.ac.ncl.csc8005.team3.block.Coordinate;
import uk.ac.ncl.csc8005.team3.coreEngine.Board;
import uk.ac.ncl.csc8005.team3.coreEngine.IOMethods;

public class BoardTest {
 private Board board;
 /**
  * Method to test the Board is being read and saved correctly within the map. 
  */
	@Test
	public void test() {
		IOMethods io = new IOMethods();
		board = io.loadBoardFromFile("resources/levelCollection/level1.txt");
		System.out.println(board.getBlockAttribute(5, 5));
	}
	
	/**
	 * Method number 2 to ensure the Board is being read and saved correctly within the map.
	 */
	@Test
	
	public void test2()
	{
	
	IOMethods io = new IOMethods();
	board = io.loadBoardFromFile("resources/levelCollection/level2.txt");
	System.out.println(board.getBlockAttribute(4, 1));
	assertEquals(board.getBlockAttribute(4, 1), BlockAttribute.FLOOR);
	System.out.println(board.getNumberOfGoals());
	assertEquals(board.getNumberOfGoals(), 2);
}

/**
 * Method to test the row count is working correctly.
 */
@Test 

public void TestRowCount()
{
	IOMethods io = new IOMethods();
	board = io.loadBoardFromFile("resources/levelCollection/level2.txt");
	assertEquals(board.getHeight(), 7);
	board.setNumOfRows();
	assertEquals(board.getHeight(), 7);
	
}

/**
 * Test to ensure the board class converts each character into the correct BlockAttribute
 */

@Test 

public void testSetEnum()
{
	char ch= '#';
	BlockAttribute thisBlockAttribute= board.setEnum(ch);
	assertEquals(BlockAttribute.WALL, thisBlockAttribute);
	char ch1= '@';
	BlockAttribute thisBlockAttribute1= board.setEnum(ch1);
	assertEquals(BlockAttribute.PLAYER, thisBlockAttribute1);
	char ch2= '$';
	BlockAttribute thisBlockAttribute2= board.setEnum(ch2);
	assertEquals(BlockAttribute.BOX, thisBlockAttribute2);
	char ch3= '.';
	BlockAttribute thisBlockAttribute3= board.setEnum(ch3);
	assertEquals(BlockAttribute.GOAL, thisBlockAttribute3);
	char ch4= '*';
	BlockAttribute thisBlockAttribute4= board.setEnum(ch4);
	assertEquals(BlockAttribute.BOXONGOAL, thisBlockAttribute4);
	char ch5= '+';
	BlockAttribute thisBlockAttribute5= board.setEnum(ch5);
	assertEquals(BlockAttribute.PLAYERONGOAL, thisBlockAttribute5);
	char ch6= ' ';
	BlockAttribute thisBlockAttribute6= board.setEnum(ch6);
	assertEquals(BlockAttribute.FLOOR, thisBlockAttribute6);
	char ch7= 'F';
	BlockAttribute thisBlockAttribute7= board.setEnum(ch7);
	assertEquals(BlockAttribute.FLOOR, thisBlockAttribute7);
}
/**
* Test to ensure that the block attributes are added to the Map correctly
*/
@Test
public void testAddToMap()
{
	int x =4;
	int y= 5;
	Coordinate thisCoordinate= new Coordinate(x,y);
	BlockAttribute thisBlockAttribute= BlockAttribute.WALL;
	board.addToMap(thisCoordinate, thisBlockAttribute);
	BlockAttribute thisBlockAttribute1= board.getBlockAttribute(x, y);
	assertEquals(thisBlockAttribute, thisBlockAttribute1);
}

}
