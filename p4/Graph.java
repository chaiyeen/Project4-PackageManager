import java.util.List;
import java.util.Map;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Set;

/**
 * Filename:   Graph.java
 * Project:    p4
 * Authors:    
 * 
 * Directed and unweighted graph implementation
 */

public class Graph implements GraphADT {
	HashMap<String, List<String>> mapList = null;


	/*
	 * Default no-argument constructor
	 */ 
	public Graph() {
		mapList = new HashMap<String, List<String>>();
	}

	/**
	 * Add new vertex to the graph.
	 *
	 * If vertex is null or already exists,
	 * method ends without adding a vertex or 
	 * throwing an exception.
	 * 
	 * Valid argument conditions:
	 * 1. vertex is non-null
	 * 2. vertex is not already in the graph 
	 */
	public void addVertex(String vertex) {
		if (vertex==null || mapList.containsKey(vertex)) {
			return;
		}
		mapList.put(vertex, new ArrayList<>());       
	}

	/**
	 * Remove a vertex and all associated 
	 * edges from the graph.
	 * 
	 * If vertex is null or does not exist,
	 * method ends without removing a vertex, edges, 
	 * or throwing an exception.
	 * 
	 * Valid argument conditions:
	 * 1. vertex is non-null
	 * 2. vertex is not already in the graph 
	 */
	public void removeVertex(String vertex) {
		//If vertex is null or does not exist, return;
		if (vertex == null || !mapList.containsKey(vertex)) {
			return;
		}

		mapList.remove(vertex);

		for (Map.Entry<String,List<String>> entry : mapList.entrySet()) {
			entry.getValue().remove(vertex);
		}
	}

	/**
	 * Add the edge from vertex1 to vertex2
	 * to this graph. (edge is directed and unweighted)
	 * If either vertex does not exist, add the non-existing vertex to the graph and then create an edge.
	 * If the edge exists in the graph,
	 * no edge is added and no exception is thrown.
	 * 
	 * Valid argument conditions:
	 * 1. neither vertex is null
	 * 2. the edge is not in the graph
	 */
	public void addEdge(String vertex1, String vertex2) {
		//If either vertex does not exist, no edge is added and no exception is thrown.
		if(vertex1==null || vertex2 ==null) {
			return;
		}   

		if (mapList.containsKey(vertex1)) {
			//If the edge exists in the graph, no edge is added and no exception is thrown.
			if (mapList.get(vertex1).contains(vertex2))
				return;
			else {
				mapList.get(vertex1).add(vertex2);
			} 
		}
		if (!mapList.containsKey(vertex1)) {
			addVertex(vertex1);
			mapList.get(vertex1).add(vertex2);
		}
	}

	/**
	 * Remove the edge from vertex1 to vertex2
	 * from this graph.  (edge is directed and unweighted)
	 * If either vertex does not exist,
	 * or if an edge from vertex1 to vertex2 does not exist,
	 * no edge is removed and no exception is thrown.
	 * 
	 * Valid argument conditions:
	 * 1. neither vertex is null
	 * 2. both vertices are in the graph 
	 * 3. the edge from vertex1 to vertex2 is in the graph
	 */
	public void removeEdge(String vertex1, String vertex2) {
		//If either vertex does not exist, no edge is removed and no exception is thrown.
		if(vertex1==null || vertex2 ==null) {
			return;
		}


		if (mapList.containsKey(vertex1)) {
			//if an edge from vertex1 to vertex2 does not exist, no edge is added and no exception is thrown.
			if (!mapList.containsKey(vertex2))
				return;
			else {
				//edge is removed and no exception is thrown.
				mapList.get(vertex1).remove(vertex2);
			}
		}
	}   

	/**
	 * Returns a Set that contains all the vertices
	 * 
	 */
	public Set<String> getAllVertices() {      
		return mapList.keySet();
	}

	/**
	 * Get all the neighbor (adjacent) vertices of a vertex
	 *
	 */
	public List<String> getAdjacentVerticesOf(String vertex) {
		return mapList.get(vertex).subList(0, mapList.get(vertex).size());
	}

	/**
	 * Returns the number of edges in this graph.
	 */
	public int size() {
		int size=0;
		mapList.entrySet().iterator();

		for (Map.Entry<String,List<String>> entry : mapList.entrySet()) {
			size = size+ entry.getValue().size();
		}
		return size;
	}

	/**
	 * Returns the number of vertices in this graph.
	 */
	public int order() {
		return mapList.size();
	}
}