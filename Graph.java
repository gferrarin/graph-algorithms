import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.NoSuchElementException;
import java.util.Set;

/**
 * Contains the API necessary for an undirected, (optionally) weighted graph. We
 * use ints to identify vertices (i.e. the vertices are labeled 0 through n-1).
 * <p/>
 * Clients of this Graph class are responsible for maintaining their own mapping between the graph's
 * integer indices and any of their own associated data, as you will do yourself in Client.java.
 * <p/>
 * We call the graph "optionally weighted" because it can be used by algorithms that use weights
 * (like Kruskal's) and by algorithms that do not (like BFS). An algorithm like BFS would simply
 * ignore any weights present.
 *
 * @author Max Scheiber (scheiber), Lewis Ellis (ellisl), 15sp
 * @author davix
 */
public class Graph {
    /**
     * Initializes a graph of size {@code n}. All valid vertices in this graph thus have integer
     * indices in the half-open range {@code [0, n)}.
     * <p/>
     * Do NOT modify this constructor header.
     *
     * @param n the number of vertices in the graph
     * @throws IllegalArgumentException if {@code n} is negative
     */
	
	private int n;
	private HashMap<Integer, HashMap<Integer, Integer>> edges;
	
    public Graph(int n) {
    	if (n < 0) {
    		throw new IllegalArgumentException();
    	}
    	
    	edges= new HashMap<Integer, HashMap<Integer, Integer>>();
    	this.n = n;
    	
    	for (int i = 0; i < n; i++) {
    		this.edges.put(i, new HashMap<Integer, Integer>());
    	}
    	
    }

    /**
     * Returns the number of vertices in the graph.
     * <p/>
     * Do NOT modify this method header.
     *
     * @return the number of vertices in the graph
     * @implSpec This method should run in O(1) time.
     */
    public int getSize() {
        return this.n;
    }

    /**
     * Returns the set of edges in this graph. Since this is an undirected graph, if an edge {@code
     * u-v} exists in the graph, be sure that you return either an Edge object for the {@code u-v}
     * direction <em>or</em> an {@link Edge} object for the {@code v-u} direction, but not both.
     * <p/>
     * Do NOT modify this method header.
     *
     * @return a set containing this graph's edges
     * @implSpec This method should run in not more than expected O(|E|) time.
     */
    public Set<Edge> getEdges() {
        Set<Edge> build = new HashSet<Edge>();
        
        for (int u : edges.keySet()) {
        	if (edges.get(u).size() > 0) {
        		for (int v: edges.get(u).keySet()) {
        			Edge e = new Edge(u, v, edges.get(u).get(v));
        			build.add(e);
        		}
        	}
        }
        return build;
    }

    /**
     * Determines if an edge exists between two vertices.
     * <p/>
     * Do NOT modify this method header.
     *
     * @param u a vertex
     * @param v a vertex
     * @return {@code true} if the {@code u-v} edge is in this graph
     * @throws IllegalArgumentException if a specified vertex does not exist
     * @implSpec This method should run in expected O(1) time.
     */
    public boolean hasEdge(int u, int v) {
        if (u > (n-1) || v > (n-1)) {
        	throw new IllegalArgumentException();
        }
        
        return (this.edges.get(u).containsKey(v));
    }

    /**
     * Creates an edge between {@code u} and {@code v} if it does not already exist. A call to this
     * method should <em>not</em> modify the edge weight if the {@code u-v} edge already exists.
     * <p/>
     * Do NOT modify this method header.
     *
     * @param u one vertex to connect
     * @param v the other vertex to connect
     * @param weight the edge weight
     * @return {@code true} if the graph changed as a result of this call, false
     * otherwise (i.e. if the edge is already present)
     * @throws IllegalArgumentException if a specified vertex does not exist
     * @implSpec This method should run in expected O(1) time.
     */
    public boolean addEdge(int u, int v, int weight) {
    	if (u > (n-1) || v > (n-1)) {
        	throw new IllegalArgumentException();
        }
    	
    	if (u > v) {
            int tmp = u;
            u = v;
            v = tmp;
        }
    	
    	// check if edge already exists
    	if (this.edges.get(u).containsKey(v)) {
    		/*System.out.println(u);
    		System.out.println(v);
    		
    		System.out.print(this.edges.get(u).get(v)); */
    		return false;
    	}
    	
    	// if edge does not exist, add it
    	else {
    		HashMap<Integer, Integer> alter = this.edges.get(u);
    		alter.put(v, weight);
    		this.edges.put(u, alter);
    		
    		HashMap<Integer, Integer> alter2 = this.edges.get(v);
    		alter2.put(u, weight);
    		this.edges.put(v, alter2);
    		System.out.println("uvw;" + u + v + this.edges.get(u).get(v));
    		System.out.println("vuw;" + v + u +  this.edges.get(v).get(u));
    		return true;
    	}
    }

    /**
     * Returns the weight of an edge.
     * <p/>
     * Do NOT modify this method header.
     *
     * @param u a vertex
     * @param v a vertex
     * @return the edge weight of {@code u-v}
     * @throws NoSuchElementException if the {@code u-v} edge does not exist
     * @throws IllegalArgumentException if a specified vertex does not exist
     * @implSpec This method should run in expected O(1) time.
     */
    public int getWeight(int u, int v) {
    	if (u > (n-1) || v > (n-1)) {
        	throw new IllegalArgumentException();
        }
    	
    	if (!this.edges.get(u).containsKey(v)) {
    		throw new NoSuchElementException();
    	}
    	
    	return this.edges.get(u).get(v); 
    }

    /**
     * Returns the neighbors of the specified vertex.
     * <p/>
     * Do NOT modify this method header.
     *
     * @param v the vertex
     * @return all neighbors of the specified vertex or an empty set if there
     * are no neighbors
     * @throws IllegalArgumentException if the specified vertex does not exist
     * @implSpec This method should run in expected O(deg(v)) time.
     */
    public Set<Integer> getNeighbors(int v) {
    	if (v > (n-1)) {
        	throw new IllegalArgumentException();
        }
    	
    	return this.edges.get(v).keySet();
    }
}
