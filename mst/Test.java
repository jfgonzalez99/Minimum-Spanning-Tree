package mst;
import mst.objects.CompleteGraph;

class Test {
    public static void main(String[] args) {
        int n = Integer.parseInt(args[0]);
        int numtrials = Integer.parseInt(args[1]);
        int d = Integer.parseInt(args[2]);

        // Make sure arguments are valid
        if (n < 1) {
            throw new IllegalArgumentException("There must be at least one vertex to form a complete graph.");
        }
        if (numtrials < 1) {
            throw new IllegalArgumentException("You must run at least one trial.");
        }
        if (d < 1 || d > 4) {
            throw new IllegalArgumentException("The dimension must be at lest 1 and no more than 4.");
        }

        for (int i = 0; i < numtrials; i++) {
            CompleteGraph testGraph = new CompleteGraph(n, d);
            testGraph.printEdges();
            System.out.println();
        }
    }
}