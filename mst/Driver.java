package mst;
import java.io.FileWriter;
import java.io.IOException;

import mst.objects.*;

class Driver {
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
        else if (testcode == 2) {
            timeTrials(d);
        }
        else {
            double avg = trialsAverage(n, d, false);
            System.out.println(avg + " " + n + " " + numtrials + " " + d);
        }
    }

    /**
     * Calculates average tree size over numtrials trials
     * @param n : numpoints
     * @param d : dimension
     * @param verbose : true if you want to print results of each trial
     */
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

    /**
     * Runs trials and writes average tree sizes to csv
     */
    private static void runTrials() {
        FileWriter csvWriter;
        try {
            // Write results to averages.csv
            csvWriter = new FileWriter("averages.csv");
            csvWriter.append("n, d=0, d=2, d=3, d=4\n");

            int[] sizes = {128, 256, 512, 1024, 2048, 4096, 8192, 16384, 32768, 
                           65536, 131072, 262144};
            int[] dimensions = {0,2,3,4};

            for (int size : sizes) {
                csvWriter.append(size + ", ");
                System.out.print(size + ", ");
                for (int dimension : dimensions) {
                    double average = trialsAverage(size, dimension, false);
                    if (dimension == dimensions[dimensions.length - 1]) {
                        csvWriter.append(average + "\n");
                        System.out.print(average + "\n");
                    }
                    else {
                        csvWriter.append(average + ", ");
                        System.out.print(average + ", ");
                    }
                }
            }

            csvWriter.flush();
            csvWriter.close();
        } catch (IOException e) {
            // Catch the exception where file does not exist
            e.printStackTrace();
        }
    }

    /**
     * Times how many nanoseconds it takes to do a single trial of each size 
     * given dimension d
     * @param d
     */
    private static void timeTrials(int d) {
        int[] sizes = {128, 256, 512, 1024, 2048, 4096, 8192, 16384, 32768, 
                       65536, 131072, 262144};
        
        for (int size : sizes) {
            long startTime = System.nanoTime();

            CompleteGraph g = new CompleteGraph(size, d);
            g.prim();

            long endTime = System.nanoTime();
            long totalTime = (endTime - startTime);
            System.out.println(size + ", " + totalTime);
        }
    }
}