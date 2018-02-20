package Graph;

import edu.princeton.cs.algs4.Digraph;
import edu.princeton.cs.algs4.Stack;

/**
* The class dectects if a directed graph contains circle.
* 
* @author Zilin
*/
public class DirectedCycle {
	private boolean[] marked;
	private int[] edgeTo;
	private Stack<Integer> cycle;
	private boolean[] onStack;
	
	/**
	 * Initialize a DC object, using DFS to detect if there is a cycle in this graph
	 * @param G The graph
	 */
	public DirectedCycle(Digraph G) {
		marked = new boolean[G.V()];
		edgeTo = new int[G.V()];
		cycle = new Stack<Integer>();
		onStack = new boolean[G.V()];
		
		for(int i = 0; i < G.V(); i++)
			if(!marked[i]) dfs(G, i);
	}
	
	/**
	 * A embellished version of DFS.
	 * @param G The Graph.
	 * @param v The index of the vertex from which we start the traversal.
	 */
	private void dfs(Digraph G, int v) {
		marked[v] = true;
		onStack[v] = true;
		for(int w : G.adj(v)) {
			if(this.hasCycle()) return;
			else if(!marked[w]) {
				edgeTo[w] = v;
				dfs(G, w);
			}else if(onStack[w]) {
				cycle.push(w);
				for(int cur = v; cur != w; cur = edgeTo[cur]) {
					cycle.push(cur);
				}
				cycle.push(w);
			}
		}
		onStack[v] = false;
	}
	
	/**
	 * Return true if it has a cycle.
	 * @return true if it has a cycle.
	 */
	public boolean hasCycle() {
		return !cycle.isEmpty();
	}
	
	/**
	 * Return the first cycle that this algorithm confronts.
	 * @return the first cycle that this algorithm confronts.
	 */
	public Iterable<Integer> cycle(){
		return cycle;
	}
}
