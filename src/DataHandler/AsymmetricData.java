package DataHandler;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

/**
 * AsymmetricData stores data of asymmetric type and also calculates the solution to the problem
 * by finding path based on nearest neighbor
 *
 * @author yagaa
 * @version 1.0.0
 * @see Data
 */
public class AsymmetricData extends Data {

    private ArrayList<ArrayList<Integer>> costs = new ArrayList<>();
    private ArrayList<Integer> path = new ArrayList<Integer>();
    private ArrayList<Double> distances = new ArrayList<Double>();
    private int numNodes = 0;
    private boolean pathFound = false;

    /**
     * Initialize AsymmetricData object
     */
    public AsymmetricData(String name) {
        super(name);
    }

    /**
     * Sets the number of nodes in this dataset
     * @param nodes Number of nodes
     */
    public void setNumNodes(int nodes) {
        numNodes = nodes;
    }

    /**
     * @return The number of nodes in this dataset
     */
    public int getNumNodes() {
        return numNodes;
    }

    /**
     * Adds one row of asymmetric data (ie) Cost needed for one node to reach all other nodes
     * @param row Cost array to reach all nodes from a particular node
     */
    public void addRow(ArrayList<Integer> row) {
        costs.add(row);
    }

    /**
     * Adds all rows of asymmetric data (ie) Cost needed for each node to reach all other nodes
     * @param data Cost array to reach all nodes from each node
     */
    public void addData(ArrayList<Integer> data) {
        for (int i=0; i<numNodes; i++) {
            ArrayList<Integer> temp = new ArrayList<>();
            for (int j=0; j<numNodes; j++) {
                if (i!=j) {
                    int idx = i * numNodes + j;
                    temp.add(data.get(idx));
                }
                else {
                    temp.add(10000000);
                }
            }
            costs.add(temp);
        }
    }

    /**
     * Finds path to be taken from each node to minimize cost based on Nearest Neighbor algorithm
     * @see <a href="https://en.wikipedia.org/wiki/Nearest_neighbour_algorithm">Wiki</a>
     */
    private void findPathNearestNeighbor() {
        path.add(0);
        int curRow = 0;
        Set<Integer> hash_Set = new HashSet<Integer>();
        hash_Set.add(0);
        for (int i=1; i<numNodes; i++) {
            int minDist = (int) 1e10;
            int minDistIdx = 0;
            for (int j=0; j<numNodes; j++) {
                if (!hash_Set.contains(j)) {
                    if (costs.get(curRow).get(j) < minDist) {
                        minDistIdx = j;
                        minDist = costs.get(i-1).get(j);
                    }
                }
            }
            curRow = minDistIdx;
            hash_Set.add(curRow);
            path.add(minDistIdx);
            distances.add(Double.valueOf(minDist));
        }
    }

    /**
     * Finds path to be taken based on the currently selected algorithm
     */
    private void findPath() {
        switch (super.algorithm) {
            case NEAREST_NEIGHBOR -> findPathNearestNeighbor();
        }
    }

    /**
     * @return Path to be taken starting at first node
     */
    public ArrayList<Integer> getPath() {
        if (!pathFound) {
            findPath();
        }
        return path;
    }

    /**
     * @return Distances of each path
     */
    public ArrayList<Double> getDistances() {
        if (!pathFound) {
            findPath();
        }
        return distances;
    }
}
