package uk.ac.ncl.csc8005.team3.coreEngine;

import java.util.*;

import uk.ac.ncl.csc8005.team3.algorithm.State;
import uk.ac.ncl.csc8005.team3.block.*;

public class Board implements BoardInterface {

	private Map<Coordinate, BlockAttribute> tMap;
	private ArrayList<String> rows;
	private int numOfRows;
	private int numOfColumns;
	private Coordinate playerCoordinate;
	private int numberOfGoals;
	State initialState;
	HashSet<Coordinate> walls;
	HashSet<Coordinate> goals;
	HashMap<Coordinate, Coordinate> blocked;

	/*
	 * Constructor to set the values of map size, which is initially empty.
	 */
	public Board() {
		rows = new ArrayList<String>();
		this.numOfRows = 0;
		this.numOfColumns = 0;
		playerCoordinate = new Coordinate(0, 0);
		numberOfGoals = 0;
		tMap = new TreeMap<Coordinate, BlockAttribute>();
		initialState = new State();
		walls = new HashSet<>();
		goals = new HashSet<>();
	}

	/**
	 * set the map marking each coordinate with a block attribute
	 * set the walls and the goals and the blocked at the same time
	 * @author Robin Lew & Marie
	 */
	public void addToMap(Coordinate thisCoordinate, BlockAttribute thisBlockAttribute) {

		tMap.put(thisCoordinate, thisBlockAttribute);
		if (thisBlockAttribute == BlockAttribute.PLAYER
				|| thisBlockAttribute == BlockAttribute.PLAYERONGOAL) {
			playerCoordinate = thisCoordinate;
			initialState.setPlayerPosition(thisCoordinate);
		}

		if (thisBlockAttribute == BlockAttribute.GOAL) {
			this.numberOfGoals++;
			goals.add(thisCoordinate);
		}

		if(thisBlockAttribute == BlockAttribute.WALL){
			walls.add(thisCoordinate);
		}
		if(thisBlockAttribute == BlockAttribute.BOX){
			initialState.getBoxes().add(thisCoordinate);
		}
	}

	/*
	 * @return Players coordinate on the board
	 */
	public Coordinate getPlayerPosition() {
		return this.playerCoordinate;
	}

	/**
	 * @return number of goals
	 */
	public int getNumberOfGoals() {
		return this.numberOfGoals;
	}

	/*
	 * clears the map, method may need to be called before new level
	 */
	public void clearMap() {
		tMap.clear();
	}

	/*
	 * returns the block attribute present at a particular coordinate
	 */
	public BlockAttribute getBlockAttribute(int x, int y) {
		Coordinate coord = new Coordinate(x, y);
		BlockAttribute thisBlockAttribute = tMap.get(coord);
		return thisBlockAttribute;
	}

	/*
	 * add new row to the map
	 * 
	 * @param string of characters that each correspond to a block attribute the
	 * number of rows is increased by one which stands for the yValues the
	 * column length is set which corresponds to the xValues
	 */
	public void addRow(String row) {
		rows.add(row);
		this.numOfRows++;
	}

	/*
	 * method that gets the number of columns (max x value is number of columns
	 * - 1)
	 */
	public int getWidth() {
		return numOfColumns;
	}

	/*
	 * method that gets the number of rows (max y value is number of rows- 1)
	 */

	public int getHeight() {
		return this.numOfRows;
	}

	/*
	 * finds the max length in order to calculate the max y coordinate(dont know
	 * if this is correct)
	 */

	public void getLength() {
		for (String thisString : rows) {
			if (thisString.length() > this.numOfColumns) {

				this.numOfColumns= thisString.length();
			}
		}

	}


	/*
	 * method to convert each symbol to a block attribute
	 */
	public BlockAttribute setEnum(char ch) {
		if (ch == '#')
			return BlockAttribute.WALL;
		if (ch == '@')
			return BlockAttribute.PLAYER;
		if (ch == '$')
			return BlockAttribute.BOX;
		if (ch == '.')
			return BlockAttribute.GOAL;
		if (ch == '*')
			return BlockAttribute.BOXONGOAL;
		if (ch == '+')
			return BlockAttribute.PLAYERONGOAL;
		return BlockAttribute.FLOOR;
	}

