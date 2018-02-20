package Graph;

import edu.princeton.cs.algs4.Digraph;
import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.StdOut;

/**
* This class calculate the transitive and reflexive closure of a graph.
* 
* @author Zilin
*/
public class TransitiveAndReflexiveClosure {
	private boolean[][] reachable;

	/**
	 * Initialize a TRC object and calculate the reachability of all the whole graph.
	 * @param G The Graph.
	 */
	public TransitiveAndReflexiveClosure(Digraph G) {
		reachable = new boolean[G.V()][G.V()];
		
		for(int v = 0; v < G.V(); v++)
			for(int w : G.adj(v)) {
				reachable[v][w] = true;
			}
			
		for(int i = 0; i < G.V(); i++) {
			reachable[i][i] = true;
			for(int j = 0; j < G.V(); j++)
				for(int k = 0; k < G.V(); k++)
					if(reachable[j][i] && reachable[i][k])
						reachable[j][k] = true;
		}
	}
	
	/**
	 * 
	 * @param v Start vertex.
	 * @param w End vertex.
	 * @return true if w is reachable from v.
	 */
	public boolean reachable(int v, int w) {
		return reachable[v][w];
	}
	
	/**
	 * A unit test client program
	 * @param args
	 */
	public static void main(String[] args) {
		String filename = "tinyDAG.txt";
		Digraph G = new Digraph(new In(filename));
		TransitiveAndReflexiveClosure TRC = new TransitiveAndReflexiveClosure(G);
		
		for(int v = 0; v < G.V(); v++) {
			StdOut.print(v + ": ");
			for(int w = 0; w < G.V(); w++) {
				if(TRC.reachable(v, w))
					StdOut.print(w + " ");
			}
		StdOut.println();
		}
	}

}
