package DataHandler;
import java.util.ArrayList;
import java.util.Collections;

import static DataHandler.Algorithm.*;

public class RegionData extends Data {
    private String region;
    private ArrayList<Point> points = new ArrayList<Point>();
    private ArrayList<Double> distances = new ArrayList<Double>();
    private boolean sorted = false;

    public RegionData(String name, String regionName) {
        super(name, true);
        region = regionName;
    }

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

    public Point getFirstPoint() {
        if (points.isEmpty()) {
            return new Point(0, 0);
        }
        else {
            return points.get(0);
        }
    }

    public ArrayList<Point> getAllPoints() {
        return points;
    }

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

    private void sort() {
        switch (super.algorithm) {
            case NEAREST_NEIGHBOR -> nearestNeighborSort();
        }
    }

    public ArrayList<Point> getSortedPoints() {
        if (!sorted) {
            sort();
        }
        return points;
    }

    public ArrayList<Double> getSortedDistances() {
        if (!sorted) {
            sort();
        }
        return distances;
    }

}