package uk.ac.ncl.csc8005.team3.coreEngine;
import uk.ac.ncl.csc8005.team3.block.*;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

/**
 * BoardController class
 * 
 *  - takes input from keyboard (arrow keys)
 *  - checks for collisions
 *  - moves player and box if applicable
 *  - checks for success (all boxes on targets) - still have to write this
 *  
 *  - i still have to fix bugs because at the moment it loses the goal when the player or box moves off it
 * 
 * @author Kate
 *
 */
public class BoardController implements KeyListener {
	
	private boolean[] keys;
	public boolean up, down, left, right;
	
	private Board thisBoard;
	private Coordinate playerCoordinate;
	
	public BoardController () {
		keys = new boolean[128]; //i don't know why 128
	}
	
	/**
	 * This will be called from somewhere in the core engine 
	 * - checks if any keys are pressed
	 */
	public void update() {
		up = keys[KeyEvent.VK_UP];
		down = keys[KeyEvent.VK_DOWN];
		left = keys[KeyEvent.VK_LEFT];
		right = keys[KeyEvent.VK_RIGHT];
	}
	
	/**
	 * Checks for collision between player and wall
	 * 
	 * - returns true if there is a collision
	 * - if false, calls method to check player-box collision 
	 * 
	 * @return true if there is a collision
	 */
	public boolean checkPlayerWallCollision() {
		
		boolean playerWallCollision = false;
		while (up) {
			//checks if the coordinate above has attribute of wall
			if (thisBoard.getBlockAttribute(playerCoordinate.getxPosition(), playerCoordinate.getyPosition() - 1) == BlockAttribute.WALL) {
				playerWallCollision = true;
			}
			else checkPlayerBoxCollision();
		}
		while (down) {
			//checks if the coordinate above has attribute of wall
			if (thisBoard.getBlockAttribute(playerCoordinate.getxPosition(), playerCoordinate.getyPosition() - 1) == BlockAttribute.WALL) {
				playerWallCollision = true;
			}
			else checkPlayerBoxCollision();
		}
		while (up) {
			//checks if the coordinate above has attribute of wall
			if (thisBoard.getBlockAttribute(playerCoordinate.getxPosition(), playerCoordinate.getyPosition() - 1) == BlockAttribute.WALL) {
				playerWallCollision = true;
			}
			else checkPlayerBoxCollision();
		}
		while (up) {
			//checks if the coordinate above has attribute of wall
			if (thisBoard.getBlockAttribute(playerCoordinate.getxPosition(), playerCoordinate.getyPosition() - 1) == BlockAttribute.WALL) {
				playerWallCollision = true;
			}
			else checkPlayerBoxCollision();
		}
		
		return playerWallCollision;
	}
	
	/**
	 * Check player box collision
	 * 
	 *  - returns true if there is a collision
	 *  - if true, calls method to check box/wall collision
	 *  - if false, calls method to move player
	 * 
	 * @return true if there is a collision
	 */
	public boolean checkPlayerBoxCollision() {
		
		boolean playerBoxCollision = false;
		while (up) {
			//checks if the coordinate above has attribute of box
			if (thisBoard.getBlockAttribute(playerCoordinate.getxPosition(), playerCoordinate.getyPosition() - 1) == BlockAttribute.BOX) {
				playerBoxCollision = true;
				checkBoxWallCollision();
			}
			// else move player
			else {
				Coordinate playerNow = new Coordinate(playerCoordinate.getxPosition(), playerCoordinate.getyPosition() - 1);
				thisBoard.addToMap(playerCoordinate, BlockAttribute.FLOOR);
				thisBoard.addToMap(playerNow, BlockAttribute.PLAYER);
			}
		}
		while (down) {
			//checks if the coordinate above has attribute of box
			if (thisBoard.getBlockAttribute(playerCoordinate.getxPosition(), playerCoordinate.getyPosition() + 1) == BlockAttribute.BOX) {
				playerBoxCollision = true;
				checkBoxWallCollision();
			}
			// else move player
			else {
				Coordinate playerNow = new Coordinate(playerCoordinate.getxPosition(), playerCoordinate.getyPosition() + 1);
				thisBoard.addToMap(playerCoordinate, BlockAttribute.FLOOR);
				thisBoard.addToMap(playerNow, BlockAttribute.PLAYER);
			}
		}
		while (left) {
			//checks if the coordinate above has attribute of box
			if (thisBoard.getBlockAttribute(playerCoordinate.getxPosition() - 1, playerCoordinate.getyPosition()) == BlockAttribute.BOX) {
				playerBoxCollision = true;
				checkBoxWallCollision();
			}
			// else move player
			else {
				Coordinate playerNow = new Coordinate(playerCoordinate.getxPosition() - 1, playerCoordinate.getyPosition());
				thisBoard.addToMap(playerCoordinate, BlockAttribute.FLOOR);
				thisBoard.addToMap(playerNow, BlockAttribute.PLAYER);
			}
		}
		while (right) {
			//checks if the coordinate above has attribute of box
			if (thisBoard.getBlockAttribute(playerCoordinate.getxPosition() + 1, playerCoordinate.getyPosition()) == BlockAttribute.BOX) {
				playerBoxCollision = true;
				checkBoxWallCollision();
			}
			// else move player
			else {
				Coordinate playerNow = new Coordinate(playerCoordinate.getxPosition() + 1, playerCoordinate.getyPosition());
				thisBoard.addToMap(playerCoordinate, BlockAttribute.FLOOR);
				thisBoard.addToMap(playerNow, BlockAttribute.PLAYER);
			}
		}
		
		return playerBoxCollision;
	}
	
