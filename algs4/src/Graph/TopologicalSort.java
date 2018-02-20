package Graph;

import edu.princeton.cs.algs4.Digraph;
import edu.princeton.cs.algs4.Queue;
import edu.princeton.cs.algs4.Stack;

/**
* The implementation is from the A&D HS17 script
* 
* @author Zilin
*/
public class TopologicalSort {
	private Queue<Integer> order;
	private int[] inDegree;
	
	/**
	 * Initialize the TS object, calculate the order.
	 * @param G The Graph.
	 */
	public TopologicalSort(Digraph G) {
		order = new Queue<Integer>();
		inDegree = new int[G.V()];
		ts(G);
	}
	
	/**
	 * Run the TS algorithm.
	 * @param G The Graph.
	 */
	private void ts(Digraph G) {
		Stack<Integer> S = new Stack<Integer>();
		
		// Calculate the inDegree of every vertex.
		for(int v = 0; v < G.V(); v++)
			for(int w : G.adj(v))
				inDegree[w]++;
		
		// Push those whose inDegree is 0 in the stack
		for(int v = 0; v < G.V(); v++)
			if(inDegree[v] == 0)
				S.push(v);
		
		while(!S.isEmpty()) {
			int v = S.pop();
			order.enqueue(v);
			for(int w : G.adj(v)) {
				inDegree[w]--;
				if(inDegree[w] == 0)
					S.push(w);
			}
		}
	}
	
	/**
	 * @return the topological order.
	 */
	public Iterable<Integer> order() {
		return order;
	}
}
