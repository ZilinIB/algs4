package Graph;


import edu.princeton.cs.algs4.Graph;
import edu.princeton.cs.algs4.Stack;

/**
 * Finds the path from source vertex s to every other connected vertex in the
 * graph. Algorithm Based on DFS. The path that this algorithm finds is not the
 * shortest path.
 * 
 * @author Zilin
 */
public class DepthFirstPaths implements Paths{
	private boolean[] marked;
	private int[] edgeTo;
	private final int s;

	/**
	 * Run DFS and calculate the parent of each vertex on the path.
	 * 
	 * @param G   The Graph in which we search the paths
	 * @param s   The source vertex
	 */
	public DepthFirstPaths(Graph G, int s) {
		marked = new boolean[G.V()];
		edgeTo = new int[G.V()];
		this.s = s;
		dfs(G, s);
	}

	/**
	 * Traverse the graph using DFS from the source vertex, store the former vertex
	 * for every vertex when we visited it the first time.
	 * 
	 * @param G   The Graph
	 * @param v   The vertex from which we start the Traversal.
	 */
	private void dfs(Graph G, int v) {
		marked[v] = true;
		for (int w : G.adj(v)) {
			if(!marked[w]) {
				edgeTo[w] = v;
				dfs(G, w);
			}
		}
	}
	
	/**
	 * Check if there is a path from source to v.
	 * @param v The end point.
	 * @return if there is a path from source to v.
	 */
	public boolean hasPathTo(int v) {
		return marked[v];
	}
	
	/**
	 * Return the path from s to v.
	 * @param v The end point.
	 * @return The stack that stores each vertex on the path in an reverse order.
	 */
	public Iterable<Integer> pathTo(int v){
		if(!hasPathTo(v)) return null;
		Stack<Integer> path = new Stack<Integer>();
		
		for(int x = v; x != s; x = edgeTo[x]) {
			path.push(x);
		}
		path.push(s);
		
		return path;
	}
}
