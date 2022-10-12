package View;

import javax.swing.*;
import java.awt.*;

public class StatsPanel extends JPanel {

    private JLabel visitedLabel = new JLabel("Cities visited: -");
    private JLabel distanceLabel = new JLabel("Distance: -");

    public StatsPanel() {
        this.setLayout(new GridLayout(2,1));
        visitedLabel.setHorizontalAlignment(0);
        distanceLabel.setHorizontalAlignment(0);
        this.add(visitedLabel);
        this.add(distanceLabel);
        this.setBounds(20,250,360,100);
    }

    public void updateVisited(int visited) {
        visitedLabel.setText("Cities visited: " + visited);
    }

    public void updateDistance(int distance) {
        Graphics g = this.getGraphics();
//        g.setColor(Color.black);
//        g.drawString("Distance: "+ distance, 0, 0);
//        distanceLabel = new JLabel("Distance: " + distance);
        distanceLabel.setText("Distance: " + distance);
    }

}
