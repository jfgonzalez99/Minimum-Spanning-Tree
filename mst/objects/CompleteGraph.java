/**
 * CompleteGraph constructs a complete, undirected weighted graph on n vertices 
 * with random edge weights depending on the number of dimensions the user 
 * inputs.
 * 
 * @author Justin Gonzalez
 * @author Carlos Robles
 */
package mst.objects;
import java.util.Random;
import java.lang.Math;

public class CompleteGraph {
    public int numpoints;
    public int dimension;
    public float[][] edges;
    public float[][] mst;
    
    // Complete Graph Class Constructor
    public CompleteGraph(int n, int d) {
        numpoints = n;
        dimension = d;
        edges = makeEdgeMatrix();
    }

    // Creates a matrix representation of the edges in the graph
    private float[][] makeEdgeMatrix() {
        float[][] e = new float[numpoints][numpoints];
        for (int i = 0; i < numpoints; i++) {
            for (int j = i; j < numpoints; j++) {
                // The distance between a vertex and itself is 0
                if (i == j) {
                    e[i][j] = 0;
                }
                else {
                    e[i][j] = calcWeight(i, j);
                    e[j][i] = 0;
                }
            }
        }
        return e;
    }

    Random rand = new Random();

    // Calculates weight of an edge
    private float calcWeight(int u, int v) {
        if (dimension == 1) {
            // Return random number between 0 and 1
            return rand.nextFloat();
        }
        else if (dimension >= 2 && dimension <= 4 ) {
            // p1 and p2 hold the coordinates of u and v
            float [] p1 = new float[dimension];
            float [] p2 = new float[dimension];
            for (int i = 0; i < dimension; i++) {
                // Give p1 and p2 random coordinates
                p1[i] = rand.nextFloat();
                p2[i] = rand.nextFloat();
            }
            return distance(p1,p2);
        }
        // Returns 0 if dimension is greater than 4
        else {
            return 0;
        }
    }

    // Calculates the Euclidean distance between two vertices
    private float distance(float[] p1, float[] p2) {
        float sum = 0;
        for (int i = 0; i < dimension; i++) {
            // Square the difference between each coordinate
            sum += Math.pow(p1[i] - p2[i], 2);
        }
        return (float) Math.pow(sum, 0.5);
    }

    /**
     * Print edge matrix for a CompleteGraph
     */
    public void printEdges() {
        for (int i = 0; i < numpoints; i++) {
            for (int j = 0; j < numpoints; j++) {
                System.out.print(edges[i][j] + "  ");
            }
            System.out.print("\n");
        }
    }

    /**
     * Implementation of Prim's algorithm for constructing the minimum spanning 
     * tree of a complete graph. Generates a list of edges that connect all 
     * vertices with the minimum possible edge weight.
     */
    public void prim() {
        mst = new float[numpoints - 1][3];
        
    }

}