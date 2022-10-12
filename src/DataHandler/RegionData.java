package DataHandler;
import java.util.ArrayList;
import java.util.Collections;

public class RegionData extends Data {
    private String region;
    private ArrayList<Point> points = new ArrayList<>();
    private ArrayList<Double> distances = new ArrayList<>();
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
            distances.add(DataMath.eucDist(points.get(points.size()-1), pt));
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

    private void nearbySort() {
        for (int i=0; i<points.size()-2; i++) {
            double closest_dist = 1e10;
            int closest_idx = points.size()-1;
            for (int j=i+1; j<points.size(); j++) {
                double dist = DataMath.eucDist(points.get(i), points.get(j));
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

    public ArrayList<Point> getSortedPoints() {
        if (!sorted) {
            nearbySort();
        }
        return points;
    }

    public ArrayList<Double> getSortedDistances() {
        if (!sorted) {
            nearbySort();
        }
        return distances;
    }

}