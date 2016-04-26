package uk.ac.ncl.csc8005.team3.algorithm;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.Set;

import uk.ac.ncl.csc8005.team3.block.Coordinate;
import uk.ac.ncl.csc8005.team3.coreEngine.Board;

public class AStarSearch {
	private static Heuristics h;
	public static Comparator<Node> costComparator = new Comparator<Node>() {
		@Override
		public int compare(Node n1, Node n2) {
			return (int) (n1.cost - n2.cost);
		}
	};

	public AStarSearch(Heuristics h) {
		AStarSearch.h = h;
	}

	public String prioritySearch(Board currentProblem) {
		String answer;
		long startTime = System.currentTimeMillis();
		int totalNode = 1;
		int redundant = 0;
		Node initial = new Node(currentProblem.getInitialState(), null, 0, "");
		Set<State> explored = new HashSet<State>();
		// check search method to see if greedy or A* is chosen
		Queue<Node> fringe = new PriorityQueue<Node>(11, costComparator);

		fringe = new PriorityQueue<Node>(11, astarComparator);

		fringe.add(initial);
		while (!fringe.isEmpty()) {
			Node n = fringe.remove();
			if (currentProblem.goalTest(n.state))
				return getSolution( n, totalNode, redundant, fringe.size(), explored.size(),
						System.currentTimeMillis() - startTime);
			if (!currentProblem.deadlockTest(n.state)) { // check for deadlock
				explored.add(n.state);
				ArrayList<String> actions = currentProblem.actions(n.state);
				for (int i = 0; i < actions.size(); i++) {
					Node child = getChild(currentProblem, n, actions.get(i),isUCS);
					if ((child != null) && (child.state != null)) {
						totalNode++;
						if ((!explored.contains(child.state)) && (!fringe.contains(child)))
							fringe.add(child);
						else {
							redundant++;
							// check if fringe contains current state and
							// compare the cost
							for (Node next : fringe) {
								if (next == child)
									if (child.cost < next.cost) {
										next = child;
									}
							}
						}
					}
				}
			}
		}
		return getSolution( null, totalNode, redundant, fringe.size(), explored.size(),
				System.currentTimeMillis() - startTime);
	}
	/**
	 * When solution is found, or when a program fails to solve the puzzle, getSolution returns the solution
	 * @param method
	 * @param n
	 * @param totalNode
	 * @param redundant
	 * @param fringeSize
	 * @param exploredSize
	 * @param totalTime
	 * @return
	 */
	private String getSolution(Node n, int totalNode, int redundant, int fringeSize, int exploredSize, long totalTime) {
		String result = "";
		int steps = 0;
		if (n == null)
			result = "Failed to solve the puzzle";
		else
			while (n.parent!=null) {
				result = n.move + " " + result;
				n = n.parent;
				steps++;
			}
		result =  ":\n" + result + "\n(total of " + steps + " steps)" +
				"\na) Number of nodes generated: " + totalNode + 
				"\nb) Number of nodes containing states that were generated previously: " + redundant + 
				"\nc) Number of nodes on the fringe when termination occurs: " + fringeSize + 
				"\nd) Number of nodes on the explored list (if there is one) when termination occurs: " + exploredSize +
				"\ne) The actual run time of the algorithm, expressed in actual time units: " + totalTime + "ms";
		return result;
	}
	
	private Node getChild(Board currentBoard, Node n, String action, boolean isUcs) {
		@SuppressWarnings("unchecked")
		HashSet<Coordinate> boxes = (HashSet<Coordinate>) n.state.boxes.clone();
		int row = n.state.getPlayerPosition().getxPosition();
		int col = n.state.getPlayerPosition().getyPosition();
		int newCost = n.cost+1;
		Coordinate newPlayer = n.state.player;
		char choice = action.charAt(0);
		switch(choice) {
			case 'u':
				//update player coordinate
				newPlayer = new Coordinate(row-1, col);
				//check if player is pushing a box
				if (boxes.contains(newPlayer)) {
					Coordinate newBox = new Coordinate(row-2, col);
					//update box coordinate
					boxes.remove(newPlayer);
					boxes.add(newBox);
					if (isUcs)
						newCost++;
				}
				break;
			case 'd':
				//update player coordinate
				newPlayer = new Coordinate(row+1, col);
				//check if player is pushing a box
				if (boxes.contains(newPlayer)) {
					Coordinate newBox = new Coordinate(row+2, col);
					//update box coordinate
					boxes.remove(newPlayer);
					boxes.add(newBox);
					if (isUcs)
						newCost++;
				}
				break;
			case 'l':
				//update player coordinate
				newPlayer = new Coordinate(row, col-1);
				//check if player is pushing a box
				if (boxes.contains(newPlayer)) {
					Coordinate newBox = new Coordinate(row, col-2);
					//update box coordinate
					boxes.remove(newPlayer);
					boxes.add(newBox);
					if (isUcs)
						newCost++;
				}
				break;
			case 'r':
				//update player coordinate
				newPlayer = new Coordinate(row, col+1);
				//check if player is pushing a box
				if (boxes.contains(newPlayer)) {
					Coordinate newBox = new Coordinate(row, col+2);
					//update box coordinate
					boxes.remove(newPlayer);
					boxes.add(newBox);
					if (isUcs)
						newCost++;
				}
				break;
		}
		return new Node(new State(boxes, newPlayer), n, newCost, Character.toString(choice));
	}
	
	/**
	 * Comparator for A*
	 */
	public static Comparator<Node> astarComparator = new Comparator<Node>() {
		@Override
		public int compare(Node n1, Node n2) {
			return (int) ((n1.cost + h.getHeuristic(n1.state)) - (n2.cost + h.getHeuristic(n2.state)));
		}
	};
}
