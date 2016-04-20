
public class MovingCharacter extends Character {

	public MovingCharacter(int xPosition, int yPosition)
	{
		super(xPosition, yPosition);
	}
	public void moveRight()
	{
		int xPosition= getxPosition();
		xPosition += 10;
		setxPosition(xPosition);
	}
	
	public void moveLeft()
	{
		int xPosition= getxPosition();
		xPosition -= 10;
		setxPosition(xPosition);
	}
	
	public void moveUp()
	{
		int yPosition= getyPosition();
		yPosition -= 10;
		setyPosition(yPosition);
	}
	
	public void moveDown()
	{
		int yPosition= getyPosition();
		yPosition += 10;
		setyPosition(yPosition);
	}
}
