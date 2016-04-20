/**
 * 
 */
package sokoban;

/**
 * @author yqjapple
 *
 */


public abstract class Move extends GameObject {
	
	
	public static final int DEFAULT_SPEED =  (int) 3.0f;
	
	protected int speed;
	protected int xMove, yMove;

	public Move(int x, int y) {
		super(x, y);
		
		speed = DEFAULT_SPEED;
		xMove = 0;
		yMove = 0;
	}
	
	public void move(){
		x += xMove;
		y += yMove;
	}
	
	//GETTERS SETTERS

	public int getxMove() {
		return xMove;
	}

	public void setxMove(int xMove) {
		this.xMove = xMove;
	}

	public int getyMove() {
		return yMove;
	}

	public void setyMove(int yMove) {
		this.yMove = yMove;
	}


	public int getSpeed() {
		return speed;
	}

	public void setSpeed(int speed) {
		this.speed = speed;
	}
	

}