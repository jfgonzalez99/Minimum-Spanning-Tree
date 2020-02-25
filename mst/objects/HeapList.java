package mst.objects;

public class HeapList {
    int size;
    double[] list;
    int length = 0;

    public HeapList(int n) {
        size = n;
        list = new double[size];
        for (int i = 0; i < size; i++) {
            list[i] = Double.POSITIVE_INFINITY;
        }
    }

    /**
     * Inserts an element into the heap list
     */
    public void insert(int vertex, double value) {
        list[vertex] = value;
        length += 1;
    }

    /**
     * Returns element with smallest value and removes from heap
     */
    public int deletemin() {
        if (length > 0) {
            // Find min
            int minIndex = 0;
            double minVal = Double.POSITIVE_INFINITY;
            for (int i = 0; i < size; i++) {
                if (list[i] < minVal) {
                    minIndex = i;
                    minVal = list[i];
                }
            }
            list[minIndex] = Double.POSITIVE_INFINITY;
            // Decrease length
            length -= 1;
            return minIndex;
        }
        else {
            throw new IllegalArgumentException("Heap is empty");
        }
    }

    /**
     * Returns whether heap is empty or not
     */
    public boolean isEmpty() {
        if (length == 0) {
            return true;
        }
        return false;
    }

    /**
     * Print the "heap" list
     */
    public void printHeap() {
        for (int i = 0; i < size; i++) { 
            System.out.println(i + "  " + list[i]);
        }
    }
}