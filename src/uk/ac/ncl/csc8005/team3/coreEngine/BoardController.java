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
 *  - checks for success (all boxes on targets)
 *  
 *  - made some improvements by adding separate movePlayer method - still lots to do though
 * 
 * @author Kate
 *
 */
public class BoardController {
	
	//input
	public boolean up, down, left, right;
	private KeyManager keyManager;
	
	//board
	private Board thisBoard; //this level
	private Coordinate playerCoordinate;
	private int goals; //the number of goals in this level
	private int goalCounter; //keeps track of how many boxes have reached goals during play
	
	public BoardController (Board thisBoard) {
	
		//input
		keyManager = new KeyManager();
		
		//board
		this.thisBoard = thisBoard;
		playerCoordinate = thisBoard.getPlayerCoordinate();
		goals = thisBoard.getNumOfGoals();
		goalCounter = 0; //at start of level, no boxes are on goals
	}
	
	/**
	 * This will be called from somewhere in the core engine 
	 * - calls update method of keyManager, which checks if any keys are pressed
	 */
	public void update() {
		keyManager.update();
	}
	
	/**
	 * Checks for collision between player and wall
	 * 
	 * - returns true if there is a collision
	 * - if false, calls method to check player-box collision 
	 * 
	 * @return true if there is a collision
	 */
	private boolean checkPlayerWallCollision() {
		
		boolean playerWallCollision = false;
		//gets next coordinate depending on direction
		while (up) {
			Coordinate nextCoordinate = new Coordinate(playerCoordinate.getxPosition(), playerCoordinate.getyPosition() - 1);
		}
		while (down) {
			Coordinate nextCoordinate = new Coordinate(playerCoordinate.getxPosition(), playerCoordinate.getyPosition() + 1);
		}
		while (left) {
			Coordinate nextCoordinate = new Coordinate(playerCoordinate.getxPosition() - 1, playerCoordinate.getyPosition());
		}
		while (right) {
			Coordinate nextCoordinate = new Coordinate(playerCoordinate.getxPosition() + 1, playerCoordinate.getyPosition());
		}
		//checks if the next coordinate has attribute of wall
		if (thisBoard.getBlockAttribute(nextCoordinate) == BlockAttribute.WALL) {
			playerWallCollision = true;
		} //if so, calls method to check player collision with box
		else checkPlayerBoxCollision();
		
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
	private boolean checkPlayerBoxCollision() {
		
		boolean playerBoxCollision = false;
		//gets next coordinate depending on direction
		while (up) {
			Coordinate nextCoordinate = new Coordinate(playerCoordinate.getxPosition(), playerCoordinate.getyPosition() - 1);
		}
		while (down) {
			Coordinate nextCoordinate = new Coordinate(playerCoordinate.getxPosition(), playerCoordinate.getyPosition() + 1);
		}
		while (left) {
			Coordinate nextCoordinate = new Coordinate(playerCoordinate.getxPosition() - 1, playerCoordinate.getyPosition());
		}
		while (right) {
			Coordinate nextCoordinate = new Coordinate(playerCoordinate.getxPosition() + 1, playerCoordinate.getyPosition());
		}
		//if it's a box, calls method to check if box will collide with wall
		if (thisBoard.getBlockAttribute(nextCoordinate) == BlockAttribute.BOX
				|| thisBoard.getBlockAttribute(nextCoordinate) == BlockAttribute.BOXONGOAL) {
			playerBoxCollision = true;
			checkBoxWallCollision();
		}
		else //MOVE PLAYER
			playerCoordinate = nextCoordinate;
		}
		return playerBoxCollision;
	}
	
	/**
	 * Check box wall collision
	 * 
	 * - returns true if there is a collision (with wall or another box)
	 * 
	 * - if false, calls methods to move player and box, then:
	 * 		- checks if box is on target once moved
	 * 		- calls method to check success of level
	 * 
	 * @return true if there is a collision
	 */
	private boolean checkBoxWallCollision() {
		boolean boxWallCollision = false;
		
		return boxWallCollision;
	}
	
	/**
	 * Success checker
	 * 
	 * - checks if all boxes are on goals
	 * 
	 * @return true if level complete
	 */
	public boolean checkSuccess() {
		boolean successful = false;

		if (goals == goalCounter)
			successful = true;
		
		return successful;
	}


}