	/*
	 * method to set each coordinate and block attribute on to the treemap
	 */
	public void setCell() {
		getLength();
		int x = 0;
		int y = this.numOfRows - 1;
		int xChecker= this.numOfColumns -1;
		Coordinate co = new Coordinate();
		for (String thisString : rows) 
		{
			if(thisString != null)
			{
				int stringLength = thisString.length();
				for (int i = 0; i < stringLength; i++) 
				{
					char ch = thisString.charAt(i);
					BlockAttribute bA = setEnum(ch);
					if(bA == null)
					{
						bA= BlockAttribute.FLOOR;
					}
				co = new Coordinate(x, y);
				addToMap(co, bA);
				x++;
			}
			if(x != xChecker)
			{
				addToMap(co, BlockAttribute.FLOOR);
				x++;
			}
			x = 0;
			y--;
		}
	}
}

public String getRow(int i) {
	return String.valueOf(rows.get(i));
}
public Map<Coordinate, BlockAttribute> getTreeMap(){
	return tMap;
}

/*****************For sokoban solver******************************/


/**
 * checks whether the boxes in the current state are in the goal position
 * returns true if goal is found, and returns false otherwise
 */
public boolean goalTest(State state) {
	for(Coordinate box : state.getBoxes())
		if (!goals.contains(box))
			return false;
	return true;
}

/**
 * simple check to see if any of the boxes are in a deadlock state:
 *  #  or  #####  or  ##
 * #$      #X$X#      $$
 * where X represents anything but a goal
 * @param state
 * @return
 */
public boolean deadlockTest(State state) {
	for (Coordinate box : state.boxes) {
		int row = box.getxPosition();
		int col = box.getyPosition();
		if (!setContains(goals, row, col)) {
			if (setContains(walls, row-1, col)&&setContains(walls, row, col-1))
				return true; //top & left
			if (setContains(walls, row-1, col)&&setContains(walls, row, col+1))
				return true; //top & right
			if (setContains(walls, row+1, col)&&setContains(walls, row, col-1))
				return true; //bottom & left
			if (setContains(walls, row+1, col)&&setContains(walls, row, col+1))
				return true; //bottom & right

			if (setContains(walls, row-1, col-1)&&setContains(walls, row-1, col)&&
					setContains(walls, row-1, col+1)&&setContains(walls, row, col-2)&&
					setContains(walls, row, col+2)&&(!setContains(goals, row, col-1))&&
					!setContains(goals, row, col+1))
				return true; //top & sides
			if (setContains(walls, row+1, col-1)&&setContains(walls, row+1, col)&&
					setContains(walls, row+1, col+1)&&setContains(walls, row, col-2)&&
					setContains(walls, row, col+2)&&(!setContains(goals, row, col-1))&&
					(!setContains(goals, row, col+1)))
				return true; //bottom & sides
			if (setContains(walls, row-1, col-1)&&setContains(walls, row, col-1)&&
					setContains(walls, row+1, col-1)&&setContains(walls, row-2, col)&&
					setContains(walls, row+2, col)&&(!setContains(goals, row-1, col))&&
					(!setContains(goals, row+1, col)))
				return true; //left & vertical
			if (setContains(walls, row-1, col+1)&&setContains(walls, row, col+1)&&
					setContains(walls, row+1, col+1)&&setContains(walls, row-2, col)&&
					setContains(walls, row+2, col)&&(!setContains(goals, row-1, col))&&
					(!setContains(goals, row+1, col)))
				return true; //right & top/bottom
		}
	}
	return false;
}

/**
 * checks the available actions for the player
 * @param state
 * @return arraylist of strings (u d l r)
 */
public ArrayList<String> actions(State state) {
	ArrayList<String> actionList = new ArrayList<String>();
	int row = state.player.getxPosition();
	int col = state.player.getyPosition();
	HashSet<Coordinate> boxes = state.boxes;

	//checking if moving up, right, down, left is valid
	//for each, check if next player move is a wall
	//if next move has a box, check next box move does not overlap with wall or another box
	Coordinate newPlayer = new Coordinate(row-1,col);
	Coordinate newBox = new Coordinate(row-2, col);
	if (!walls.contains(newPlayer))
		if (boxes.contains(newPlayer)&&(boxes.contains(newBox)||walls.contains(newBox)))
			;
		else
			actionList.add("u");
	newPlayer = new Coordinate(row,col+1);
	newBox = new Coordinate(row, col+2);
	if (!walls.contains(newPlayer))
		if (boxes.contains(newPlayer)&&(boxes.contains(newBox)||walls.contains(newBox)))
			;
		else
			actionList.add("r");
	newPlayer = new Coordinate(row+1,col);
	newBox = new Coordinate(row+2, col);
	if (!walls.contains(newPlayer))
		if (boxes.contains(newPlayer)&&(boxes.contains(newBox)||walls.contains(newBox)))
			;
		else
			actionList.add("d");
	newPlayer = new Coordinate(row,col-1);
	newBox = new Coordinate(row, col-2);
	if (!walls.contains(newPlayer))
		if (boxes.contains(newPlayer)&&(boxes.contains(newBox)||walls.contains(newBox)))
			;
		else
			actionList.add("l");
	return actionList;
}

private boolean setContains(HashSet<Coordinate> set, int row, int col) {
	if (set.contains(new Coordinate(row, col)))
		return true;
	return false;
}

public State getInitialState(){
	return initialState;
}
public HashSet<Coordinate> getGoals(){
	return goals;
}

}
