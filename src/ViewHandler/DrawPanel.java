package ViewHandler;
import DataHandler.DatasetManager;
import DataHandler.DatasetType;
import DataHandler.Point;

import java.awt.*;
import java.util.ArrayList;
import javax.swing.*;

/**
 * The DrawPanel holds the space and tools to visualize the dataset and the travel path
 *
 * @author yagaa
 * @version 1.0.0
 */
public class DrawPanel extends JPanel {

    /**
     * Initializes the DrawPanel and specifies visual aspects such as border and position
     */
    public DrawPanel() {
        this.setBorder(BorderFactory.createLineBorder(Assets.accentColor, 2));
        this.setBounds(10,350,380,380);
        this.setBackground(Color.white);
    }

    /**
     * Paints a set of points on the panel
     *
     * @param points The Points that need to be plotted on the DrawPanel
     */
    public void paintPoints(ArrayList<Point> points) {
        Graphics g = this.getGraphics();
        super.paintComponent(g);
        this.setBorder(BorderFactory.createLineBorder(Assets.accentColor, 2));
        CustomTimer.timeDelay(100);
        g.setColor(Color.black);
        for (int i=0; i<points.size(); i++) {
            Point pt = points.get(i);
            g.drawLine((int) pt.yCoord(), this.getHeight()-(int) pt.xCoord(), (int) pt.yCoord(), this.getHeight()-(int) pt.xCoord());
        }
    }

    /**
     * Paints a set of lines on the panel
     *
     * @param points The Points that need to be connected and drawn as lines on the DrawPanel
     */
    public void paintLines(ArrayList<Point> points) {
        Graphics g = this.getGraphics();
        g.setColor(Color.red);
        for (int i=0; i<points.size()-1; i++) {
            Point pt1 = points.get(i);
            Point pt2 = points.get(i+1);
            g.drawLine((int) pt1.yCoord(), this.getHeight()-(int) pt1.xCoord(), (int) pt2.yCoord(), this.getHeight()-(int) pt2.xCoord());
        }
    }
}
