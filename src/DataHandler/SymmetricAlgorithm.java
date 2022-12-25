package DataHandler;

import java.util.ArrayList;
import java.util.Collections;

public abstract class SymmetricAlgorithm {

    /**
     * Sort points based on the Nearest Neighbor Algorithm
     * @see <a href="https://en.wikipedia.org/wiki/Nearest_neighbour_algorithm">Wiki</a>
     */
    private static void nearestNeighbor(SymmetricData data) {
        ArrayList<Point> points = data.getAllPoints();
        ArrayList<Double> distances = data.getDistances();
        for (int i=0; i<points.size()-2; i++) {
            double closest_dist = 1e10;
            int closest_idx = points.size()-1;
            for (int j=i+1; j<points.size(); j++) {
                double dist = MathOps.eucDist(points.get(i), points.get(j));
                if (dist < closest_dist) {
                    closest_idx = j;
                    closest_dist = dist;
                }
            }
            distances.set(i+1, closest_dist);
            Collections.swap(points, i+1, closest_idx);
        }
        data.setPoints(points);
        data.setDistances(distances);
    }

    /**
     * Runs the currently selected algorithm
     */
    public static void runAlgorithm(SymmetricData data) {
        switch ((SymmetricAlgorithmTypes) data.algorithm) {
            case NEAREST_NEIGHBOR -> nearestNeighbor(data);
        }
    }
}
