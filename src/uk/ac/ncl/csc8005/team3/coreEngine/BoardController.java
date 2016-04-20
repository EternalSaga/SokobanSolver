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
			Coordinate nextPlayerCoordinate = new Coordinate(playerCoordinate.getxPosition(), playerCoordinate.getyPosition() - 1);
		}
		while (down) {
			Coordinate nextPlayerCoordinate = new Coordinate(playerCoordinate.getxPosition(), playerCoordinate.getyPosition() + 1);
		}
		while (left) {
			Coordinate nextPlayerCoordinate = new Coordinate(playerCoordinate.getxPosition() - 1, playerCoordinate.getyPosition());
		}
		while (right) {
			Coordinate nextPlayerCoordinate = new Coordinate(playerCoordinate.getxPosition() + 1, playerCoordinate.getyPosition());
		}
		//checks if the next coordinate has attribute of wall
		if (thisBoard.getBlockAttribute(nextPlayerCoordinate) == BlockAttribute.WALL) {
			playerWallCollision = true;
		} //if so, calls method to check player collision with box
		else checkPlayerBoxCollision(nextPlayerCoordinate);
		
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
	private boolean checkPlayerBoxCollision(Coordinate nextPlayerCoordinate) {
		
		boolean playerBoxCollision = false;
		//if next coordinate is a box, calls method to check if box will collide with wall
		if (thisBoard.getBlockAttribute(nextPlayerCoordinate) == BlockAttribute.BOX
				|| thisBoard.getBlockAttribute(nextPlayerCoordinate) == BlockAttribute.BOXONGOAL) {
			playerBoxCollision = true;
			checkBoxWallCollision(nextPlayerCoordinate);
		}
		else //MOVE PLAYER
			playerCoordinate = nextPlayerCoordinate;
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
	private boolean checkBoxWallCollision(Coordinate nextPlayerCoordinate) {
		boolean boxWallCollision = false;
		
		//gets next coordinate beyond box depending on direction
		while (up) {
			Coordinate nextBoxCoordinate = new Coordinate(playerCoordinate.getxPosition(), playerCoordinate.getyPosition() - 2);
		}
		while (down) {
			Coordinate nextBoxCoordinate = new Coordinate(playerCoordinate.getxPosition(), playerCoordinate.getyPosition() + 2);
		}
		while (left) {
			Coordinate nextBoxCoordinate = new Coordinate(playerCoordinate.getxPosition() - 2, playerCoordinate.getyPosition());
		}
		while (right) {
			Coordinate nextBoxCoordinate = new Coordinate(playerCoordinate.getxPosition() + 2, playerCoordinate.getyPosition());
		}
		//if it's a wall or another box
		if (thisBoard.getBlockAttribute(nextBoxCoordinate) == BlockAttribute.BOX
				|| thisBoard.getBlockAttribute(nextBoxCoordinate) == BlockAttribute.BOXONGOAL
				|| thisBoard.getBlockAttribute(nextBoxCoordinate == BlockAttribute.WALL)) {
			boxWallCollision = true;
		}
		else //MOVE PLAYER AND BOX
			playerCoordinate = nextPlayerCoordinate;
			boxCoordinate = nextBoxCoordinate;	
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
	public boolean checkSuccess() {
		boolean successful = false;

		if (goals == goalCounter)
			successful = true;
		
		return successful;
	}


}
