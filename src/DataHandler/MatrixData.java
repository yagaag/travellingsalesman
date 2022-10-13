package DataHandler;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

import static DataHandler.Algorithm.*;

public class MatrixData extends Data {
    private ArrayList<ArrayList<Integer>> costs = new ArrayList<>();
    private ArrayList<Integer> path = new ArrayList<Integer>();
    private ArrayList<Double> distances = new ArrayList<Double>();
    private int numNodes = 0;
    private boolean pathFound = false;

    public MatrixData(String name) {
        super(name, false);
    }

    public void setNumNodes(int nodes) {
        numNodes = nodes;
    }

    public int getNumNodes() {
        return numNodes;
    }

    public void addRow(ArrayList<Integer> row) {
        costs.add(row);
    }

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

    private void findPathNearestNeighbor() {
        System.out.println(costs.size());
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

    private void findPath() {
        switch (super.algorithm) {
            case NEAREST_NEIGHBOR -> findPathNearestNeighbor();
        }
    }

    public ArrayList<Integer> getPath() {
        if (!pathFound) {
            findPath();
        }
        return path;
    }

    public ArrayList<Double> getDistances() {
        if (!pathFound) {
            findPath();
        }
        return distances;
    }
}
