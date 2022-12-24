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

/**
 * This class App solves and visualizes the Travelling Salesman problem for various datasets
 *
 * @author yagaa
 * @version 1.0.0
 */
public class App extends JFrame implements ActionListener {

    private String title = "Travelling Salesman Problem";
    private SelectionPanel selectionPanel = new SelectionPanel();
    private StatsPanel statsPanel = new StatsPanel();
    private JButton button = new JButton("Start Travelling");
    private DrawPanel drawPanel = new DrawPanel();
    private int visited = 0;
    private int distance = 0;

    /**
     * Sets up UI components and displays the JFrame
     */
    public App() {
        button.addActionListener(this);
//        JLabel algLabel = new JLabel("Algorithm: " + algorithm.name);
//        algLabel.setBounds(108, 130, 184, 30);
        button.setBounds(100,180,200,50);
        this.setPreferredSize(new Dimension(400,800));
        this.setLayout(null);
        this.setTitle(title);
        this.add(selectionPanel);
//        this.add(algLabel);
        this.add(button);
        this.add(statsPanel);
        this.add(drawPanel);
        this.add(new Panel());
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.pack();
        this.setVisible(true);
    }

    /**
     * Generates an animated drawing of lines on the drawPanel and distance information on the statsPanel
     *
     * @param  drawPoints Set of endpoints of lines to be drawn on the panel
     * @param  distances Length of each line to add to the travel distance of the salesman
     */
    private void travelAnimated(ArrayList<Point> drawPoints, ArrayList<Double> distances) {

        int start = 0;
        int div = drawPoints.size() / 10;
        CustomTimer.timeDelay(2000);
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
            drawPanel.paintLines(subPoints);
            start += div;
            CustomTimer.timeDelay(900);
        }
    }

    /**
     * Presents the solution to the travelling salesman problem for symmetric
     *
     * @param data SymmetricData object that contains point information
     */
    private void presentSymmetricData(SymmetricData data) {
        ArrayList<Point> drawPoints = CanvasHandler.convertToPanelSizing(data.getAllPoints(), drawPanel.getWidth(), drawPanel.getHeight());
        drawPanel.paintPoints(drawPoints);
        ArrayList<Point> sortedDrawPoints = CanvasHandler.convertToPanelSizing(data.getSortedPoints(), drawPanel.getWidth(), drawPanel.getHeight());
        ArrayList<Double> sortedDistances = data.getSortedDistances();
        button.setText("Travelling...");
        travelAnimated(sortedDrawPoints, sortedDistances);
    }

    /**
     * Presents the solution to the travelling salesman problem for symmetric
     *
     * @param data AsymmetricData object that contains cost and path information
     */
    private void presentAsymmetricData(AsymmetricData data) {
        int numNodes = data.getNumNodes();
        ArrayList<Point> drawPoints = CanvasHandler.generatePointMatrix(numNodes, drawPanel.getWidth(), drawPanel.getHeight());
        drawPanel.paintPoints(drawPoints);
        ArrayList<Integer> path = data.getPath();
        ArrayList<Double> distances = data.getDistances();
        ArrayList<Point> pathPoints = new ArrayList<>();
        pathPoints.add(drawPoints.get(0));
        for (int i=1; i<numNodes; i++) {
            int idx = numNodes*i + path.get(i);
            pathPoints.add(drawPoints.get(idx));
        }
        button.setText("Travelling...");
        travelAnimated(pathPoints, distances);
    }

    /**
     * Solves the Travelling Salesman problem for the selected dataset with a particular algorithm.
     *
     * @param datasetType The type of dataset to run travelling salesman algorithm on
     * @param fileName The filename of the data to run travelling salesman algorithm on
     */
    private void runApp(DatasetType datasetType, String fileName, Algorithm algorithm) {
        Thread thread = new Thread() {
            public void run() {
                button.setText("Calculating...");
                if (!FileReader.validateFileExist(fileName)) {
                    button.setText("File Missing :(");
                    return;
                }
                if (datasetType == SYMMETRIC) {
                    SymmetricData data = FileReader.readSymmetricDataFile(fileName);
                    data.setAlgorithm(algorithm);
                    presentSymmetricData(data);
                }
                else {
                    AsymmetricData data = FileReader.readAsymmetricDataFile(fileName);
                    data.setAlgorithm(algorithm);
                    presentAsymmetricData(data);
                }
                button.setText("Start Travelling");
            }
        };
        thread.start();
    }

    /**
     * Prepares and runs the algorithm upon button press
     *
     * @param e ActionEvent passed by key press
     */
    @Override
    public void actionPerformed(ActionEvent e) {
        visited = 0;
        statsPanel.updateVisited(visited);
        distance = 0;
        statsPanel.updateDistance(distance);
        DatasetType datasetType = selectionPanel.selectedDatasetType();
        String fileName = selectionPanel.selectedFile();
        Algorithm algorithm = selectionPanel.selectedAlgorithm();
        runApp(datasetType, fileName, algorithm);
    }
}