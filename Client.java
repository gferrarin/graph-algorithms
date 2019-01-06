import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * Represents some client that needs to use the Graph API and WidestPath API to come up with an
 * answer for their hypothetical business.
 * <p/>
 * Imagine that you work for an Internet Service Provider and you have some data on the links
 * between their routers. This includes which pairs of routers have a link between them
 * and---importantly---the bandwidths of those links.
 * <p/>
 * For business purposes described in the writeup, you're interested in finding the bottleneck
 * bandwidth of the maximum bottleneck path between arbitrary routers, represented by their IP
 * addresses.
 * <p/>
 * In case you haven't seen one before, an IP (Internet Protocol) address is a numerical label
 * assigned to devices on the internet. They're typically (IPv4) 32-bit integers formatted as four
 * dot-delimited 8-bit decimal integers, like "158.130.92.168", for example. These formatting
 * details shouldn't matter in this assignment; just treat them as strings.
 *
 * @author Max Scheiber (scheiber), Lewis Ellis (ellisl), 15sp
 * @author davix
 */
public class Client {
    /**
     * Initializes the necessary data structures to perform a proper
     * {@link #getBottleneck(String, String)} computation.
     * <p/>
     * Do NOT modify this constructor header.
     *
     * @param links an array of strings with each element representing a link
     * between two routers and its bandwidth. Each string is a comma-separated
     * 3-tuple of the form "{@code src,tgt,bandwidth}". Note that there are no
     * braces or parenetheses surrounding the tuple and there is no whitespace
     * around commas. The {@code src} and {@code tgt} are IP addresses and
     * {@code bandwidth} is a positive integer. The links array will never be
     * null, and every element will be well-formed and non-null.
     */
	private Graph g;
	private Map<String, Integer> vert;
	
    public Client(String[] links) {
    	Set<Edge> toAdd = new HashSet<Edge>();
    	int count = 0;
    	vert = new HashMap<String, Integer>();
    	
        for (int i = 0; i < links.length; i++) {
        	String[] decode = links[i].split(",");
        	if(!vert.containsKey(decode[0])) {
        		vert.put(decode[0], count);
        		count++;
        	}
        	
        	if(!vert.containsKey(decode[1])) {
        		vert.put(decode[1], count);
        		count++;
        	}
        	
        	Edge add = new Edge(vert.get(decode[0]), vert.get(decode[1]), 
        			Integer.parseInt(decode[3]));
        }
        g = new Graph(vert.size());
        for (Edge e : toAdd) {
        	g.addEdge(e.u, e.v, e.weight);
        }
    }

    /**
     * Returns the bottleneck bandwidth of a maximum bottleneck path from router src to router tgt.
     * You may assume that the graph is connected, so every node has at least one path to every
     * other node. You may assume that all inputs are valid (i.e., {@code src} and {@code tgt} are
     * in the graph, and they are never null).
     * <p/>
     * Do NOT modify this method header.
     *
     * @param src the starting IP address
     * @param tgt the ending IP address
     * @return the weight of the bottleneck along a maximum bottleneck path from {@code src} to
     * {@code tgt}, or 0 if {@code src == tgt}
     */
    public int getBottleneck(String src, String tgt) {
    	int u = vert.get(src);
    	int v= vert.get(tgt);
    	
        List<Integer> maxPath = WidestPath.getWidestPath(g, u, v);
        
        double bottleneck = Double.POSITIVE_INFINITY;
        
        int prev = maxPath.get(0);
        int current = 0;
        
        for (int i = 1; i < maxPath.size(); i++) {
        	current = maxPath.get(i);
        	
        	if (g.getWeight(prev, current) < bottleneck) {
        		bottleneck = g.getWeight(prev, current);
        	}
        }
        return (int) bottleneck;
    }
}
