package uk.ac.ncl.csc8005.team3.block;
public class Coordinate implements Comparable<Coordinate> {
	
	private int xPosition;
	private int yPosition;
	
	public Coordinate (int x, int y)
	{
		this.xPosition= x;
		this.yPosition= y;
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
	
	public int getxPosition()
	{
		return xPosition;
	}
	
	public int getyPosition()
	{
		return yPosition;
	}
	@Override
	/**
	 * @author RobinLew
	 */
	public int compareTo(Coordinate o) {
		int result = 0;
		if(this.xPosition != o.getxPosition())result=this.xPosition-o.getxPosition();
		else{
			result = this.yPosition-o.yPosition;
		}
		return result;
	}
}
