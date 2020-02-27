package mst.objects;

public class HeapList {
    int size;
    double[] list;
    int length = 0;

    /**
     * "Heap"List constructor. Takes as input the size of the HeapList.
     * @param n
     */
    public HeapList(int n) {
        size = n;
        list = new double[size];
        for (int i = 0; i < size; i++) {
            // Distance will never exceed 2
            list[i] = 100;
        }
    }

    /**
     * Inserts an element into the heap list
     */
    public void insert(int vertex, double value) {
        // Only increase heap size if it goes from null to a new value
        if (list[vertex] == 100) { 
            length += 1;
        }
        list[vertex] = value;
    }

    /**
     * Returns element with smallest value and removes from heap
     */
    public int deletemin() {
        if (length > 0) {
            // Find min
            int minIndex = 0;
            double minVal = 100;
            for (int i = 0; i < size; i++) {
                if (list[i] < minVal) {
                    minIndex = i;
                    minVal = list[i];
                }
            }
            list[minIndex] = 100;
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