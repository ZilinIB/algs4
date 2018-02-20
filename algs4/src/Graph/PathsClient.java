package Graph;

import edu.princeton.cs.algs4.Graph;
import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.StdOut;

/**
* Unit test client program for Paths. (DepthFirstPaths & BreadthFirstPaths)
* 
* (Example for BFS)
* % java PathsClient tinyCG.txt 0
* 0 to 0: 0
* 0 to 1: 0-1
* 0 to 2: 0-2
* 0 to 3: 0-2-3
* 0 to 4: 0-2-4
* 0 to 5: 0-5
* 
* @author Zilin
*/
public class PathsClient {

	public static void main(String[] args) {
		Graph G = new Graph(new In(args[0]));
		int s = Integer.parseInt(args[1]);
		Paths search = new DepthFirstPaths(G, s);
//		Paths search = new BreadthFirstPaths(G, s);
		
		for(int v = 0; v < G.V(); v++) {
			StdOut.print(s + " to " + v + ": ");
			if(search.hasPathTo(v)) {
				for(int x : search.pathTo(v))
					if(x == s) StdOut.print(x);
					else StdOut.print("-" + x);
			StdOut.println();
			}
		}
	}
}
