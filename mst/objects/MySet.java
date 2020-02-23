package mst.objects;

public class MySet {
    int size;
    int[] list;

    public MySet(int n) {
        size = n;
        list = new int[size];
        for (int i = 0; i < size; i++) {
            list[i] = i;
        }
    }

    /**
     * Removes n from the set
     * @param n
     */
    public void remove(int n) {
        if (n >= 0 && n < size) {
            // list[n] = -1 indicates that n is not in the list
            list[n] = -1;
        }
    }

    /**
     * Returns whether or not the set contains n
     * @param n
     * @return boolean
     */
    public boolean contains(int n) {
        if (list[n] == n) {
            return true;
        }
        return false;
    }
}