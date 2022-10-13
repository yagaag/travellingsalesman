package DataHandler;
import java.util.ArrayList;

import static DataHandler.Algorithm.*;

public class MatrixData extends  Data {
    private ArrayList<ArrayList<Integer>> costs = new ArrayList<>();
    private ArrayList<Integer> path = new ArrayList<Integer>();
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

    private void findPathNearestNeighbor() {
        path.add(0);
        for (int i=1; i<numNodes; i++) {
            path.add(i);
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
}
