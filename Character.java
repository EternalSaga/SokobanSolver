
public class Character {
	private int size;
	private int xPosition;
	private int yPosition;
	private boolean isVisible;
	
	public Character(int xPosition, int yPosition)
	{
		this.size= 10;
		this.xPosition= xPosition;
		this.yPosition= yPosition;
		this.isVisible= true;
	}

	public int getSize() {
		return size;
	}

	public void setSize(int size) {
		this.size = size;
	}

	public int getxPosition() {
		return xPosition;
	}

	public void setxPosition(int xPosition) {
		this.xPosition = xPosition;
	}

	public int getyPosition() {
		return yPosition;
	}

	public void setyPosition(int yPosition) {
		this.yPosition = yPosition;
	}
	
	public boolean getisVisible()
	{
		return isVisible;
	}
	
	public void setIsVisible(boolean visibility)
	{
		this.isVisible= visibility;
	}

}
