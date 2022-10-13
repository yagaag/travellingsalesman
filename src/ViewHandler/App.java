package ViewHandler;
import DataHandler.*;
import DataHandler.Point;
import DataHandler.DatasetType;
import static DataHandler.DatasetType.*;
import static DataHandler.Algorithm.*;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class App extends JFrame implements ActionListener {
    private static String title = "Travelling Salesman Problem";
    private SelectionPanel selectionPanel = new SelectionPanel();
    private StatsPanel statsPanel = new StatsPanel();
    private JButton button = new JButton("Start Travelling");
    private DrawPanel drawPanel = new DrawPanel();
    private Algorithm algorithm = NEAREST_NEIGHBOR;
    private int visited = 0;
    private int distance = 0;

    public App() {

        button.addActionListener(this);
        JLabel algLabel = new JLabel("Algorithm: " + algorithm.name);
        algLabel.setBounds(108, 130, 184, 30);
        button.setBounds(100,180,200,50);

        this.setPreferredSize(new Dimension(400,800));
        this.setLayout(null);
        this.setTitle(title);
        this.add(selectionPanel);
        this.add(algLabel);
        this.add(button);
        this.add(statsPanel);
        this.add(drawPanel);
        this.add(new Panel());
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.pack();
        this.setVisible(true);
    }

    private void travelAnimatedRegionData(ArrayList<Point> drawPoints, ArrayList<Double> distances) {

        int start = 0;
        int div = drawPoints.size() / 10;
        CustomTimer.timeDelay(900);
        for (int i=0; i<10; i++) {
            if (i==9) {
                div = drawPoints.size()-1-start;
                visited += 1;
            }
            visited += div;
            statsPanel.updateVisited(visited);
            Double distSum = 0.0;
            for (int j=start; j<start+div; j++) {
                distSum += distances.get(j);
            }
            distance += distSum;
            statsPanel.updateDistance(distance);
            ArrayList<Point> subPoints = new ArrayList<Point>(drawPoints.subList(start, start+div+1));
            drawPanel.paintLines(drawPanel.getGraphics(), subPoints);
            start += div;
            CustomTimer.timeDelay(900);
        }
    }

    private void presentRegionData(RegionData data) {
        ArrayList<Point> drawPoints = CanvasHandler.convertToPanelSizing(data.getAllPoints(), drawPanel.getWidth(), drawPanel.getHeight());
        drawPanel.paintPoints(drawPanel.getGraphics(), drawPoints);
        ArrayList<Point> sortedDrawPoints = CanvasHandler.convertToPanelSizing(data.getSortedPoints(), drawPanel.getWidth(), drawPanel.getHeight());
        ArrayList<Double> sortedDistances = data.getSortedDistances();
        button.setText("Travelling...");
        travelAnimatedRegionData(sortedDrawPoints, sortedDistances);
    }

    private void presentMatrixData(MatrixData data) {
        ArrayList<Point> drawPoints = CanvasHandler.generatePointMatrix(data.getNumNodes(), drawPanel.getWidth(), drawPanel.getHeight());
        drawPanel.paintPoints(drawPanel.getGraphics(), drawPoints);
//        ArrayList<Point>
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        Thread thread = new Thread() {
            public void run() {
                button.setText("Calculating...");
                visited = 0;
                statsPanel.updateVisited(visited);
                distance = 0;
                statsPanel.updateDistance(distance);
                DatasetType datasetType = selectionPanel.selectedDatasetType();
                String fileName = selectionPanel.selectedFile();
                if (datasetType == COUNTRY) {
                    RegionData data = FileReader.readRegionFile(fileName);
                    data.setAlgorithm(algorithm);
                    presentRegionData(data);
                }
                else {
                    MatrixData data = FileReader.readMatrixFile(fileName);
                    data.setAlgorithm(algorithm);
                    presentMatrixData(data);
                }
                button.setText("Start Travelling");
            }
        };
        thread.start();
    }
}