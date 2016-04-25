package uk.ac.ncl.csc8005.team3.algorithm;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.Set;


public class AStarSearch {
private static Heuristics h;
	
	public AStarSearch(Heuristics h) {
		AStarSearch.h = h;
	}
	
	public String prioritySearch(Problem currentProblem) {
		String answer;
		long startTime = System.currentTimeMillis();
		int totalNode = 1;
		int redundant = 0;
		Node initial = new Node(p.initialState, null, 0, "");
		Set<State> explored = new HashSet<State>();
		//check search method to see if greedy or A* is chosen
		Queue<Node> fringe = new PriorityQueue<Node>(11, costComparator);

		
		fringe = new PriorityQueue<Node>(11, astarComparator);

		
		fringe.add(initial);
		while (!fringe.isEmpty()) {
			Node n = fringe.remove();
			if (p.goalTest(n.state))
				return getSolution(method, n, totalNode, redundant, fringe.size(), explored.size(), System.currentTimeMillis() - startTime);
			if (!p.deadlockTest(n.state)) { //check for deadlock
				explored.add(n.state);
				ArrayList<String> actions = p.actions(n.state);
				for (int i=0; i<actions.size(); i++) {
					Node child = getChild(p, n, actions.get(i), isUCS);
					if((child!=null) && (child.state!=null)) {
						totalNode++;
						if ((!explored.contains(child.state))&&(!fringe.contains(child)))
								fringe.add(child);
						else {
							redundant++;
							//check if fringe contains current state and compare the cost
							for (Node next : fringe) {
								if (next == child) 
									if(child.cost < next.cost) {
										next = child;
									}
							}
						}
					}
				}
			}
		}
		return getSolution(method, null, totalNode, redundant, fringe.size(), explored.size(), System.currentTimeMillis() - startTime);
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
