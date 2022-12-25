package DataHandler;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

public abstract class AsymmetricAlgorithm {

    /**
     * Finds path to be taken from each node to minimize cost based on Nearest Neighbor algorithm
     * @see <a href="https://en.wikipedia.org/wiki/Nearest_neighbour_algorithm">Wiki</a>
     */
    private static void nearestNeighbor(AsymmetricData data) {

        ArrayList<ArrayList<Integer>> costs = data.getCosts();
        int numNodes = data.getNumNodes();
        ArrayList<Integer> path = new ArrayList<>();
        ArrayList<Double> distances = new ArrayList<>();

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

        data.setCosts(costs);
        data.setPath(path);
        data.setDistances(distances);
    }

    public static void runAlgorithm(AsymmetricData data) {
        switch ((AsymmetricAlgorithmTypes) data.algorithm) {
            case NEAREST_NEIGHBOR -> nearestNeighbor(data);
        }
    }
}
