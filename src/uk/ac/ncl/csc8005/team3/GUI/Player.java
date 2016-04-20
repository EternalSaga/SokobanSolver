/**
 * 
 */
package sokoban;

/**
 * @author yqjapple
 *
 */
import java.awt.Graphics;
import java.awt.image.BufferedImage;

public class Player extends Move {

	private Game game;
	private Animation playerDown,playerUp,playerLeft,playerRight;
	
	public Player(Game game, int x, int y) {
		super(x, y);
		this.game = game;
		playerDown = new Animation(500,GamePropeties.player_down);
		playerUp = new Animation(500,GamePropeties.player_up);
		playerLeft = new Animation(500,GamePropeties.player_left);
		playerRight = new Animation(500,GamePropeties.player_right);
	}

	@Override
	public void update() {
		playerDown.update();
		playerUp.update();
		playerLeft.update();
		playerRight.update();
		getInput();
		move();
	}
	
	private void getInput(){
		xMove = 0;
		yMove = 0;
		
		if(game.getKeyManager().up)
			yMove = -speed;
		if(game.getKeyManager().down)
			yMove = speed;
		if(game.getKeyManager().left)
			xMove = -speed;
		if(game.getKeyManager().right)
			xMove = speed;
	}

	@Override
	public void render(Graphics g) {
		g.drawImage(getCurrentAnimation(), x,  y, null);
	}
	
	private BufferedImage getCurrentAnimation(){
		if(xMove < 0){
			return playerLeft.getCurrentFrame();
		}else if(xMove > 0){
			return playerRight.getCurrentFrame();
		}else if(yMove < 0){
			return playerUp.getCurrentFrame();
		}else{
			return playerDown.getCurrentFrame();
		}
	}

}