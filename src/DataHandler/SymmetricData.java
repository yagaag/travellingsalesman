package DataHandler;

import java.util.ArrayList;
import java.util.Collections;

/**
 * AsymmetricData stores data of symmetric type and also calculates the solution to the problem
 * by sorting points based on nearest neighbor
 *
 * @author yagaa
 * @version 1.0.0
 * @see Data
 */
public class SymmetricData extends Data {

    private ArrayList<Point> points = new ArrayList<Point>();
    private ArrayList<Double> distances = new ArrayList<Double>();
    private boolean sorted = false;

    /**
     * Initialize SymmetricData
     */
    public SymmetricData(String name) {
        super(name);
    }

    /**
     * Adds points to the data
     *
     * @param  x  an absolute URL giving the base location of the image
     * @param  y the location of the image, relative to the url argument
     */
    public void addPoint(float x, float y) {
        Point pt = new Point(x, y);
        if (points.isEmpty()) {
            distances.add(0.0);
        }
        else {
            distances.add(MathOps.eucDist(points.get(points.size()-1), pt));
        }
        points.add(pt);
        sorted = false;
    }

    /**
     * @return All Points stored in the symmetric data object
     */
    public ArrayList<Point> getAllPoints() {
        return points;
    }

    /**
     * Sort points based on the Nearest Neighbor Algorithm
     * @see <a href="https://en.wikipedia.org/wiki/Nearest_neighbour_algorithm">Wiki</a>
     */
    private void nearestNeighborSort() {
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
        sorted = true;
    }

    /**
     * Performs sort based on currently selected algorithm
     */
    private void sort() {
        switch (super.algorithm) {
            case NEAREST_NEIGHBOR -> nearestNeighborSort();
        }
    }

    /**
     * @return Sorted points
     */
    public ArrayList<Point> getSortedPoints() {
        if (!sorted) {
            sort();
        }
        return points;
    }

    /**
     * @return Distances between sorted points
     */
    public ArrayList<Double> getSortedDistances() {
        if (!sorted) {
            sort();
        }
        return distances;
    }
}