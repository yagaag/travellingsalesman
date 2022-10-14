package ViewHandler;
import DataHandler.DatasetManager;
import DataHandler.DatasetType;
import static DataHandler.DatasetType.*;

import javax.swing.*;

/**
 * The SelectionPanel shows different configurations to run the Travelling Salesman problem on
 *
 * @author yagaa
 * @version 1.0.0
 * @see DatasetType
 * @see DatasetManager
 */
public class SelectionPanel extends JPanel {

    private JRadioButton radioButton1 = new JRadioButton(SYMMETRIC.name);
    private JRadioButton radioButton2 = new JRadioButton(ASYMMETRIC.name);
    private ButtonGroup radioButtons = new ButtonGroup();
    JLabel fileLabel = new JLabel("Select country/region: ");
    private JComboBox<String> comboBox = new JComboBox<>();
    private DatasetType datasetType = SYMMETRIC;
    private String[] fileList;

    /**
     * Initializes the DrawPanel and specifies visual aspects such as border and position;
     * Also fetches available datasets and populates combo boxes.
     */
    public SelectionPanel() {
        this.setBounds(0,0,400,130);
        JLabel typeLabel = new JLabel("Set dataset type: ");
        typeLabel.setBounds(20, 20, 160, 30);
        radioButtons.add(radioButton1);
        radioButtons.add(radioButton2);
        radioButton1.setSelected(true);
        radioButton1.addActionListener(e -> changeDataset(SYMMETRIC));
        radioButton2.addActionListener(e -> changeDataset(ASYMMETRIC));
        radioButton1.setBounds(170, 20, 98, 30);
        radioButton2.setBounds(268, 20, 120, 30);
        fileList = DatasetManager.fetchAllRegions();
        populateComboBox();
        fileLabel.setBounds(20, 70, 160, 50);
        comboBox.setBounds(170, 70, 180, 50);
        this.setLayout(null);
        this.add(typeLabel);
        this.add(radioButton1);
        this.add(radioButton2);
        this.add(fileLabel);
        this.add(comboBox);
    }

    /**
     * Change the dataset type
     * @param type Updated DatasetType
     */
    private void changeDataset(DatasetType type) {
        if (datasetType != type) {
            datasetType = type;
            if (type == SYMMETRIC) {
                fileList = DatasetManager.fetchAllRegions();
                fileLabel.setText("Select country/region: ");
            }
            else {
                fileList = DatasetManager.fetchAllAsymmetricFiles();
                fileLabel.setText("Select file: ");
            }
            populateComboBox();
        }
    }

    /**
     * Populate the combo box with list of files
     */
    private void populateComboBox() {
        comboBox.removeAllItems();
        for (String s: fileList) {
            comboBox.addItem(s);
        }
    }

    /**
     * @return Type of dataset currently selected
     */
    public DatasetType selectedDatasetType() {
        return datasetType;
    }

    /**
     * @return File currently selected
     */
    public String selectedFile() {
        String fileName = String.valueOf(comboBox.getSelectedItem());
        if (datasetType == SYMMETRIC) {
            fileName = DatasetManager.fetchSymmetricFile(fileName);
        }
        return fileName;
    }
}
