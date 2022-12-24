package DataHandler;
import java.io.File;  // Import the File class
import java.io.FileNotFoundException;  // Import this class to handle errors
import java.util.ArrayList;
import java.util.Scanner;

/**
 * The FileReader reads and populates Data objects given a filename for different file types
 *
 * @author yagaa
 * @version 1.0.0
 * @see DatasetManager
 */
public abstract class FileReader {

    private static String path = "dataset/";

    /**
     * Validates whether a file exists in local file system
     *
     * @param file Name of the file to be validated
     * @return Does the file exist
     */
    public static boolean validateFileExist(String file) {
        File myFile = new File(path+file);
        if (myFile.exists()) {
            return true;
        }
        return false;
    }

    /**
     * Reads symmetric data files and returns a SymmetricData object
     *
     * @param file Name of the file to be read
     * @return SymmetricData object containing points and details from the file
     * @see SymmetricData
     */
    public static SymmetricData readSymmetricDataFile(String file) {
        SymmetricData data = new SymmetricData(file);
        ArrayList<Float> temp = new ArrayList<>();
        try {
            File myFile = new File(path+file);
            Scanner myReader = new Scanner(myFile);
            while (myReader.hasNextLine()) {
                String d = myReader.nextLine();
                String[] arr = d.split(" ");
                if (arr[0].matches("-?\\d+")) {
                    temp.add(Float.parseFloat(arr[2]));
                    data.addPoint(Float.parseFloat(arr[1]), Float.parseFloat(arr[2]));
                }
            }
            System.out.println();
            myReader.close();
        } catch (FileNotFoundException e) {
            System.out.println("Unfortunately, the file was not found");
            e.printStackTrace();
        }
        return data;
    }

    /**
     * Reads asymmetric data files and returns a AsymmetricData object
     *
     * @param file Name of the file to be read
     * @return AsymmetricData object containing costs and details from the file
     * @see AsymmetricData
     */
    public static AsymmetricData readAsymmetricDataFile(String file) {
        AsymmetricData data = new AsymmetricData(file);
        ArrayList<Integer> tempStor = new ArrayList<Integer>();
        try {
            File myFile = new File(path+file);
            Scanner myReader = new Scanner(myFile);
            boolean dataStarted = false;
            while (myReader.hasNextLine()) {
                String d = myReader.nextLine();
                String[] arr = d.split(" ");
                if (arr[0].contains("DIMENSION")) {
                    int i=0;
                    while (!arr[i].matches("-?\\d+")) {
                        i+=1;
                    }
                    data.setNumNodes(Integer.parseInt(arr[i]));
                }
                if (dataStarted) {
                    for (int i=0; i<arr.length; i++) {
                        if (arr[i].matches("-?\\d+")) {
                            tempStor.add(Integer.parseInt(arr[i]));
                        }
                    }
                }
                if (arr[0].contains("EDGE_WEIGHT_SECTION")) {
                    dataStarted = true;
                }
            }
            myReader.close();
        } catch (FileNotFoundException e) {
            System.out.println("Unfortunately, the file was not found");
            e.printStackTrace();
        }
        if (Math.sqrt(tempStor.size()) < data.getNumNodes()) {
            data.setNumNodes((int) Math.sqrt(tempStor.size()));
        }
        data.addData(tempStor);
        return data;
    }
}
