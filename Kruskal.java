import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * Facade for finding the minimal spanning tree of a graph. Kruskal's algorithm uses a union-find
 * data structure to find the minimum spanning tree of a graph. A minimum spanning tree is
 * represented as just another graph.
 *
 * @author Max Scheiber (scheiber), Lewis Ellis (ellisl), 15sp
 * @author davix
 */
public final class Kruskal {
    private Kruskal() {
    }

    /**
     * Finds a minimum spanning tree of the specified graph. If there are multiple possible minimum
     * spanning trees, this method may return any one of them. If the specified graph is empty, then
     * an empty tree is returned. If the graph is disconnected, then a minimum spanning forest is
     * returned.
     * <p/>
     * Do NOT modify this method header.
     *
     * @param g the graph
     * @return a new {@link Graph} containing the same vertices as the specified graph, but with
     * only the edges on the minimum spanning tree.
     * @throws IllegalArgumentException if the specified graph is null
     */
    public static Graph getMST(Graph g) {
    	if (g == null) {
    		throw new IllegalArgumentException();
    	}
    	
    	UnionFind u = new UnionFind(g.getSize());
    	Set<Edge> s = g.getEdges();
    	List<Edge> l = new ArrayList<Edge>();
    	Set<Edge> output = new HashSet<Edge>();
    	
    	for (Edge e : s) {
    		l.add(e);
    	}
    	
    	Collections.sort(l);
    	
    	for (Edge e : l) {
    		if (u.find(e.u) != u.find(e.v)) {
    			output.add(e);
    			u.union(e.u, e.v);
    		}
    	}
    	
    	Graph mst = new Graph(g.getSize()); {
    		for (Edge e : output) {
    			mst.addEdge(e.u, e.v, e.weight);
    		}
    	}
    	return mst;
    }
}
