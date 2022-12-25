package ViewHandler;
import DataHandler.*;

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
    private JComboBox<String> datasetComboBox = new JComboBox<>();
    JLabel algLabel = new JLabel("Select algorithm: ");
    private JComboBox<String> algorithmComboBox = new JComboBox<>();
    private DatasetType datasetType = SYMMETRIC;

    private AlgorithmTypes algorithm = SymmetricAlgorithmTypes.NEAREST_NEIGHBOR;

    /**
     * Initializes the DrawPanel and specifies visual aspects such as border and position;
     * Also fetches available datasets and populates combo boxes.
     */
    public SelectionPanel() {
        this.setBounds(0,0,400,160);
        JLabel typeLabel = new JLabel("Set dataset type: ");
        typeLabel.setBounds(20, 20, 160, 30);
        radioButtons.add(radioButton1);
        radioButtons.add(radioButton2);
        radioButton1.setSelected(true);
        radioButton1.addActionListener(e -> changeDataset(SYMMETRIC));
        radioButton2.addActionListener(e -> changeDataset(ASYMMETRIC));
        radioButton1.setBounds(170, 20, 98, 30);
        radioButton2.setBounds(268, 20, 120, 30);
        populateDatasetComboBox();
        populateAlgorithmComboBox();
        fileLabel.setBounds(20, 67, 160, 40);
        datasetComboBox.setBounds(170, 67, 180, 40);
        algLabel.setBounds(20, 120, 160, 40);
        algorithmComboBox.setBounds(170, 120, 180, 40);
        this.setLayout(null);
        this.add(typeLabel);
        this.add(radioButton1);
        this.add(radioButton2);
        this.add(fileLabel);
        this.add(datasetComboBox);
        this.add(algLabel);
        this.add(algorithmComboBox);
    }

    /**
     * Change the dataset type
     * @param type Updated DatasetType
     */
    private void changeDataset(DatasetType type) {
        if (datasetType != type) {
            datasetType = type;
            populateDatasetComboBox();
            populateAlgorithmComboBox();
        }
    }

    /**
     * Populate the combo box with list of files
     */
    private void populateDatasetComboBox() {
        datasetComboBox.removeAllItems();
        if (datasetType == SYMMETRIC) {
            for (String file: DatasetManager.fetchAllRegions()) {
                datasetComboBox.addItem(file);
            }
            fileLabel.setText("Select country/region: ");
        } else {
            for (String file: DatasetManager.fetchAllAsymmetricFiles()) {
                datasetComboBox.addItem(file);
            }
            fileLabel.setText("Select file: ");
        }
    }

    private void populateAlgorithmComboBox() {
        algorithmComboBox.removeAllItems();
        if (datasetType == SYMMETRIC) {
            for (SymmetricAlgorithmTypes alg: SymmetricAlgorithmTypes.values()) {
                algorithmComboBox.addItem(alg.name);
            }
        } else {
            for (AsymmetricAlgorithmTypes alg: AsymmetricAlgorithmTypes.values()) {
                algorithmComboBox.addItem(alg.name);
            }
        }
    }

    /**
     * @return Type of dataset currently selected
     */
    public DatasetType selectedDatasetType() {
        return datasetType;
    }

    public AlgorithmTypes selectedAlgorithm() {
        String algName = String.valueOf(algorithmComboBox.getSelectedItem());
        if (datasetType == SYMMETRIC) {
            return SymmetricAlgorithmTypes.fromString(algName);
        } else {
            return AsymmetricAlgorithmTypes.fromString(algName);
        }
    }

    /**
     * @return File currently selected
     */
    public String selectedFile() {
        String fileName = String.valueOf(datasetComboBox.getSelectedItem());
        if (datasetType == SYMMETRIC) {
            fileName = DatasetManager.fetchSymmetricFile(fileName);
        }
        return fileName;
    }
}
