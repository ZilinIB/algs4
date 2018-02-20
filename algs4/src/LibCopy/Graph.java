package LibCopy;

import java.util.NoSuchElementException;

import edu.princeton.cs.algs4.Bag;
import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.Stack;
import edu.princeton.cs.algs4.StdOut;

/**
 * The class represents an undirected graph of vertices name 0 through |V| to 1.
 * It supports two primary operations: add an edge to the graph, iterate over all
 * of the vertices adjacent to a vertex. It also provides methods for returning the 
 * number of vertices |V| and the number of edges |E|. Parallel edges and self-loops
 * are permitted. 
 * By convention, a self-loop v-v appears in the adjacency list of v twice and 
 * contributes two to the degree of v.
 * 
 * This implementation uses an adjacency-lists representation, which is a 
 * vertex-indexed array of "Bag" objects.
 * All operations take constant time (in the worst case) except iterating over the 
 * vertices.
 * 
 * @author Zilin
 */
public class Graph {
	private static final String NEWLINE = System.getProperty("line.separator");
	
	private final int V;
	private int E;
	private Bag<Integer>[] adj;
	
	/**
	 * Initializes an empty graph with V vertices and 0 edges.
	 * param V the number of vertices
	 * 
	 * @param V number of vertices
	 * @throws IllegalArgumentException if V < 0
	 */
	public Graph(int V) {
		if (V < 0) throw new IllegalArgumentException("Number of vertices must be nonnegative.");
		this.V = V;
		this.E = 0;
		adj = (Bag<Integer>[]) new Bag[V];
		for (int v = 0; v < V; v++) {
			adj[v] = new Bag<Integer>();
		}
	}
	/**
	 * Initialize a graph from the specified input stream.
	 * The format is the number of vertices |V|,
	 * followed by the number of edges |E|,
	 * followed by the |E| pairs of vertices, with each entry separated by whitespace.
	 * 
	 * @param in the input stream
	 * @throws IllegalArgumentException if the endpoints of any edge are not in prescribed range
	 * @throws IllegalArgumentExcepiton if the number of vertices or edges is negative
	 * @throws IllegalArgumentException if the input stream is in the wrong format
	 */
	public Graph(In in) {
		try {
			this.V = in.readInt();
			if(V < 0) throw new IllegalArgumentException("number of vertices in a Graph can't be negative.");
			adj = (Bag<Integer>[]) new Bag[V];
			for(int v = 0; v < V; v++) {
				adj[v] = new Bag<Integer>();
			}
			
			int E = in.readInt();
			if(E < 0) throw new IllegalArgumentException("number of edges in a Graph can't be negative.");
			for (int i = 0; i < E; i++) {
				int v = in.readInt();
				int w = in.readInt();
				validateVertex(v);
				validateVertex(w);
				addEdge(v, w);
			}
		}catch (NoSuchElementException e) {
			throw new IllegalArgumentException("invalid input format in Graph constructor.");
		}
	}
	
	/**
	 * Initializes a new graph that is a deep copy of G
	 * 
	 * @param G the graph to copy
	 */
	public Graph(Graph G) {
		this(G.V());
		this.E = G.E();
		for (int v = 0; v < G.V(); v++) {
			// reverse so that adjacency list is in same order as original
			Stack<Integer> reverse = new Stack<Integer>();
			for(int w : G.adj[v]) {
				reverse.push(w);
			}
			for(int w : reverse) {
				adj[v].add(w);
			}
		}
	}
	
	/**
	 * Returns the number of the vertices in this graph.
	 * 
	 * @return the number of the vertices in this graph.
	 */
	public int V() {
		return V;
	}
	
	/**
	 * Returns the number of edges in this graph.
	 */
	public int E() {
		return E;
	}
	
	/**
	 * throw an IllegalArgumentException unless 0 <= v < V
	 */
	private void validateVertex(int v) {
		if(v < 0 || v >= V)
			throw new IllegalArgumentException("vertex" + v + "is not between 0 and " + (V-1));
	}
	
	/**
	 * Adds the undirected edge v-w to this graph.
	 * 
	 * @param v one vertex in the edge
	 * @param w the other vertex in the edge
	 * @throws IllegalArgumentException unless 0 <= v, w < V
	 */
	public void addEdge(int v, int w) {
		validateVertex(v);
		validateVertex(w);
		E++;
		adj[w].add(w);
		adj[v].add(v);
	}
	
	/**
	 * Returns the vertices adjacent to vertex v.
	 * 
	 * @param v the vertex
	 * @return the vertices adjacent to vertex v, as an iterable
	 * @throws IllegalArgumentException unless 0 <= v < V
	 */
	public Iterable<Integer> adj(int v){
		return adj[v];
	}
	
	/**
	 * Returns the degree of vertex v
	 * 
	 * @param v the vertex
	 * @return the degree of vertex v
	 * @throws IllegalArgumentException unless 0 <= v < V
	 */
	public int degree(int v) {
		validateVertex(v);
		return adj[v].size();
	}
	
	/**
	 * Returns a string representation of this graph.
	 * 
	 * @return the number of vertices, followed by the number of edges E,
	 * 		   followed by the V adjacency lists
	 */
	public String toString() {
		StringBuilder s = new StringBuilder();
		s.append(V + " vertices, " + E + "edges " + NEWLINE);
		for(int v = 0; v < V; v++) {
			s.append(v + ": ");
			for (int w : adj[v]) {
				s.append(w + " ");
			}
			s.append(NEWLINE);
		}
		return s.toString();
	}
	
	/**
	 * Unit tests the Graph data type
	 * 
	 * @param args the command-line arguments
	 */
	public static void main(String[] args) {
		In in = new In(args[0]);
		Graph G = new Graph(in);
		StdOut.println(G);
	}
}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
