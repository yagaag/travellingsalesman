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

    public void setCosts(ArrayList<ArrayList<Integer>> costs) {
        this.costs = costs;
    }

    public void setPath(ArrayList<Integer> path) {
        this.path = path;
        distances.add(0.0);
        for (int i=0; i<path.size()-2; i++) {
            Double dist = Double.valueOf(costs.get(path.get(i)).get(path.get(i+1)));
            distances.add(dist);
        }
    }

    public void setDistances(ArrayList<Double> distances) {
        this.distances = distances;
    }

    /**
     * @return Costs of travelling
     */
    public ArrayList<ArrayList<Integer>> getCosts() {
        return costs;
    }

    /**
     * @return Path to be taken starting at first node
     */
    public ArrayList<Integer> getPath() {
        return path;
    }

    /**
     * @return Distances of each path
     */
    public ArrayList<Double> getDistances() {
        return distances;
    }
}
