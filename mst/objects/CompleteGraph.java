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
    public double[][] vertices;

    Random rand = new Random();
    
    /**
     * Random complete graph constructor. Takes as input the size and dimension 
     * of the graph.
     * @param n
     * @param d
     */
    public CompleteGraph(int n, int d) {
        numpoints = n;
        dimension = d;
        vertices = new double[numpoints][dimension];
        randVertices();
    }

    /**
     * Fills vertex list with random coordinates
     */
    private void randVertices() {
        for (int i = 0; i < numpoints; i++) {
            for (int j = 0; j < dimension; j++) {
                // Give vertex i random coordinates
                vertices[i][j] = rand.nextDouble();
            }
        }
    }

    /**
     * Creates a matrix representation of the edges in the graph
     */
    public void makeEdgeMatrix() {
        double[][] e = new double[numpoints][numpoints];
        for (int i = 0; i < numpoints - 1; i++) {
            for (int j = i+1; j < numpoints; j++) {
                e[i][j] = distance(vertices[i], vertices[j]);
                e[j][i] = e[i][j];
            }
        }
        edges = e;
    }

    /**
     * Calculates the Euclidean distance between two vertices
     * @param p1
     * @param p2
     * @return distance
     */
    private double distance(double[] p1, double[] p2) {
        if (dimension == 0) {
            return rand.nextDouble();
        }
        else {
            double sum = 0;
            for (int i = 0; i < dimension; i++) {
                // Square the difference between each coordinate
                sum += Math.pow(p1[i] - p2[i], 2);
            }
            return Math.pow(sum, 0.5);
        }
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
                    double edge_vw = distance(vertices[v], vertices[w]);
                    if (dist[w] > edge_vw) {
                        dist[w] = edge_vw;
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

    /**
     * Calculates the sum of the edge weights in the minimum spanning tree
     * @return sum
     */
    public double treeSize() {
        double sum = 0;
        for (int i = 0; i < numpoints - 1; i++) {
            sum += mst[i][2];
        }
        return sum;
    }

    /**
     * Print the minimum spanning tree
     */
    public void printMST() {
        for (int i = 0; i < numpoints - 1; i++) {
            for (int j = 0; j < 3; j++) {
                System.out.print(mst[i][j] + "  ");
            }
            System.out.print("\n");
        }
    }
}