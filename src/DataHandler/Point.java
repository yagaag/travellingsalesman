package DataHandler;

/**
 * Point holds 2-D coordinates
 *
 * @author yagaa
 * @version 1.0.0
 * @see SymmetricData
 */
public class Point {

    private double x;
    private double y;

    public Point(double xCoordinate, double yCoordinate) {
        x = xCoordinate;
        y = yCoordinate;
    }

    /**
     * @return x-coordinate of the point
     */
    public double xCoord() {
        return x;
    }

    /**
     * @return y-coordinate of the point
     */
    public double yCoord() {
        return y;
    }
}
