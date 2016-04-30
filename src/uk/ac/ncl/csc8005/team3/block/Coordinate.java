package uk.ac.ncl.csc8005.team3.block;
public class Coordinate implements Comparable<Coordinate> {
	
	private int xPosition;
	private int yPosition;
	
	public Coordinate (int x, int y)
	{
		this.xPosition= x;
		this.yPosition= y;
	}
	
	public Coordinate(){
		xPosition = 0;
		yPosition = 0;
	}
	
	public void setxPosition(int x)
	{
		if( x >= 0)
		{
			this.xPosition= x;
		}

	}
	
	public void setyPosition(int y)
	{
		if( y >=0 )
		{
			this.yPosition= y;
		}
	}
	
	public int getX()
	{
		return xPosition;
	}
	
	public int getY()
	{
		return yPosition;
	}
	@Override
	/**
	 * @author RobinLew
	 */
	public int compareTo(Coordinate o) {
		int result = 0;
		if(this.xPosition != o.getX())result=this.xPosition-o.getX();
		else{
			result = this.yPosition-o.yPosition;
		}
		return result;
	}
	
	@Override
	public int hashCode() {
		return xPosition*1000 + yPosition;
	}
	
	// equals method used for contains()
	@Override
	public boolean equals(Object object){
		if (object == null) return false;
	    if (object == this) return true;
	    if (this.getClass() != object.getClass()) return false;
		Coordinate c = (Coordinate) object;
		if(this.hashCode()== c.hashCode()) return true;
	    return ((this.xPosition == c.xPosition) && (this.yPosition == c.yPosition));
	}
	
	public String toString(){
		return "[X:"+xPosition+"]\n[Y:"+yPosition+"]\n";
	}
}
