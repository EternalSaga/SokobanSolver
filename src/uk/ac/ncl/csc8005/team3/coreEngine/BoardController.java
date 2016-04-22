package uk.ac.ncl.csc8005.team3.coreEngine;
import uk.ac.ncl.csc8005.team3.block.*;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

/**
 * BoardController class
 * 
 *  - takes input from KeyManager (arrow keys)
 *  - checks for collisions
 *  - moves player and box if applicable
 *  - checks for success (all boxes on goals)
 *  
 *  Most methods are private because they will only be called from within this class
 *  
 * 	PROBLEMS:
 * 
 *      -   does not add to move history yet
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
	private boolean levelComplete; //if the level is complete
	
	public BoardController (Board thisBoard) {
	
		//input
		keyManager = new KeyManager();
		
		//board
		this.thisBoard = thisBoard;
		playerCoordinate = thisBoard.getPlayerCoordinate();
		goals = thisBoard.getNumOfGoals();
		goalCounter = 0; //at start of level, no boxes are on goals
		levelComplete = false;
	}
	
	/**
	 * Not sure if this is needed
	 * - calls update method of keyManager, which checks if any keys are pressed
	 */
	public void update() {
		keyManager.update();
	}
	
	/**
	 * Gets input from KeyManager
	 * 
	 *  - determines the next coordinates to which the characters want to move
	 *  - starts the process of calling collision checking methods
	 * 
	 */
	private void getInput(){
		//maybe should change to a switch statement. i think there might be problems if more than one key is pressed at once
		if(keyManager().up) {
			nextPlayerCoordinate = new Coordinate(playerCoordinate.getxPosition(), playerCoordinate.getyPosition() - 1);
			nextBoxCoordinate = new Coordinate(playerCoordinate.getxPosition(), playerCoordinate.getyPosition() - 2);
		}
		else if(keyManager().down) {
			nextPlayerCoordinate = new Coordinate(playerCoordinate.getxPosition(), playerCoordinate.getyPosition() + 1);
			nextBoxCoordinate = new Coordinate(playerCoordinate.getxPosition(), playerCoordinate.getyPosition() + 2);
		}
		else if(keyManager().left) {
			nextPlayerCoordinate = new Coordinate(playerCoordinate.getxPosition() - 1, playerCoordinate.getyPosition());
			nextBoxCoordinate = new Coordinate(playerCoordinate.getxPosition() - 2, playerCoordinate.getyPosition());
		}
		else if(keyManager().right) {
			nextPlayerCoordinate = new Coordinate(playerCoordinate.getxPosition() + 1, playerCoordinate.getyPosition());
			nextBoxCoordinate = new Coordinate(playerCoordinate.getxPosition() + 2, playerCoordinate.getyPosition());
		}
		else { //no arrow keys are being pressed so return here without calling collision checker
			return;
		}
		//calls first collision-checking method
		checkPlayerWallCollision();
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
		} 
		//if not, calls method to check player collision with box
		else {
			checkPlayerBoxCollision();
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
	private boolean checkPlayerBoxCollision() {
		
		boolean playerBoxCollision = false;
		
		//if next coordinate is a box, calls method to check if box will collide with wall
		if (thisBoard.getBlockAttribute(nextPlayerCoordinate) == BlockAttribute.BOX
				|| thisBoard.getBlockAttribute(nextPlayerCoordinate) == BlockAttribute.BOXONGOAL) {
			playerBoxCollision = true;
			checkBoxWallCollision();
		}
		else {
			movePlayer();
		}
		return playerBoxCollision;
	}
	
	/**
	 * Check box wall collision
	 * 
	 * - returns true if there is a collision (with wall or another box)
	 * 
	 * - if false, calls methods to move player and box
	 * 
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
		else {
			moveBox();
			movePlayer();
		}
		return boxWallCollision;
	}
	
	/**
	 * Move player
	 * 
	 * - changes block attributes on board
	 * - changes value of the field playerCoordinate
	 * 
	 */
	private void movePlayer() {
		
		//if he's moving onto a goal
		if (thisBoard.getBlockAttribute(nextPlayerCoordinate) == BlockAttribute.GOAL
				|| thisBoard.getBlockAttribute(nextPlayerCoordinate) == BlockAttribute.BOXONGOAL) {
			thisBoard.addToMap(nextPlayerCoordinate, BlockAttribute.PLAYERONGOAL);
		}
		//else he's moving onto a floor square
		else {
			thisBoard.addToMap(nextPlayerCoordinate, BlockAttribute.PLAYER);
		}
		//if he's moving off a goal
		if (thisBoard.getBlockAttribute(playerCoordinate) == BlockAttribute.PLAYERONGOAL) {
			thisBoard.addToMap(playerCoordinate, BlockAttribute.GOAL);
		}
		//else he's moving off a floor square
		else {
			thisBoard.addToMap(playerCoordinate, BlockAttribute.FLOOR);
		}
		//change local variable
		playerCoordinate = nextPlayerCoordinate;
	}
	
	/**
	 * Move box
	 * 
	 * - changes block attributes on board
	 * 
	 * - if the box is moving on or off a goal, changes value of the field goalCounter
	 * - if the box is moving onto a goal, calls method to check success of level
	 * 
	 * Note: the coordinate the box is moving off of is the same one that the player is
	 * moving onto, so no need to change those attributes in this method, because the 
	 * movePlayer method does it.
	 * 
	 */
	private void moveBox() {
		//if the box will be moving onto a goal
		if (thisBoard.getBlockAttribute(nextBoxCoordinate) == BlockAttribute.GOAL) {
			thisBoard.addToMap(nextBoxCoordinate, BlockAttribute.BOXONGOAL);
			//add to goal counter and check success
			goalCounter++;
			checkSuccess();
		}
		//else it's moving onto floor
		else {
			thisBoard.addToMap(nextBoxCoordinate, BlockAttribute.BOX);
		}
		//if the box is moving off a goal
		if (thisBoard.getBlockAttribute(nextPlayerCoordinate) ==  BlockAttribute.BOXONGOAL) {
			//need to decrement the goalCounter as it just moved off a goal
			goalCounter--;
		}
	}
	
	/**
	 * Success checker
	 * 
	 * - checks if all boxes are on goals
	 * - changes the field levelComplete to true if so
	 * 
	 * @return true if level complete
	 */
	private boolean checkSuccess() {
		boolean successful = false;

		if (goals == goalCounter)
			successful = true;
			levelComplete = true;
		
		return successful;
	}

}
