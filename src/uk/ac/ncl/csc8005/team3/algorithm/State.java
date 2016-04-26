package uk.ac.ncl.csc8005.team3.algorithm;
import java.util.HashSet;

import uk.ac.ncl.csc8005.team3.block.Coordinate;

/**
 * This class is used to store the position and history of boxes and the player.
 */
public class State {

	public HashSet<Coordinate> boxes;
	public Coordinate player;
	
	public State() {
		boxes = new HashSet<>();
		player = new Coordinate();
	}
	
	public State(HashSet<Coordinate> boxes2, Coordinate newPlayer) {
		this.boxes = boxes2;
		this.player = newPlayer;
	}

	/**
	 * Implemented hashcode to check if state is already explored
	 */
	@Override
	public int hashCode() {
		int result = 17;
		for (Coordinate b : boxes) {
			result = 37 * result + b.hashCode();
		}
		result = 37 * result + player.hashCode();
		return result;
	}
	
	/**
	 * Overriding equals for contains() method
	 */
	@Override
	public boolean equals(Object object){
		
	    if (object == null) return false;
	    if (object == this) return true;
	    if (this.getClass() != object.getClass()) return false;
	    State s = (State)object;
	    if(this.hashCode()== s.hashCode()) return true;
	    if((this.boxes == s.boxes) && (this.player == s.player)) return true;
	    return false;
	}
	
	public void setPlayerPosition(Coordinate co){
		this.player = co;
	}
	
	public Coordinate getPlayerPosition(){
		return player;
	}
	/**
	 * return the HashSet of all boxes' positions.
	 */
	public HashSet<Coordinate> getBoxes(){
		return boxes;
	}
	
}