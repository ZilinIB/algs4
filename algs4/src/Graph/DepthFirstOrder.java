package Graph;

import edu.princeton.cs.algs4.Digraph;
import edu.princeton.cs.algs4.Queue;
import edu.princeton.cs.algs4.Stack;

/**
* This class enables clients to iterate through the vertices in various orders
* defined by the depth-first search.(prev order, post order, reverse-post order) 
* 
* @author Zilin
*/
public class DepthFirstOrder {
	private boolean[] marked;
	private Queue<Integer> prev;
	private Queue<Integer> post;
	private Stack<Integer> reversePost;
	
	/**
	 * Calculate all these orders using DFS.
	 * @param G The Graph.
	 */
	public DepthFirstOrder(Digraph G) {
		prev = new Queue<Integer>();
		post = new Queue<Integer>();
		reversePost = new Stack<Integer>();
		marked = new boolean[G.V()];
		
		for(int i = 0; i < G.V(); i++)
			if(!marked[i]) dfs(G, i);
	}
	
	/**
	 * Run DFS from the vertex v.
	 * @param G The graph.
	 * @param v The starting point.
	 */
	private void dfs(Digraph G, int v) {
		prev.enqueue(v);
		marked[v] = true;
		for(int w : G.adj(v)) {
			if(!marked[w])
				dfs(G, w);
		}
		post.enqueue(v);
		reversePost.push(v);
	}
	
	/**
	 * @return The prev order.
	 */
	public Iterable<Integer> prev(){
		return prev;
	}
	
	/**
	 * @return The post order.
	 */
	public Iterable<Integer> post(){
		return post;
	}
	
	/**
	 * @return The reversePost order.
	 */
	public Iterable<Integer> reversePost(){
		return reversePost;
	}
}
