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
 * 	PROBLEMS:
 * 		- 	box moving method doesn't account for if there's more than one box
 * 		-   does not change block attribute when box moves
 * 		-   general confusion about block attributes etc
 * 
 * @author Kate
 *
 */
public class BoardController {
	
	//input
	private KeyManager keyManager;
	
	//board
	private Board thisBoard; //this level
	private Coordinate playerCoordinate, nextPlayerCoordinate, nextBoxCoordinate;
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
	 * Not sure if this is needed
	 * - calls update method of keyManager, which checks if any keys are pressed
	 */
	public void update() {
		keyManager.update();
	}
	
	/**
	 * Gets input from KeyManager and starts process of calling collision checking methods
	 * 
	 */
	private void getInput(){
		//gets next coordinate depending on what arrow key is pressed
		if(KeyManager().up)
			nextPlayerCoordinate = new Coordinate(playerCoordinate.getxPosition(), playerCoordinate.getyPosition() - 1);
			nextBoxCoordinate = new Coordinate(playerCoordinate.getxPosition(), playerCoordinate.getyPosition() - 2);
		if(KeyManager().down)
			nextPlayerCoordinate = new Coordinate(playerCoordinate.getxPosition(), playerCoordinate.getyPosition() + 1);
			nextBoxCoordinate = new Coordinate(playerCoordinate.getxPosition(), playerCoordinate.getyPosition() + 2);
		if(KeyManager().left)
			nextPlayerCoordinate = new Coordinate(playerCoordinate.getxPosition() - 1, playerCoordinate.getyPosition());
			nextBoxCoordinate = new Coordinate(playerCoordinate.getxPosition() - 2, playerCoordinate.getyPosition());
		if(KeyManager().right)
			nextPlayerCoordinate = new Coordinate(playerCoordinate.getxPosition() + 1, playerCoordinate.getyPosition());
			nextBoxCoordinate = new Coordinate(playerCoordinate.getxPosition() + 2, playerCoordinate.getyPosition());
		//calls first collision-checking method
		checkPlayerWallCollision(nextPlayerCoordinate);
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
	private boolean checkPlayerBoxCollision() {
		
		boolean playerBoxCollision = false;
		//if next coordinate is a box, calls method to check if box will collide with wall
		if (thisBoard.getBlockAttribute(nextPlayerCoordinate) == BlockAttribute.BOX
				|| thisBoard.getBlockAttribute(nextPlayerCoordinate) == BlockAttribute.BOXONGOAL) {
			playerBoxCollision = true;
			checkBoxWallCollision();
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
	 * @param Coordinate the next coordinate that the player will move to
	 * @return true if there is a collision
	 */
	private boolean checkBoxWallCollision() {
		boolean boxWallCollision = false;
		
		//if coordinate beyond the box is a wall or another box
		if (thisBoard.getBlockAttribute(nextBoxCoordinate) == BlockAttribute.BOX
				|| thisBoard.getBlockAttribute(nextBoxCoordinate) == BlockAttribute.BOXONGOAL
				|| thisBoard.getBlockAttribute(nextBoxCoordinate == BlockAttribute.WALL)) {
			boxWallCollision = true;
		}
		else {//MOVE PLAYER AND BOX
			playerCoordinate = nextPlayerCoordinate;
			boxCoordinate = nextBoxCoordinate;	
			//check if box is now on goal
			if (thisBoard.getBlockAttribute(nextBoxCoordinate) == BlockAttribute.GOAL) {
				//add to goal counter and check success
				goalCounter++;
				checkSuccess();
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
	public boolean checkSuccess() {
		boolean successful = false;

		if (goals == goalCounter)
			successful = true;
		
		return successful;
	}


}
