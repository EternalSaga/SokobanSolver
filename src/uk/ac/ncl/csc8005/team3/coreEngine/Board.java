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


	/*
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
	 * set the map marking each coordinate with a block attribute
	 * set the walls and the goals and the blocked at the same time
	 * @author Marie
	 */
	public void addToMap(Coordinate thisCoordinate, BlockAttribute thisBlockAttribute) {

		tMap.put(thisCoordinate, thisBlockAttribute);
		if (thisBlockAttribute == BlockAttribute.PLAYER
				|| thisBlockAttribute == BlockAttribute.PLAYERONGOAL) {
			playerCoordinate = thisCoordinate;

		}
	}

	public void setNumberOfGoals()
	{
		int count= Collections.frequency(tMap.values(), BlockAttribute.GOAL);
		count += Collections.frequency(tMap.values(), BlockAttribute.BOXONGOAL);
		count += Collections.frequency(tMap.values(), BlockAttribute.PLAYERONGOAL);
		this.numberOfGoals= count;
	}


	public int getNumberOfBoxesOnGoal()
	{
		return this.numberBoxesOnGoal;
	}

	public void incrementNumBoxesOnGoal()
	{
		this.numberBoxesOnGoal++;
	}

	public void decrementNumBoxesOnGoal()
	{
		this.numberBoxesOnGoal--;
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
	/* 
	 * checks number of rows
	 */
	public void setNumOfRows()
	{
		numOfRows= rows.size();
	}
	/*
	 * method that gets the number of columns (max x value is number of columns
	 * - 1)
	 */
	public int getWidth()
	{
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
		setNumOfRows();
		int x = 0;
		int y = this.numOfRows - 1;
		//int xChecker= this.numOfColumns -1;
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
				//			if(x !> xChecker)
				//			{
				//				addToMap(co, BlockAttribute.FLOOR);
				//				x++;
				//			}
				x = 0;
				y--;
			}
		}
	}

	public String getRow(int y) {
		Iterator it = tMap.entrySet().iterator();
		int width = rows.get(rows.size()-y-1).length();
		char[] currentRow = new char[width];
		while(it.hasNext()){
			Map.Entry ent = (Map.Entry) it.next();
			if(((Coordinate)ent.getKey()).getY()==y){
				switch ((BlockAttribute)ent.getValue()) {
				case PLAYER:
					currentRow[((Coordinate)ent.getKey()).getX()]='@';
					break;
				case PLAYERONGOAL:
					currentRow[((Coordinate)ent.getKey()).getX()]='+';
					break;
				case BOX:
					currentRow[((Coordinate)ent.getKey()).getX()]='$';
					break;
				case BOXONGOAL:
					currentRow[((Coordinate)ent.getKey()).getX()]='*';
					break;
				case GOAL:
					currentRow[((Coordinate)ent.getKey()).getX()]='.';
					break;
				case WALL:
					currentRow[((Coordinate)ent.getKey()).getX()]='#';
					break;
				case FLOOR:
					currentRow[((Coordinate)ent.getKey()).getX()]=' ';
					break;

				default:
					break;
				}
			}
		}
		return new String(currentRow);
	}
	public Map<Coordinate, BlockAttribute> getTreeMap(){
		return tMap;
	}



}
