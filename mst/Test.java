package mst;
import java.io.FileWriter;
import java.io.IOException;

import mst.objects.*;

class Test {
    static int numtrials;

    public static void main(String[] args) {
        int testcode = Integer.parseInt(args[0]);
        int n = Integer.parseInt(args[1]);
        numtrials = Integer.parseInt(args[2]);
        int d = Integer.parseInt(args[3]);

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
        
        if (testcode == 1) {
            runTrials();
        }
        else {
            double avg = trialsAverage(n, d, false);
            System.out.println(avg + " " + n + " " + numtrials + " " + d);
        }
    }

    private static double trialsAverage(int n, int d, boolean verbose) {
        double sum = 0;
        for (int i = 0; i < numtrials; i++) {
            CompleteGraph g = new CompleteGraph(n, d);
            g.prim();
            sum += g.treeSize();
            if (verbose) {
                System.out.println("Trial " + (i+1) + ": " + g.treeSize());
            }
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
                System.out.print(size + ", ");
                for (int dimension : dimensions) {
                    double average = trialsAverage(size, dimension, false);
                    csvWriter.append(average + ", ");
                    System.out.print(average + ", ");
                }
                csvWriter.append("\n");
                System.out.print("\n");
            }

            csvWriter.flush();
            csvWriter.close();
        } catch (IOException e) {
            // Catch the exception where file does not exist
            e.printStackTrace();
        }
        
    }
}