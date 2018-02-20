package Graph;

/**
* The API of paths implementation for graph
* 
* @author Zilin
*/
public interface Paths {
	boolean hasPathTo(int v);
	Iterable<Integer> pathTo(int v);
}
