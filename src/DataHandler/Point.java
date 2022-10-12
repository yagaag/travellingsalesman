package DataHandler;

public class Point {
    private double x;
    private double y;
    public Point(double xCoordinate, double yCoordinate) {
        x = xCoordinate;
        y = yCoordinate;
    }
    public double[] returnPoint() {
        double[] c = {x, y};
        return c;
    }
    public double xCoord() {
        return x;
    }
    public double yCoord() {
        return y;
    }
}
