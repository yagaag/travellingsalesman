package DataHandler;

import java.util.ArrayList;

public class CanvasHandler {

    public static ArrayList<Point> convertToPanelSizing(ArrayList<Point> points, int width, int height) {
        ArrayList<Point> rescaledPoints = new ArrayList<Point>();
        double xMin = 1e10;
        double yMin = 1e10;
        double xMax = 0;
        double yMax = 0;
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
        double xDiff = xMax - xMin;
        double yDiff = yMax - yMin;
        double normFactor = (double) height;

        double xAspect = normFactor * 0.8;
        double yAspect = normFactor * 0.8;

        if (xDiff > yDiff) {
            yAspect = (yDiff / xDiff) * normFactor * 0.8;
        }
        else {
            xAspect = (xDiff / yDiff) * normFactor * 0.8;
        }
        for (int i=0; i<points.size(); i++) {
            double xCoord = (((points.get(i).xCoord()-xMin) * xAspect) / xDiff) + (width * 0.1);
            double yCoord = (((points.get(i).yCoord()-yMin) * yAspect) / yDiff) + (height * 0.1);
            rescaledPoints.add(new Point(xCoord, yCoord));
        }
        return rescaledPoints;
    }

    public static ArrayList<Point> generatePointMatrix(int size, int width, int height) {
        System.out.println(size);
        ArrayList<Point> points = new ArrayList<>();
        int rowGap = width / (size+2);
        int colGap = height / (size+2);
        int curRow = rowGap;
        int curCol = colGap;
        for (int i=0; i<size; i++) {
            for (int j=0; j<size; j++) {
                System.out.println(curRow);
                System.out.println(curCol);
                points.add(new Point(curRow, curCol));
                curRow+=rowGap;
                curCol+=colGap;
            }
        }
        return points;
    }

}
