# Minimum Spanning Tree

Programming Assignment 1 for CS 124

*Carlos Robles and Justin Gonzalez*

## How to Run

To run the algorithm on random complete graphs of size `numpoints` and dimension `dimension` and return the average MST size over `numtrials`:

```./randmst.sh 0 numpoints numtrials dimension```

## Output

```average_tree_size numpoints numtrials dimension```

## Write Results to CSV File

To run the algorithm on random complete graphs of size `numpoints = [128, 256, 512, 1024, 2048, 4096, 8192, 16384, 32768, 65536, 131072, 262144]` and `dimension = [0, 2, 3, 4]` and export the average MST size over `numtrials` to `averages.csv` run the following command:

```./randmst.sh 1 2 numtrials 2```
