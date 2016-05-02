package uk.ac.ncl.csc8005.team3.coreEngine;

import java.util.*;


import uk.ac.ncl.csc8005.team3.block.*;

public class Board implements BoardInterface {

	private Map<Coordinate, BlockAttribute> tMap;
	private ArrayList<String> rows;
	private int numOfRows;
	private int numOfColumns;
	private Coordinate playerCoordinate;
	private int numberOfGoals;
	private int numberBoxesOnGoal;


	/**
	 * Constructor to set the values of map size, which is initially empty.
	 */
	public Board() {
		rows = new ArrayList<String>();
		this.numOfRows = 0;
		this.numOfColumns = 0;
		playerCoordinate = new Coordinate(0, 0);
		numberOfGoals = 0;
		numberBoxesOnGoal= 0;
		tMap = new TreeMap<Coordinate, BlockAttribute>();
	}

	/**
	 * Method used to to store the coordinates as a key and the corresponding blockAttribute on a map.
	 * @param thisCoordinate, the key coordinate value
	 * @Param thisBlockAttribute, the BlockAttribute added as a value to the coordinate
	 */
	public void addToMap(Coordinate thisCoordinate, BlockAttribute thisBlockAttribute) {

		tMap.put(thisCoordinate, thisBlockAttribute);
		if (thisBlockAttribute == BlockAttribute.PLAYER
				|| thisBlockAttribute == BlockAttribute.PLAYERONGOAL) {
			playerCoordinate = thisCoordinate;

		}
	}

	/**
	 * Method to count the number of goals present on the board. This method updates the 
	 * numberOfGoals variable.
	 */
	public void setNumberOfGoals()
	{
		int count= Collections.frequency(tMap.values(), BlockAttribute.GOAL);
		count += Collections.frequency(tMap.values(), BlockAttribute.BOXONGOAL);
		count += Collections.frequency(tMap.values(), BlockAttribute.PLAYERONGOAL);
		this.numberOfGoals= count;
	}

	/**
	 * Method to get the number of boxes on goal, to be used by the board control class to initiate the goal
	 * checker.
	 */
	public int getNumberOfBoxesOnGoal()
	{
		return this.numberBoxesOnGoal;
	}

	/**
	 * Method increments the number of boxes on goal by one.
	 */
	public void incrementNumBoxesOnGoal()
	{
		this.numberBoxesOnGoal++;
	}

	/**
	 * Method Decrements the number of boxes on goal by one.
	 */
	public void decrementNumBoxesOnGoal()
	{
		this.numberBoxesOnGoal--;
	}

	/**
	 * @return Players coordinate on the board.
	 */
	public Coordinate getPlayerPosition() {
		return this.playerCoordinate;
	}

	/**
	 * @return number of goals.
	 */
	public int getNumberOfGoals() {
		return this.numberOfGoals;
	}

	/**
	 * clears the map, used to reset the board.
	 */
	public void clearMap() {
		tMap.clear();
	}

	/**
	 * returns the block attribute present at a particular coordinate
	 */
	public BlockAttribute getBlockAttribute(int x, int y) {
		Coordinate coord = new Coordinate(x, y);
		BlockAttribute thisBlockAttribute = tMap.get(coord);
		return thisBlockAttribute;
	}

	/**
	 * Method to add new row to the map. Each row is inserted as a string of characters, each character
	 *  represents a particular BlockAttribute. Each string is added to an Arraylist called Rows.
	 *  The variable that stores the number of rows is incremented by one each time a new row is added.
	 *  If the String containts a '*' the number of boxes on goal variable is incremented by one. 
	 * 
	 * @param string of characters which represent a row on the map.
	 */
	public void addRow(String row) {
		rows.add(row);
		for(int i= 0; i< row.length(); i++)
		{
			char ch = row.charAt(i);
			if(ch == '*')
			{
				this.numberBoxesOnGoal++;
			}
		}
		this.numOfRows++;
	}

	/**
	 * set the number of rows.
	 */
	public void setNumOfRows()
	{
		numOfRows= rows.size();
	}

	/**
	 * method that gets the number of rows (max y value is number of rows- 1).
	 * @return number of rows.
	 */

	public int getHeight() {
		return this.numOfRows;
	}

	/**
	 * finds the max length or width of the board in order to calculate the max y coordinate
	 * @return the numberOfColumns.
	 */

	public int getLength() {
		for (String thisString : rows) {
			if (thisString.length() > this.numOfColumns) {

				this.numOfColumns= thisString.length();
			}
		}
		return this.numOfColumns;
	}


	/**
	 * Method to convert each character to its corresponding block attribute.
	 * @param the character being converted.
	 * @return the BlockAttribute the character corresponds with.
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

	/**
	 * method to set each coordinate and block attribute on to the treeMap.
	 * Method counts how many goals are present on the board.
	 */
	public void setCell() {
		getLength();
		setNumOfRows();
		int x = 0;
		int y = this.numOfRows - 1;
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
				x = 0;
				y--;
			}
		}
		setNumberOfGoals();
	}
	/**
	 * Method used to return a row in its string value.
	 * @param i, index of row
	 */
	public String getRow(int i) {
		return String.valueOf(rows.get(i));
	}

	/**
	 * Method used to return the map.
	 * @return Tmap: TreeMap.
	 */
	public Map<Coordinate, BlockAttribute> getTreeMap(){
		return tMap;
	}



}
