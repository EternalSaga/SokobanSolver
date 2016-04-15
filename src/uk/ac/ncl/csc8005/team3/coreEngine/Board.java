package uk.ac.ncl.csc8005.team3.coreEngine;
import uk.ac.ncl.csc8005.team3.block.BlockAttribute;
import uk.ac.ncl.csc8005.team3.block.Coordinate;

import java.util.ArrayList;
import java.util.TreeMap;
public class Board {
	private TreeMap<Coordinate, BlockAttribute> tMap;
	private ArrayList<String> rows;
	private int numOfRows;
	private int numOfColumns;
	

	public Board ()
	{
		rows= new ArrayList<String>();
		this.numOfRows = rows.size();
		this.numOfColumns= length();
		tMap= new TreeMap<Coordinate, BlockAttribute>();
		thisLevel = 1;
	}

	public void addToMap(Coordinate thisCoordinate, BlockAttribute thisBlockAttribute)
	{
		
				tMap.put(thisCoordinate, bA);	
	}
	
	public void clearMap()
	{
		tMap.clear();
	}
	
	public void getEntrySet()
	{
		 Set mapSet=tMap.entrySet();
		 System.out.println (mapSet);
	}
	
	public BlockAttribute getBlockAttribute(int x, int y)
	{
		Coordinate coord= new Coordinate(x, y);
		BlockAttribute thisBlockAttribute= tree.get(coord);
		return thisBlockAttribute;
	}
	
	   public void addRow(String row)
	   {
        rows.add(row);
        if(row.length() > width)
{ 
        	this.numOfColumns= length(); 
        	this.numOfRows ++;
}
    }
			
	   public int getWidth()
	   {
		   return numOfColumns;
	   }
	   
	   public int getNumOfRows()
	   {
		   return this.numOfRows;
	   }
	   
	   public int length()
	   {
		   int maxlength= 0;
		   for(String thisString: rows)
		   {
			   if(thisString.length() > maxlength)
			   {
				   maxlength= thisString.length();
			   }
		   }
		   return maxlength;
	   }
	   
	   public BlockAttribute setEnum(char ch)
	   {
		   if(ch == '#') return  BlockAttribute.WALL;
	        if(ch == '@') return  BlockAttribute.PLAYER;
	        if(ch == '$') return  BlockAttribute.BOX;
	        if(ch == '.') return  BlockAttribute.GOAL;
	        if(ch == '*') return  BlockAttribute.BOXONGOAL;
	        if(ch == '+') return  BlockAttribute.PLAYERONGOAL;        
	        return BlockAttribute.FLOOR;	
	   }
	   
	   public void setCell()
	   {
		   length();
		   int x= 0;
		   int y= rows.size() - 1;
		   for(String thisString : rows)
		   {
			   for (int i=0; i <numOfColumns; i++)
			   {
				   char ch= thisString.charAt(0);
				   BlockAttribute bA= setEnum(ch);
				   Coordinate co= new Coordinate(x,y);
				   addToMap(co, bA);
				   x++;
			   }
			   x=0;
			   y--;
		   }
	   }
}
