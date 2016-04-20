import java.util.*;



public class Board implements BoardInterface {

	private Map<Coordinate, BlockAttribute> tMap;
	private ArrayList<String> rows;
	private int numOfRows;
	private int numOfColumns;
	private Coordinate playerCoordinate;
	private int numberOfGoals;
/*
 * Constructor to set the values of map size, which is initially empty.
 */
	public Board ()
	{
		rows= new ArrayList<String>();
		this.numOfRows = rows.size();
		this.numOfColumns= length();
		playerCoordinate= new Coordinate(0,0);
		numberOfGoals= 0;
		tMap= new TreeMap<Coordinate, BlockAttribute>();
	}
/*
 * set the map marking each coordinate with a block attribute
 */
	public void addToMap(Coordinate thisCoordinate, BlockAttribute thisBlockAttribute)
	{
		
				tMap.put(thisCoordinate, thisBlockAttribute);	
				if( thisBlockAttribute == BlockAttribute.PLAYER)
				{
					playerCoordinate= thisCoordinate;
				}
				if(thisBlockAttribute == BlockAttribute.GOAL)
				{
					this.numberOfGoals++;
				}
	}
	
	/*
	 *@return Players coordinate on the board 
	 */
	public Coordinate getPlayerPosition()
	{
		return this.PlayerCoordinate;
	}
	
	/** 
	 * @return number of goals
	 */
	public int getNumberOfGoals()
	{
	return this.numberOfGoals;	
	}
	}
	/*
	 * clears the map, method may need to be called before new level
	 */
	public void clearMap()
	{
		tMap.clear();
	}
	
	/*
	 * returns the block attribute present at a particular coordinate
	 */
	public BlockAttribute getBlockAttribute(int x, int y)
	{
		Coordinate coord= new Coordinate(x, y);
		BlockAttribute thisBlockAttribute= tMap.get(coord);
		return thisBlockAttribute;
	}
	/*
	 * add new row to the map
	 * @param string of characters that each correspond to a block attribute
	 * the number of rows is increased by one which stands for the yValues
	 * the column length is set which corresponds to the xValues
	 */
	   public void addRow(String row)
	   {
        rows.add(row);
        if(row.length() > numOfColumns)
{ 
        	this.numOfColumns= length(); 
        	this.numOfRows ++;
}
    }
		/*
		 * method that gets the number of columns (max x value is number of columns - 1)	
		 */
	   public int getWidth()
	   {
		   return numOfColumns;
	   }
	   
	   /*
	    * method that gets the number of rows (max y value is number of rows- 1)	
	    */
	   
	   public int getNumOfRows()
	   {
		   return this.numOfRows;
	   }
	   
	   /*
	    * finds the max length in order to calculate the max y coordinate(dont know if this is correct) 
	    */
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
	   /*
	    * method to convert each symbol to a block attribute
	    */
	   public BlockAttribute setEnum(char ch)
	   {
		   if(ch == '#') return  BlockAttribute.WALL;
	        if(ch == '@') return  BlockAttribute.PLAYER;
	        if(ch == '$') return  BlockAttribute.BOX;
	        if(ch == '.') return  BlockAttribute.GOAL;
	        if(ch == '*') return  BlockAttribute.BOXONGOAL;
	        if(ch == '+') return  BlockAttribute.PLAYERONGOAL; 
	        return BlockAttribute.EMPTY;
	   }
	   /*
	    * method to set each coordinate and block attribute on to the treemap
	    */
	   public void setCell()
	   {
		   length();
		   int x= 0;
		   int y= rows.size() - 1;
		   for(String thisString : rows)
		   {
			   for (int i=0; i <numOfColumns; i++)
			   {
				   char ch= thisString.charAt(i);
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