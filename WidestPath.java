import java.util.ArrayList;
import java.util.List;

/**
 * Returns a widest path between two vertices in a graph. A widest path between two vertices
 * maximizes the weight of the minimum-weight edge in the path.
 * <p/>
 * There are multiple ways to solve this problem, but consider how you can do it with what you've
 * already built---BFS and Kruskal's.
 *
 * @author Max Scheiber (scheiber), Lewis Ellis (ellisl), 15sp
 * @author davix
 */
public final class WidestPath {
    private WidestPath() {
    }

    /**
     * Computes a widest path from {@code src} to {@code tgt} for a specified graph. If there are
     * multiple widest paths, this method may return any one of them.
     * <p/>
     * Do NOT modify this method header.
     *
     * @param g the graph
     * @param src the vertex from which to start the search
     * @param tgt the vertex to find via {@code src}
     * @return an ordered list of vertices on a widest path from {@code src} to {@code tgt}, or an
     * empty list if there is no such path. The first element is {@code src} and the last element is
     * {@code tgt}. If {@code src == tgt}, a list containing just that element is returned.
     * @throws IllegalArgumentException if {@code src} or {@code tgt} is not in the graph
     * @throws IllegalArgumentException if the specified graph is null
     */
    public static List<Integer> getWidestPath(Graph g, int src, int tgt) {
        if (g == null) {
        	throw new IllegalArgumentException();
        }
        
        if (src < 0 || tgt < 0 || src > (g.getSize() - 1) || tgt > (g.getSize() - 1)) {
        	throw new IllegalArgumentException();
        }
        
        if (src == tgt) {
        	List<Integer> output = new ArrayList<Integer>();
        	output.add(src);
        	return output;
        }
        
        Graph reverse = new Graph(g.getSize());
        for (Edge e : g.getEdges()) {
        	reverse.addEdge(e.u, e.v, (e.weight * -1));
        }
        
        Graph reverse_MST = Kruskal.getMST(reverse);
        return BFS.getShortestPath(reverse_MST, src, tgt);
    }
}
