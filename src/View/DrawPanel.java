package View;
import DataHandler.Assets;
import DataHandler.Rescaler;
import DataHandler.Point;

import java.awt.*;
import java.util.ArrayList;
import java.util.concurrent.TimeUnit;

import javax.swing.*;

public class DrawPanel extends JPanel {

    public DrawPanel() {
        this.setBorder(BorderFactory.createLineBorder(Assets.accentColor, 2));
        this.setBounds(10,350,380,380);
        this.setBackground(Color.white);
    }

    public void paintPoints(Graphics g, ArrayList<Point> points) {
        super.paintComponent(g);
        this.setBorder(BorderFactory.createLineBorder(Assets.accentColor, 2));

        g.setColor(Color.black);
        for (int i=0; i<points.size(); i++) {
            Point pt = points.get(i);
            g.drawLine((int) pt.yCoord(), this.getHeight()-(int) pt.xCoord(), (int) pt.yCoord(), this.getHeight()-(int) pt.xCoord());
        }
    }

    public void paintLines(Graphics g, ArrayList<Point> points) {
        g.setColor(Color.red);
        for (int i=0; i<points.size()-1; i++) {
            Point pt1 = points.get(i);
            Point pt2 = points.get(i+1);
            g.drawLine((int) pt1.yCoord(), this.getHeight()-(int) pt1.xCoord(), (int) pt2.yCoord(), this.getHeight()-(int) pt2.xCoord());
        }
    }
}
