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

    /**
     * Initialize SymmetricData
     */
    public SymmetricData(String name) {
        super(name);
    }

    /**
     * Adds points to the data
     *
     * @param  x  the x-coordinate
     * @param  y the y-coordinate
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
    }

    public void setPoints(ArrayList<Point> points) {
        this.points = points;
        for (int i=0; i<points.size()-2; i++) {
            double dist = MathOps.eucDist(points.get(i), points.get(i+1));
            distances.set(i+1, dist);
        }
    }

    public void setDistances(ArrayList<Double> distances) {
        this.distances = distances;
    }

    /**
     * @return All Points stored in the symmetric data object
     */
    public ArrayList<Point> getAllPoints() {
        return points;
    }

    public ArrayList<Double> getDistances() {
        return distances;
    }
}