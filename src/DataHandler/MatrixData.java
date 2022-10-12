package DataHandler;
import java.util.ArrayList;

public class MatrixData extends  Data {
    private ArrayList<Point> points;
    public MatrixData(String name) {
        super(name, false);
    }
    public void addPoint(float x, float y) {
        points.add(new Point(x, y));
    }
}
