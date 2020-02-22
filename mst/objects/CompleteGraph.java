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
    public double[][] edges;
    public double[][] mst;
    
    // Complete Graph Class Constructor
    public CompleteGraph(int n, int d) {
        numpoints = n;
        dimension = d;
        edges = makeEdgeMatrix();
    }

    // Creates a matrix representation of the edges in the graph
    private double[][] makeEdgeMatrix() {
        double[][] e = new double[numpoints][numpoints];
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
    private double calcWeight(int u, int v) {
        if (dimension == 1) {
            // Return random number between 0 and 1
            return rand.nextDouble();
        }
        else if (dimension >= 2 && dimension <= 4 ) {
            // p1 and p2 hold the coordinates of u and v
            double [] p1 = new double[dimension];
            double [] p2 = new double[dimension];
            for (int i = 0; i < dimension; i++) {
                // Give p1 and p2 random coordinates
                p1[i] = rand.nextDouble();
                p2[i] = rand.nextDouble();
            }
            return distance(p1,p2);
        }
        // Returns 0 if dimension is greater than 4
        else {
            return 0;
        }
    }

    // Calculates the Euclidean distance between two vertices
    private double distance(double[] p1, double[] p2) {
        double sum = 0;
        for (int i = 0; i < dimension; i++) {
            // Square the difference between each coordinate
            sum += Math.pow(p1[i] - p2[i], 2);
        }
        return Math.pow(sum, 0.5);
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
     * Remove edges from graph that are unlikely to be used in the spanning  
     * tree.
     */
    public void pruneEdges() {

    }

    /**
     * Implementation of Prim's algorithm for constructing the minimum spanning 
     * tree of a complete graph. Generates a list of edges that connect all 
     * vertices with the minimum possible edge weight.
     */
    public void prim() {
        mst = new double[numpoints - 1][3];
        double[][] h = new double[numpoints - 1][3];
        double[][] s = new double[numpoints - 1][3];

        double[] dist = new double[numpoints];
        double[] prev = new double[numpoints];
        for (int i = 0; i < numpoints; i++) {
            dist[i] = Double.POSITIVE_INFINITY;
            prev[i] = -1; // Here we treat -1 like null
        }
    }

}