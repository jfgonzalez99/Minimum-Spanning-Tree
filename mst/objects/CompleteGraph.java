/**
 * CompleteGraph constructs a complete, undirected weighted graph on n vertices 
 * with random edge weights depending on the number of dimensions the user 
 * inputs.
 * 
 * @author Justin Gonzalez
 * @author Carlos Robles
 */
package mst.objects;

public class CompleteGraph {
    public int numpoints;
    public int dimension;
    

    // Complete Graph Class Constructor
    public CompleteGraph(int n, int d) {
        numpoints = n;
        dimension = d;
    }
}