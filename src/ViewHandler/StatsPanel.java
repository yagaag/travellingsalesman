package ViewHandler;

import javax.swing.*;
import java.awt.*;

/**
 * The StatsPanel displays real-time stats of the number of cities visited and the distance travelled by the Travelling Salesman
 *
 * @author yagaa
 * @version 1.0.0
 */
public class StatsPanel extends JPanel {

    private JLabel visitedLabel = new JLabel("Cities visited: -");
    private JLabel distanceLabel = new JLabel("Distance: -");

    /**
     * Initializes the StatsPanel and specifies visual aspects such as border and position.
     */
    public StatsPanel() {
        this.setLayout(new GridLayout(2,1));
        visitedLabel.setHorizontalAlignment(0);
        distanceLabel.setHorizontalAlignment(0);
        this.add(visitedLabel);
        this.add(distanceLabel);
        this.setBounds(20,250,360,90);
    }

    /**
     * Updates the number of cities visited stat
     * @param visited Updated number of cities visited by the travelling salesman
     */
    public void updateVisited(int visited) {
        visitedLabel.setText("Cities visited: " + visited);
    }

    /**
     * Updates the total distance travelled stat
     * @param distance Updated distance travelled by the travelling salesman
     */
    public void updateDistance(int distance) {
        Graphics g = this.getGraphics();
        distanceLabel.setText("Distance: " + distance);
    }
}
