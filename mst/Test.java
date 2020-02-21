package mst;
import mst.objects.CompleteGraph;

class Test {
    public static void main(String[] args) {
        int n = Integer.parseInt(args[0]);
        int numtrials = Integer.parseInt(args[1]);
        int d = Integer.parseInt(args[2]);

        CompleteGraph testGraph = new CompleteGraph(n, d);
        testGraph.printEdges();
    }
}