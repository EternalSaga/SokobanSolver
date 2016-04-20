
public class Map {
private Character[] mapCharacters;
private int maxCharacters;
private int numCharacters;
	public Map(int maxCharacters) 
	{
	this.numCharacters= 0;
		this.maxCharacters= maxCharacters;
		mapCharacters= new Character[this.maxCharacters];
	}
	
	public boolean checkSpace()
	{
		return (numCharacters < maxCharacters);
	}
	
	public void addCharacter(Character thisCharacter)
	{
				if(checkSpace() == true)
				{
					mapCharacters[numCharacters] = thisCharacter;
					numCharacters ++;
				}
		
	}
	public void changexPosition(Character ch, int direction)
	{
		for(Character thisCharacter: mapCharacters)
		{ 
			if(ch.equals(thisCharacter))
			{
				if(thisCharacter instanceof MovingCharacter)
				{
				if(direction == -10)
				{
					((MovingCharacter) thisCharacter).moveUp();
				}
				else{
					if(direction == 10)
					{
						((MovingCharacter) thisCharacter).moveDown();
					}
					else{
						break;
					}
				}
				}
			}
		}
		
	}
	
	public void changeyPosition(Character ch, int direction)
	{
		for(Character thisCharacter: mapCharacters)
		{ 
			if(ch.equals(thisCharacter))
			{
				if(thisCharacter instanceof MovingCharacter)
				{
				if(direction == -10)
				{
					((MovingCharacter) thisCharacter).moveLeft();
				}
				else{
					if(direction == 10)
					{
						((MovingCharacter) thisCharacter).moveRight();
					}
					else{
						break;
					}
				}
				}
			}
		}
		
	}
	


}
