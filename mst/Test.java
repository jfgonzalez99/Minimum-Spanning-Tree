package mst;
import java.io.FileWriter;
import java.io.IOException;

import mst.objects.*;

class Test {
    static int numtrials;

    public static void main(String[] args) {
        int n = Integer.parseInt(args[0]);
        numtrials = Integer.parseInt(args[1]);
        int d = Integer.parseInt(args[2]);

        // Make sure arguments are valid
        if (n < 1) {
            throw new IllegalArgumentException("There must be at least one vertex to form a complete graph.");
        }
        if (numtrials < 1) {
            throw new IllegalArgumentException("You must run at least one trial.");
        }
        if (d < 0 || d == 1 || d > 4) {
            throw new IllegalArgumentException("The dimension must be 0, 2, 3, or 4");
        }

        // for (int i = 0; i < numtrials; i++) {
        // CompleteGraph testGraph = new CompleteGraph(n, d);
        // testGraph.printEdges();
        // System.out.println();

        // testGraph.prim();
        // testGraph.printMST();
        // System.out.println();
        // }

        runTrials();
    }

    private static double trialsAverage(int n, int d) {
        double sum = 0;
        for (int i = 0; i < numtrials; i++) {
            CompleteGraph g = new CompleteGraph(n, d);
            g.prim();
            sum += g.treeSize();
        }
        return sum / numtrials;
    }

    private static void runTrials() {
        // Write results to averages.csv
        FileWriter csvWriter;
        try {
            csvWriter = new FileWriter("../averages.csv");
            csvWriter.append("n, d=0, d=2, d=3, d=4\n");

            int[] sizes = {128, 256, 512, 1024, 2048, 4096, 8192, 16384, 32768, 65536, 131072, 262144};
            int[] dimensions = {0,2,3,4};

            for (int size : sizes) {
                csvWriter.append(size + ", ");
                for (int dimension : dimensions) {
                    double average = trialsAverage(size, dimension);
                    csvWriter.append(average + ", ");
                }
                csvWriter.append("\n");
            }

            csvWriter.flush();
            csvWriter.close();
        } catch (IOException e) {
            // Catch the exception where file does not exist
            e.printStackTrace();
        }
        
    }
}