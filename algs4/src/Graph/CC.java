package Graph;

import edu.princeton.cs.algs4.Graph;

/**
* Find the connected component of the Graph using and put the connected vertices in the same bag.
* It supports 3 operations: check if two vertices are in the same CC, count the number of CC in the Graph,
* check the component identifier for a vertex.
* 
* This implementation based mainly on DFS.
* 
* @author Zilin
*/
public class CC {
	private boolean[] marked;
	private int[] id;
	private int count;
	
	/**
	 * Initialize the CC object for Graph G.
	 * @param G The Graph.
	 */
	public CC(Graph G) {
		marked = new boolean[G.V()];
		id = new int[G.V()];
		count = 0;
		
		for(int i = 0; i < G.V(); i++) {
			if(!marked[i]) {
				dfs(G, i);
				count++;
			}
		}
	};
	
	/**
	 * Every complete running process produce a CC.
	 * @param G The Graph.
	 * @param v The vertex from which we start the dfs.
	 */
	private void dfs(Graph G, int v) {
		marked[v] = true;
		id[v] = count;
		for(int w : G.adj(v))
			if(!marked[w]) dfs(G, w);
	}

	/**
	 * Check if vertices v and w are in the same CC.
	 * @param v One of the vertices to be checked.
	 * @param w The same as v.
	 * @return True if v and w are in the same CC, false otherwise.
	 */
	public boolean connected(int v, int w) {
		return id[v] == id[w];
	};
	
	/**
	 * Return the number of CCs in this Graph.
	 * @return The number of CCs in this Graph.
	 */
	public int count() {
		return count;
	};
	
	/**
	 * Return the component identifier of v.
	 * @param v The query vertex.
	 * @return The component identifier of v.
	 */
	public int id(int v) {
		return id[v];
	};
}
