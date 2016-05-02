package JunitTest;

import static org.junit.Assert.*;



//import static org.junit.Assert.*;

import org.junit.Test;

import uk.ac.ncl.csc8005.team3.block.BlockAttribute;
import uk.ac.ncl.csc8005.team3.block.Coordinate;
import uk.ac.ncl.csc8005.team3.coreEngine.Board;
import uk.ac.ncl.csc8005.team3.coreEngine.IOMethods;


public class BoardTest {

	private Board board= new Board();

	/**
	 * Method to test that the board class implement a board correctly.
	 */

	@Test
	public void test() {
		IOMethods io = new IOMethods();
		board = io.loadBoardFromFile("resources/levelCollection/level2.txt");
		System.out.println(board.getNumberOfBoxesOnGoal());
		assertEquals(board.getNumberOfBoxesOnGoal(), 1);
		System.out.println(board.getBlockAttribute(4, 1));
		System.out.println(board.getNumberOfGoals());
		assertEquals(board.getNumberOfGoals(), 2);
	}
	/**
	 * Method  to test that each coordinate and blockAttribute are being stored correctly in the map
	 */
	@Test
	public void testAddToMap()
	{
		int x= 4;
		int y= 3;
		Coordinate thisCoordinate= new Coordinate(x,y);
		BlockAttribute thisBlockAttribute= BlockAttribute.WALL;
		board.addToMap(thisCoordinate, thisBlockAttribute);
		assertEquals(board.getBlockAttribute(x, y), BlockAttribute.WALL);
		assertEquals(board.getBlockAttribute(1, 2), null);
	}

	/**
	 * Test to ensure that each character are being read and converted correctly into their corresponding
	 * blockAttribute.
	 */
	@Test
	public void testSetEnum()
	{
		char ch= '#';
		BlockAttribute thisBlockAttribute= board.setEnum(ch);
		assertEquals(thisBlockAttribute, BlockAttribute.WALL);
		char ch1= '@';
		BlockAttribute thisBlockAttribute1= board.setEnum(ch1);
		assertEquals(thisBlockAttribute1, BlockAttribute.PLAYER);
		char ch2= '$';
		BlockAttribute thisBlockAttribute2= board.setEnum(ch2);
		assertEquals(thisBlockAttribute2, BlockAttribute.BOX);
		char ch3= '.';
		BlockAttribute thisBlockAttribute3= board.setEnum(ch3);
		assertEquals(thisBlockAttribute3, BlockAttribute.GOAL);
		char ch4= '+';
		BlockAttribute thisBlockAttribute4= board.setEnum(ch4);
		assertEquals(thisBlockAttribute4, BlockAttribute.PLAYERONGOAL);
		char ch5= '*';
		BlockAttribute thisBlockAttribute5= board.setEnum(ch5);
		assertEquals(thisBlockAttribute5, BlockAttribute.BOXONGOAL);
		char ch6= ' ';
		BlockAttribute thisBlockAttribute6= board.setEnum(ch6);
		assertEquals(thisBlockAttribute6, BlockAttribute.FLOOR);
		char ch7= 'F';
		BlockAttribute thisBlockAttribute7= board.setEnum(ch7);
		assertEquals(thisBlockAttribute7, BlockAttribute.FLOOR);

	}

	/**
	 * Method to ensure rows(or Strings) are being added correctly in the Arraylist.
	 */
	@Test
	public void testAddRow()
	{
		String string1 = new String("#########");
		String string2= new String("#  @. #");
		board.addRow(string1);
		board.addRow(string2);
		String string3= board.getRow(0);
		assertTrue(string1.equals(string3));

	}
}
