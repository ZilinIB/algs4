package Graph;

import edu.princeton.cs.algs4.Bag;
import edu.princeton.cs.algs4.Graph;
import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.StdOut;

/**
* This class is the unit test for CC.
* 
* %java CCClient tinyG.txt
* 3 components
* 6 5 4 3 2 1 0 
* 8 7 
* 12 11 10 9 
* 
* @author Zilin
*/
public class CCClient {
	
	public static void main(String[] args) {
		Graph G = new Graph(new In(args[0]));
		CC cc = new CC(G);
		
		int M = cc.count();
		StdOut.println(M + " components");
		
		Bag<Integer>[] components;
		components = (Bag<Integer>[]) new Bag[M];
		for(int i = 0; i < M; i++)
			components[i] = new Bag<Integer>();
		for(int v = 0; v < G.V(); v++)
			components[cc.id(v)].add(v);
		for(int i = 0; i < M; i++) {
			for(int v: components[i])
				StdOut.print(v + " ");
			StdOut.println();
		}
	}

}
