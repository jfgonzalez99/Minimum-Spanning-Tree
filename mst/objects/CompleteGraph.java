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

    // /**
    //  * Creates a jagged matrix representation of the edges in the graph
    //  * @return edge matrix
    //  */
    // private double[][] makeEdgeMatrix2() {
    //     double[][] e = new double[numpoints - 1][];
    //     for (int i = 0; i < numpoints - 1; i++) {
    //         e[i] = new double[numpoints - 1 - i];
    //         for (int j = 0; j < numpoints - 1 - i; j++) {
    //             e[i][j] = calcWeight(i, j);
    //         }
    //     }
    //     return e;
    // }

    /**
     * Creates a matrix representation of the edges in the graph
     * @return edge matrix
     */
    private double[][] makeEdgeMatrix() {
        double[][] e = new double[numpoints][numpoints];
        for (int i = 0; i < numpoints - 1; i++) {
            for (int j = i+1; j < numpoints; j++) {
                e[i][j] = calcWeight(i, j);
                e[j][i] = e[i][j];
            }
        }
        return e;
    }

    Random rand = new Random(1234);

    /**
     * Calculates the weight of edge between two vertices
     * @param u
     * @param v
     * @return weight
     */
    private double calcWeight(int u, int v) {
        if (dimension == 0) {
            // Return random number between 0 and 1
            return rand.nextDouble();
        }
        else {
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
    }

    /**
     * Calculates the Euclidean distance between two vertices
     * @param p1
     * @param p2
     * @return distance
     */
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
    public double[][] prim() {
        // List of edges in MST
        mst = new double[numpoints - 1][3];

        // Initialize list implementation
        HeapList h = new HeapList(numpoints);
        // Set first vertex to be starting vertex
        h.insert(0,0);

        MySet v_minus_s = new MySet(numpoints);
        v_minus_s.remove(0);

        double[] dist = new double[numpoints];
        double[] prev = new double[numpoints];
        for (int i = 0; i < numpoints; i++) {
            dist[i] = Double.POSITIVE_INFINITY;
            prev[i] = -1; // Here we treat -1 like null
        }

        dist[0] = 0;
        while (!h.isEmpty()) {
            int v = h.deletemin();
            v_minus_s.remove(v);
            for (int w = 0; w < numpoints; w++) {
                if (v_minus_s.contains(w)) {
                    if (dist[w] > edges[v][w]) {
                        dist[w] = edges[v][w];
                        prev[w] = v;
                        h.insert(w, dist[w]);
                    }
                }
            }
        }

        for (int i = 1; i < numpoints; i++) {
            mst[i - 1] = new double[] {prev[i], i, dist[i]};
        }

        return mst;
    }

    public void printMST() {
        for (int i = 0; i < numpoints - 1; i++) {
            for (int j = 0; j < 3; j++) {
                System.out.print(mst[i][j] + "  ");
            }
            System.out.print("\n");
        }
    }
}