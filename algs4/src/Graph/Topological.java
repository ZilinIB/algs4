package Graph;

import edu.princeton.cs.algs4.Digraph;
import edu.princeton.cs.algs4.StdOut;
import edu.princeton.cs.algs4.SymbolDigraph;

/**
* This class aims to find a topological order in the graph if it is DAG.
* 
* There are two implementations of the topological sort, 
* the first one (DepthFirstOrder.java) is based on DFS
* the second one (topologicalSort.java) is based a method on the A&D HS17 script P112.
* 
* Client example
* % java Topological jobs.txt / 2
* Calculus
* Linear Algebra
* Introduction to CS
* Advanced Programming
* Algorithms
* Theoretical CS
* Artificial Intelligence
* Robotics
* Machine Learning
* Neural Networks
* 
* @author Zilin
*/
public class Topological {
	private Iterable<Integer> order;
	
	/**
	 * Calculate the orders, and store the reversePost order in the field.
	 * @param G
	 */
	public Topological(Digraph G, String method) {
		DirectedCycle cyclefinder = new DirectedCycle(G);
		if(!cyclefinder.hasCycle()) {
			switch(method) {
				case "1": DepthFirstOrder dfs = new DepthFirstOrder(G);
						  order = dfs.reversePost();
						  break;
				case "2": TopologicalSort ts = new TopologicalSort(G);
						  order = ts.order();
						  break;
				default:  System.err.println("No such method! Method must be either 1 or 2!");
			}
		}
	}
	
	/**
	 * Judge if the graph is acyclic.
	 * @return true if it's DAG;
	 */
	public boolean isDAG() {
		return order != null;
	}
	
	/**
	 * Return the vertices in topological order.
	 * @return the vertices in topological order.
	 */
	public Iterable<Integer> order(){
		return order;
	}
	
	public static void main(String[] args) {
		String filename = args[0];
		String separator = args[1];
		String method = args[2];
		SymbolDigraph sg = new SymbolDigraph(filename, separator);
		
		Topological top = new Topological(sg.G(), method);
		for(int v : top.order())
			StdOut.println(sg.name(v));
	}
}