	/**
	 * Check box wall collision
	 * 
	 * - returns true if there is a collision (with wall or another box)
	 * - if false, calls methods to move player and box
	 * - checks if box is on target once moved????
	 * 
	 * @return true if there is a collision
	 */
	public boolean checkBoxWallCollision() {
		
		boolean boxWallCollision = false;
		while (up) {
			//sets local variables needed for potential moves
			Coordinate boxNow = new Coordinate(playerCoordinate.getxPosition(), playerCoordinate.getyPosition() - 2);
			Coordinate playerNow = new Coordinate(playerCoordinate.getxPosition(), playerCoordinate.getyPosition() - 1);
			//checks if the coordinate 2 spaces above is a wall
			if (thisBoard.getBlockAttribute(playerCoordinate.getxPosition(), playerCoordinate.getyPosition() - 2) == BlockAttribute.WALL
					//or if it's another box 
					|| thisBoard.getBlockAttribute(playerCoordinate.getxPosition(), playerCoordinate.getyPosition() - 2) == BlockAttribute.BOX
					|| thisBoard.getBlockAttribute(playerCoordinate.getxPosition(), playerCoordinate.getyPosition() - 2) == BlockAttribute.BOXONGOAL) {
				boxWallCollision = true;
			}
			//if it's a goal
			else if (thisBoard.getBlockAttribute(playerCoordinate.getxPosition(),  playerCoordinate.getyPosition() - 2) == BlockAttribute.GOAL) {
				//move player and box
				thisBoard.addToMap(boxNow, BlockAttribute.BOXONGOAL);
				thisBoard.addToMap(playerNow, BlockAttribute.PLAYER);
				thisBoard.addToMap(playerCoordinate, BlockAttribute.FLOOR);
				//check success of level
			}
			else { //else move player and box
				thisBoard.addToMap(boxNow, BlockAttribute.BOX);
				thisBoard.addToMap(playerNow, BlockAttribute.PLAYER);
				thisBoard.addToMap(playerCoordinate, BlockAttribute.FLOOR);
			}
		}
		while (down) {
			//sets local variables needed for potential moves
			Coordinate boxNow = new Coordinate(playerCoordinate.getxPosition(), playerCoordinate.getyPosition() + 2);
			Coordinate playerNow = new Coordinate(playerCoordinate.getxPosition(), playerCoordinate.getyPosition() + 1);
			//checks if the coordinate 2 spaces below is a wall
			if (thisBoard.getBlockAttribute(playerCoordinate.getxPosition(), playerCoordinate.getyPosition() + 2) == BlockAttribute.WALL
					//or if it's another box 
					|| thisBoard.getBlockAttribute(playerCoordinate.getxPosition(), playerCoordinate.getyPosition() + 2) == BlockAttribute.BOX
					|| thisBoard.getBlockAttribute(playerCoordinate.getxPosition(), playerCoordinate.getyPosition() + 2) == BlockAttribute.BOXONGOAL) {
				boxWallCollision = true;
			}
			//if it's a goal
			else if (thisBoard.getBlockAttribute(playerCoordinate.getxPosition(),  playerCoordinate.getyPosition() + 2) == BlockAttribute.GOAL) {
				//move player and box
				thisBoard.addToMap(boxNow, BlockAttribute.BOXONGOAL);
				thisBoard.addToMap(playerNow, BlockAttribute.PLAYER);
				thisBoard.addToMap(playerCoordinate, BlockAttribute.FLOOR);
				//check success of level
			}
			else { //else move player and box
				thisBoard.addToMap(boxNow, BlockAttribute.BOX);
				thisBoard.addToMap(playerNow, BlockAttribute.PLAYER);
				thisBoard.addToMap(playerCoordinate, BlockAttribute.FLOOR);
			}
		}
		while (left) {
			//sets local variables needed for potential moves
			Coordinate boxNow = new Coordinate(playerCoordinate.getxPosition() - 2, playerCoordinate.getyPosition());
			Coordinate playerNow = new Coordinate(playerCoordinate.getxPosition() - 1, playerCoordinate.getyPosition());
			//checks if the coordinate 2 spaces left is a wall
			if (thisBoard.getBlockAttribute(playerCoordinate.getxPosition() - 2, playerCoordinate.getyPosition()) == BlockAttribute.WALL
					//or if it's another box 
					|| thisBoard.getBlockAttribute(playerCoordinate.getxPosition() - 2, playerCoordinate.getyPosition()) == BlockAttribute.BOX
					|| thisBoard.getBlockAttribute(playerCoordinate.getxPosition() - 2, playerCoordinate.getyPosition()) == BlockAttribute.BOXONGOAL) {
				boxWallCollision = true;
			}
			//if it's a goal
			else if (thisBoard.getBlockAttribute(playerCoordinate.getxPosition() - 2,  playerCoordinate.getyPosition()) == BlockAttribute.GOAL) {
				//move player and box
				thisBoard.addToMap(boxNow, BlockAttribute.BOXONGOAL);
				thisBoard.addToMap(playerNow, BlockAttribute.PLAYER);
				thisBoard.addToMap(playerCoordinate, BlockAttribute.FLOOR);
				//check success of level
			}
			else { //else move player and box
				thisBoard.addToMap(boxNow, BlockAttribute.BOX);
				thisBoard.addToMap(playerNow, BlockAttribute.PLAYER);
				thisBoard.addToMap(playerCoordinate, BlockAttribute.FLOOR);
			}
		}
		while (right) {
			//sets local variables needed for potential moves
			Coordinate boxNow = new Coordinate(playerCoordinate.getxPosition() + 2, playerCoordinate.getyPosition());
			Coordinate playerNow = new Coordinate(playerCoordinate.getxPosition() + 1, playerCoordinate.getyPosition());
			//checks if the coordinate 2 spaces above is a wall
			if (thisBoard.getBlockAttribute(playerCoordinate.getxPosition() + 2, playerCoordinate.getyPosition()) == BlockAttribute.WALL
					//or if it's another box 
					|| thisBoard.getBlockAttribute(playerCoordinate.getxPosition() + 2, playerCoordinate.getyPosition()) == BlockAttribute.BOX
					|| thisBoard.getBlockAttribute(playerCoordinate.getxPosition() + 2, playerCoordinate.getyPosition()) == BlockAttribute.BOXONGOAL) {
				boxWallCollision = true;
			}
			//if it's a goal
			else if (thisBoard.getBlockAttribute(playerCoordinate.getxPosition() + 2,  playerCoordinate.getyPosition()) == BlockAttribute.GOAL) {
				//move player and box
				thisBoard.addToMap(boxNow, BlockAttribute.BOXONGOAL);
				thisBoard.addToMap(playerNow, BlockAttribute.PLAYER);
				thisBoard.addToMap(playerCoordinate, BlockAttribute.FLOOR);
				//check success of level
			}
			else { //else move player and box
				thisBoard.addToMap(boxNow, BlockAttribute.BOX);
				thisBoard.addToMap(playerNow, BlockAttribute.PLAYER);
				thisBoard.addToMap(playerCoordinate, BlockAttribute.FLOOR);
			}
		}
		
		return boxWallCollision;
	}
	
	/**
	 * Success checker
	 * 
	 * - checks if all boxes are on goals
	 * 
	 * @return true if level complete
	 */
	public boolean success() {
		boolean successful = false;
		//check if all boxes are on goals
		return successful;
	}
	

	//INHERITED METHODS FROM KEYLISTENER
	
	@Override
	public void keyPressed(KeyEvent e) {
		keys[e.getKeyCode()] = true;
		System.out.println(up); //just to test key input is working
		
	}

	@Override
	public void keyReleased(KeyEvent e) {
		keys[e.getKeyCode()] = false;
		
	}

	@Override
	public void keyTyped(KeyEvent e) {
		
	}

}
