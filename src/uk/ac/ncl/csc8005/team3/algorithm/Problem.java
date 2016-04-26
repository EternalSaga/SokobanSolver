package uk.ac.ncl.csc8005.team3.algorithm;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;

import uk.ac.ncl.csc8005.team3.block.Coordinate;
import uk.ac.ncl.csc8005.team3.coreEngine.Board;

public class Problem extends Board {
	State initialState;
	HashSet<Coordinate> walls;
	HashSet<Coordinate> goals;
	HashSet<Coordinate> boxes;
	HashMap<Coordinate, Coordinate> blocked;
	
	public Problem() {
		super();
		
		
	}
}
