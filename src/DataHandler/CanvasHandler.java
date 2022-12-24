package DataHandler;

import java.util.ArrayList;

/**
 * This class CanvasHandler contains functions to manipulate/create Point data to properly display on JPanels
 *
 * @author yagaa
 * @version 1.0.0
 * @see Point
 * @see ViewHandler.DrawPanel
 */
public abstract class CanvasHandler {

    /**
     * Convert the given set of points to fit within a height and width retaining aspect ratio
     *
     * @param points Set of Points
     * @param width Horizontal boundary
     * @param height Vertical boundary
     * @return Set of points fit within the stipulated boundary
     */
    public static ArrayList<Point> convertToPanelSizing(ArrayList<Point> points, int width, int height) {
        ArrayList<Point> rescaledPoints = new ArrayList<Point>();
        double xMin = 1e10;
        double yMin = 1e10;
        double xMax = -1e10;
        double yMax = -1e10;
        for (int i=0; i<points.size(); i++) {
            if (points.get(i).xCoord() < xMin) {
                xMin = points.get(i).xCoord();
            }
            if (points.get(i).yCoord() < yMin) {
                yMin = points.get(i).yCoord();
            }
            if (points.get(i).xCoord() > xMax) {
                xMax = points.get(i).xCoord();
            }
            if (points.get(i).yCoord() > yMax) {
                yMax = points.get(i).yCoord();
            }
        }
        System.out.println(xMin);
        System.out.println(xMax);
        System.out.println(yMin);
        System.out.println(yMax);
        double xDiff = xMax - xMin;
        double yDiff = yMax - yMin;
        double normFactor = height;

        double xAspect = normFactor * 0.8;
        double yAspect = normFactor * 0.8;

        if (xDiff > yDiff) {
            yAspect = (yDiff / xDiff) * normFactor * 0.8;
        }
        else {
            xAspect = (xDiff / yDiff) * normFactor * 0.8;
        }

        double xCorrection = (width-xAspect) / 2;
        double yCorrection = (height-yAspect) / 2;
        for (int i=0; i<points.size(); i++) {
            double xCoord = (((points.get(i).xCoord()-xMin) * xAspect) / xDiff) + xCorrection;
            double yCoord = (((points.get(i).yCoord()-yMin) * yAspect) / yDiff) + yCorrection;
            rescaledPoints.add(new Point(xCoord, yCoord));
        }

        return rescaledPoints;
    }

    /**
     * Creates a matrix of points of needed size within a boundary
     *
     * @param size Size of the matrix to be created
     * @param width Horizontal boundary
     * @param height Vertical boundary
     * @return Set of points forming a matrix of dimension size x size
     */
    public static ArrayList<Point> generatePointMatrix(int size, int width, int height) {
        ArrayList<Point> points = new ArrayList<>();

        int rowGap = width / (size+1);
        int colGap = width / (size+1);
        int curRow = (int) (rowGap + (width*0.05));
        int curCol = (int) (colGap + (height*0.05));
        for (int i=0; i<size; i++) {
            for (int j=0; j<size; j++) {
                points.add(new Point(curRow, curCol));
                curCol += colGap;
            }
            curRow += rowGap;
            curCol = (int) (colGap + (height*0.05));
        }
        return points;
    }
}
