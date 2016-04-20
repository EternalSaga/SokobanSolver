/**
 * 
 */
package sokoban;

import java.awt.image.BufferedImage;


/**
 * @author yqjapple
 *
 */
import java.awt.image.BufferedImage;

public class GamePropeties {
	
	private static BlockAttribute block;
	
	private static final int width = 60, height = 60;
	
	public static BufferedImage wall, floor, goal, box, boxongoal, playerongoal;
	public static BufferedImage[] player_down,player_up,player_left,player_right;
	
	public static void init(){
		
		player_down = new BufferedImage[2];
		player_up = new BufferedImage[2];
		player_left = new BufferedImage[2];
		player_right = new BufferedImage[2];
		
		player_down[0] = ImageLoader.loadImage("/background/player_down1.png");
		player_down[1] = ImageLoader.loadImage("/background/player_down2.png");
		player_up[0] = ImageLoader.loadImage("/background/player_up1.png");
		player_up[1] = ImageLoader.loadImage("/background/player_up2.png");
		player_right[0]= ImageLoader.loadImage("/background/player_right1.png");
		player_right[1]= ImageLoader.loadImage("/background/player_right2.png");
		player_left[0] = ImageLoader.loadImage("/background/player_left1.png");
		player_left[1] = ImageLoader.loadImage("/background/player_left2.png");
		
		wall = ImageLoader.loadImage(block.WALL.getPath());
		floor = ImageLoader.loadImage(block.FLOOR.getPath());
		goal = ImageLoader.loadImage(block.GOAL.getPath());
		box = ImageLoader.loadImage(block.BOX.getPath());
		boxongoal = ImageLoader.loadImage(block.BOXONGOAL.getPath());
		playerongoal = ImageLoader.loadImage(block.PLAYERONGOAL.getPath());
		
		
		
		
	}
	
}