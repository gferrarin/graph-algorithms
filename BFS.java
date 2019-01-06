import java.util.ArrayList;
import java.util.Collections;
import java.util.Deque;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Queue;
import java.util.Set;

/**
 * Facade for computing an unweighted shortest path between two vertices in a graph. We represent
 * paths as ordered lists of integers corresponding to vertices.
 *
 * @author Max Scheiber (scheiber), Lewis Ellis (ellisl), 15sp
 * @author davix
 */
public final class BFS {
    private BFS() {
    }

    /**
     * Returns a shortest path from {@code src} to {@code tgt} by executing a breadth-first search.
     * If there are multiple shortest paths, this method may return any one of them.
     * <p/>
     * Do NOT modify this method header.
     *
     * @param g the graph
     * @param src the vertex from which to search
     * @param tgt the vertex to find via {@code src}
     * @return an ordered list of vertices on a shortest path from {@code src} to {@code tgt}, or an
     * empty list if there is no path from {@code src} to {@code tgt}. The first element should be
     * {@code src} and the last element should be {@code tgt}. If {@code src == tgt}, a list
     * containing just that element is returned.
     * @throws IllegalArgumentException if {@code src} or {@code tgt} is not in the graph
     * @throws IllegalArgumentException if the specified graph is null
     */
    public static List<Integer> getShortestPath(Graph g, int src, int tgt) {
    	if (g == null) {
        	throw new IllegalArgumentException();
        }
    	
    	if (src < 0 || tgt < 0 || src > (g.getSize() - 1) || tgt > (g.getSize() - 1)) {
        	throw new IllegalArgumentException();
        }
        
        List<Integer> output = new ArrayList<Integer>();
        
        if (src == tgt) {
        	output.add(src);
        	return output;
        }
         
        else {
        	Map<Integer, Integer> paths = new HashMap<Integer, Integer>();
        	Deque<Integer> q = new LinkedList<Integer>();
        	q.addLast(src);
        	
        	int current = src; 
        	while (current != tgt){
 
        		int parent = q.removeFirst();
        		
        		for (int i : g.getNeighbors(parent)) {
        			if (!paths.containsKey(i)) {
        				q.addLast(i);
        				paths.put(i, parent);
        			}
        		}
        		if (q.isEmpty()) {
        			return output;
        		}
        		
        		else {
        			current = q.getFirst();
        		}
        	}
        	
        	while (current != src) {
        		output.add(current);
        		current = paths.get(current);
        	}
        	output.add(src);
        	Collections.reverse(output);
        	return output;
        }
    }
}
