package ViewHandler;

import DataHandler.DatasetManager;
import DataHandler.DatasetType;
import static DataHandler.DatasetType.*;

import javax.swing.*;
import java.beans.PropertyChangeListener;
import java.util.Observable;

public class SelectionPanel extends JPanel {
    private JRadioButton radioButton1 = new JRadioButton("Country");
    private JRadioButton radioButton2 = new JRadioButton("Matrix");
    private ButtonGroup radioButtons = new ButtonGroup();
    JLabel fileLabel = new JLabel("Select country/region: ");
    private JComboBox<String> comboBox = new JComboBox<>();
    private DatasetType datasetType = COUNTRY;
    private String[] fileList;
    public SelectionPanel() {
        this.setBounds(0,0,400,130);
        JLabel typeLabel = new JLabel("Set dataset type: ");
        typeLabel.setBounds(20, 20, 160, 30);
        radioButtons.add(radioButton1);
        radioButtons.add(radioButton2);
        radioButton1.setSelected(true);
        radioButton1.addActionListener(e -> changeDataset(COUNTRY));
        radioButton2.addActionListener(e -> changeDataset(MATRIX));
        radioButton1.setBounds(200, 20, 100, 30);
        radioButton2.setBounds(300, 20, 100, 30);
        fileList = DatasetManager.fetchAllRegions();
        populateComboBox();
        fileLabel.setBounds(20, 70, 160, 50);
        comboBox.setBounds(200, 70, 180, 50);
        this.setLayout(null);
        this.add(typeLabel);
        this.add(radioButton1);
        this.add(radioButton2);
        this.add(fileLabel);
        this.add(comboBox);
    }

    private void changeDataset(DatasetType type) {
        if (datasetType != type) {
            datasetType = type;
            if (type == COUNTRY) {
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

    private void populateComboBox() {
        comboBox.removeAllItems();
        for (String s: fileList) {
            comboBox.addItem(s);
        }
    }

    public DatasetType selectedDatasetType() {
        return datasetType;
    }

    public String selectedFile() {
        String fileName = String.valueOf(comboBox.getSelectedItem());
        if (datasetType == COUNTRY) {
            fileName = DatasetManager.fetchRegionFile(fileName);
        }
        return fileName;
    }
}
