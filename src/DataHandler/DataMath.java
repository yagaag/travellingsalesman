package DataHandler;

import java.util.ArrayList;

public class DataMath {

    public static double eucDist(Point pt1, Point pt2) {
        return Math.sqrt(Math.pow(pt2.xCoord()-pt1.xCoord(), 2) + Math.pow(pt2.yCoord()- pt1.yCoord(), 2));
    }

}
