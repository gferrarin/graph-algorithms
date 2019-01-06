import java.util.HashMap;
import java.util.Map;
import java.util.NoSuchElementException;

/**
 * A data structure for tracking a set of elements partitioned into disjoint subsets. We can
 * <em>union</em> these subsets together and then <em>find</em> which set an element belongs to. In
 * particular, we can determine when two elements belong to the same set.
 *
 * @author Max Scheiber (scheiber), Lewis Ellis (ellisl), 15sp
 * @author davix
 */
public class UnionFind {
    /**
     * Initializes a new union-find structure for the specified number of elements.
     * <p/>
     * Do NOT modify this constructor header.
     *
     * @param n the number of singleton sets with which to start
     * @throws IllegalArgumentException if {@code n} is negative
     */
	
	private Map<Integer, Integer> tree;
	private Map<Integer, Integer> rank;
	private int n;
	
    public UnionFind(int n) {
        if (n < 0) { 
        	throw new IllegalArgumentException();
        }
        
        this.n= n;
        this.tree = new HashMap<Integer, Integer>();
        this.rank = new HashMap<Integer, Integer>();
        for (int i = 0; i < n; i++) {
        	tree.put(i, i);
        	rank.put(i, 0);
        }
    }

    /**
     * Joins two sets.
     * <p/>
     * Do NOT modify this method header.
     *
     * @param u a vertex in the first set
     * @param v a vertex in the second set
     * @throws NoSuchElementException if {@code u < 0} or {@code u >= n} or {@code v < 0} o {@code v
     * >= n}
     */
    public void union(int u, int v) {
        if (u < 0 || u >= n || v < 0 || v>= n) {
        	throw new NoSuchElementException();
        }
        
        int ru = find(u);
        int rv = find(v);
        
        if (ru != rv) {
        	int ruRank = rank.get(ru);
        	int rvRank = rank.get(rv);
        	
        	if (ruRank == rvRank) {
        		/*System.out.println("hey");
        		System.out.println("hello: " + "parent:" + rv + ",child:" + ru); */
            	tree.put(rv, ru);
            	rank.put(ru, rank.get(ru) + 1);
            }
            
            else if (ruRank < rvRank) {
            	tree.put(ru, rv);
            }
            
            else {
            	tree.put(rv, ru);
            }	   
        }
    }
    
    public int rank(int i) {
    	return rank.get(i);
    }

    /**
     * Finds which set a vertex belongs to. We represent a vertex's set by the index of its tree's
     * root node, as discussed in the writeup. If for some {@code v}, {@code find(u) == find(v)},
     * then {@code u} and {@code v} are in the same set.
     * <p/>
     * Do NOT modify this method header.
     *
     * @param u the vertex
     * @return the root of the set to which the input vertex belongs
     * @throws NoSuchElementException if {@code u < 0} or {@code u >= n}
     */
    public int find(int u) {
        if (u < 0 || u >= n) {
        	throw new NoSuchElementException();
        }
        
        int parent = tree.get(u);
        //System.out.println("u:" + u + ",parent:" + tree.get(u));
        if (u != parent) {
        	tree.put(u, find(parent));
        }
        
        return tree.get(u);
    }
}
