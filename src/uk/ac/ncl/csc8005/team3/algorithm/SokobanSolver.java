package uk.ac.ncl.csc8005.team3.algorithm;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.HashSet;
import java.util.NoSuchElementException;
import java.util.Scanner;

import uk.ac.ncl.csc8005.team3.coreEngine.Board;
import uk.ac.ncl.csc8005.team3.coreEngine.IOMethods;

/**
 * SokobanSolver is adapted from open source website Github.com.
 * Reference: https://github.com/jameshong92/sokoban-solver/blob/master/src/SokobanSolver.java
 * SokobanSolver loads the input file and stores each character into appropriate set (walls, goals, boxes or player)
 * After the file is read, it calls Search class to solve the sokoban puzzle according to the chosen search method.
 * @Original author Hyun Seung Hong (hh2473)
 * @Modified by Ruizhe Liu
 */
public class SokobanSolver {
	
	private HashSet<Coordinate> walls;
	private HashSet<Coordinate> goals;
	private HashSet<Coordinate> boxes;
	
	private Coordinate player;
	private Problem prob;
	private Heuristics h;
	
	private int row;
	private int col;
	
	public SokobanSolver() {
	}
	
	/**
	 * Reads each line from the input file and adds each character to walls, goals, player or boxes
	 * @param filename
	 * @return
	 * @throws FileNotFoundException
	 * @throws NumberFormatException
	 * @throws NoSuchElementException
	 */
	public int loadFile(Board board, char hChoice) throws
			NumberFormatException, NoSuchElementException {
		IOMethods io = new IOMethods();
		col = 0;
		int numPlayer = 0;
		walls = new HashSet<Coordinate>();
		goals = new HashSet<Coordinate>();
		boxes = new HashSet<Coordinate>();
		String[] currentBoard = io.outputStringBoard(board);

		for (int i=0; i<currentBoard.length; i++) {
			for (int j=0; j<currentBoard[i].length(); j++) {
				char c = currentBoard[i].charAt(j);
				if (c=='#') //walls
					walls.add(new Coordinate(i, j));
				if (c == '@' || c == '+') { //player
					player = new Coordinate(i, j);
					numPlayer++;
				}
				if (c == '.' || c == '+' || c == '*') //goals
					goals.add(new Coordinate(i, j));
				if (c == '$' || c == '*') //boxes
					boxes.add(new Coordinate(i,j));
			}
			if (currentBoard[i].length() > col)
				col = currentBoard[i].length();
		}
		prob = new Problem(walls, new State(boxes, player), goals);
		h = new Heuristics(goals, hChoice);
		System.out.println("row: " + row + ", col: " + col);
		return numPlayer;
	}

	/**
	 * Calls appropriate search method to solve the puzzle
	 * @param method
	 * @return
	 */
	public String solve(char method) {
		Search s = new Search(h);
		switch(method) {
		case 'b':
			return s.bfs(prob);
		case 'd':
			return s.dfs(prob);
		case 'u':
			return s.prioritySearch(prob, 'u');
		case 'a':
			return s.prioritySearch(prob, 'a');	
		case 'g':
			return s.prioritySearch(prob, 'g');
		default:
			return "Invalid method, please choose a valid search method.";
		}
	}

	public int getRow() {
		return row;
	}
	
	public int getCol() {
		return col;
	}

	public HashSet<Coordinate> getWalls() {
		return walls;
	}
	
	public HashSet<Coordinate> getBoxes() {
		return boxes;
	}
	
	public HashSet<Coordinate> getGoals() {
		return goals;
	}
	
	public Coordinate getPlayer() {
		return player;
	}
}
