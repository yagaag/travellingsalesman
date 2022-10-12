package View;
import DataHandler.*;
import DataHandler.Point;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class App extends JFrame implements ActionListener {
    private static String title = "Travelling Salesman Problem";
    private String datasetType = "Country";
    private JRadioButton radioButton1 = new JRadioButton("Country");
    private JRadioButton radioButton2 = new JRadioButton("Matrix");
    private ButtonGroup radioButtons = new ButtonGroup();
    JLabel fileLabel = new JLabel("Select country/region: ");
    private String[] fileList;
    private JComboBox<String> comboBox = new JComboBox<>();
    private StatsPanel statsPanel = new StatsPanel();
    private int visited = 0;
    private int distance = 0;
    private JButton button = new JButton("Start Travelling");
    private DrawPanel drawPanel = new DrawPanel();
//    private JFrame frame = new JFrame();
    public App() {

        JLabel typeLabel = new JLabel("Set dataset type: ");
        typeLabel.setBounds(20, 20, 160, 30);
        radioButtons.add(radioButton1);
        radioButtons.add(radioButton2);
        radioButton1.setSelected(true);
        radioButton1.addActionListener(e -> changeDataset("Country"));
        radioButton2.addActionListener(e -> changeDataset("Matrix"));
        radioButton1.setBounds(200, 20, 100, 30);
        radioButton2.setBounds(300, 20, 100, 30);

        fileList = DatasetManager.fetchAllRegions();
        populateComboBox();
        button.addActionListener(this);
        fileLabel.setBounds(20, 70, 160, 50);
        comboBox.setBounds(200, 70, 180, 50);
        JLabel algLabel = new JLabel("Algorithm: Nearest Neighbor");
        algLabel.setBounds(108, 130, 184, 30);
        button.setBounds(100,180,200,50);

        this.setPreferredSize(new Dimension(400,800));
        this.setLayout(null);
        this.setTitle(title);
        this.add(typeLabel);
        this.add(radioButton1);
        this.add(radioButton2);
        this.add(fileLabel);
        this.add(comboBox);
        this.add(algLabel);
        this.add(button);
        this.add(statsPanel);
        this.add(drawPanel);
        this.add(new Panel());
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.pack();
        this.setVisible(true);
    }

    private void populateComboBox() {
        comboBox.removeAllItems();
        for (String s: fileList) {
            comboBox.addItem(s);
        }
    }

    private void changeDataset(String type) {
        if (datasetType != type) {
            datasetType = type;
            if (type == "Country") {
                fileList = DatasetManager.fetchAllRegions();
                fileLabel.setText("Select country/region: ");
            }
            else {
                fileList = DatasetManager.fetchAllMatrixFiles();
                fileLabel.setText("Select matrix file: ");
            }
            populateComboBox();
        }
    }

    public void timeDelay(long t) {
        try {
            Thread.sleep(t);
        } catch (InterruptedException e) {}
    }

    private void travelAnimated(ArrayList<Point> drawPoints, ArrayList<Double> distances) {
        int start = 0;
        int div = drawPoints.size() / 10;
        timeDelay(900);
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
            timeDelay(900);
        }
    }

    private void present(RegionData data) {
        ArrayList<Point> drawPoints = Rescaler.convertToPanelSizing(data.getAllPoints(), drawPanel.getWidth(), drawPanel.getHeight());
        drawPanel.paintPoints(drawPanel.getGraphics(), drawPoints);
        ArrayList<Point> sortedDrawPoints = Rescaler.convertToPanelSizing(data.getSortedPoints(), drawPanel.getWidth(), drawPanel.getHeight());
        ArrayList<Double> sortedDistances = data.getSortedDistances();
        travelAnimated(sortedDrawPoints, sortedDistances);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        Thread thread = new Thread() {
            public void run() {
                button.setText("Travelling...");
                visited = 0;
                statsPanel.updateVisited(visited);
                distance = 0;
                statsPanel.updateDistance(distance);
                String fileName = String.valueOf(comboBox.getSelectedItem());
                if (datasetType == "Country") {
                    RegionData data = FileReader.readRegionFile(fileName);
                    present(data);
                }
                else {
                    MatrixData data = FileReader.readMatrixFile(fileName);
                }
                button.setText("Start Travelling");
            }
        };
        thread.start();
    }
}