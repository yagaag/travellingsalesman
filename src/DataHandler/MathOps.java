package DataHandler;

/**
 * MathOps class has implementations of any needed mathematical formulae/operations for use in the app
 *
 * @author yagaa
 * @version 1.0.0
 */
public abstract class MathOps {

    /**
     * Calculates the euclidean distance between two Point objects
     * The points are interchangeable since the operation is commutative
     *
     * @param pt1  The first point
     * @param pt2  The second point
     * @return Euclidean distance between the two points
     * @see Point
     */
    public static double eucDist(Point pt1, Point pt2) {
        return Math.sqrt(Math.pow(pt2.xCoord()-pt1.xCoord(), 2) + Math.pow(pt2.yCoord()- pt1.yCoord(), 2));
    }
}
