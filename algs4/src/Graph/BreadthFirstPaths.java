package Graph;

import edu.princeton.cs.algs4.Graph;
import edu.princeton.cs.algs4.Queue;
import edu.princeton.cs.algs4.Stack;

/**
* Finds the shortest path from source vertex s to every other vertex
* using BFS algorithm.
* 
* @author Zilin
*/
public class BreadthFirstPaths implements Paths {
	private boolean[] marked;
	private int[] edgeTo;
	private final int s;
	
	/**
	 * Run bfs and store the parent of each vertex on the path.
	 * @param G The Graph.
	 * @param s The source vertex.
	 */
	public BreadthFirstPaths(Graph G, int s) {
		marked = new boolean[G.V()];
		edgeTo = new int[G.V()];
		this.s = s;
		
		bfs(G, s);
	}
	
	/**
	 * Traverse the graph using BFS from the source vertex, store the former vertex
	 * for every vertex when we visited it the first time.
	 * 
	 * @param G   The Graph
	 * @param v   The vertex from which we start the Traversal.
	 */
	private void bfs(Graph G, int s) {
		Queue<Integer> queue = new Queue<Integer>();
		queue.enqueue(s);
		marked[s] = true;
		
		while(!queue.isEmpty()) {
			int v = queue.dequeue();
			for(int w : G.adj(v)) {
				if(!marked[w]) {
					marked[w] = true;
					edgeTo[w] = v;
					queue.enqueue(w);
				}
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
	public Iterable<Integer> pathTo(int v) {
		if(!hasPathTo(v)) return null;
		Stack<Integer> path = new Stack<Integer>();
		
		for(int x = v; x != s; x = edgeTo[x]) {
			path.push(x);
		}
		path.push(s);
		
		return path;
	}
}
