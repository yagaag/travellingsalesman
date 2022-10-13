package DataHandler;
import java.io.File;  // Import the File class
import java.io.FileNotFoundException;  // Import this class to handle errors
import java.util.ArrayList;
import java.util.Scanner;
import org.apache.commons.lang3.math.NumberUtils;

public class FileReader {

    private static String path = "dataset/";

    public static boolean validateFileExist(String fileName) {
        File myFile = new File(path+fileName);
        if (myFile.exists()) {
            return true;
        }
        return false;
    }

    public static RegionData readRegionFile(String fileName) {
        RegionData data = new RegionData(fileName);
        try {
            File myFile = new File(path+fileName);
            Scanner myReader = new Scanner(myFile);
            while (myReader.hasNextLine()) {
                String d = myReader.nextLine();
                String[] arr = d.split(" ");
                if (NumberUtils.isDigits(arr[0])) {
                    data.addPoint(Float.parseFloat(arr[1]), Float.parseFloat(arr[2]));
                }
            }
            myReader.close();
        } catch (FileNotFoundException e) {
            System.out.println("Unfortunately, the file was not found");
            e.printStackTrace();
        }
        return data;
    }

    public static MatrixData readMatrixFile(String fileName) {
        MatrixData data = new MatrixData(fileName);
        ArrayList<Integer> tempStor = new ArrayList<Integer>();
        try {
            File myFile = new File(path+fileName);
            Scanner myReader = new Scanner(myFile);
            boolean dataStarted = false;
            while (myReader.hasNextLine()) {
                String d = myReader.nextLine();
                String[] arr = d.split(" ");
                if (arr[0].contains("DIMENSION")) {
                    int i=0;
                    while (!NumberUtils.isDigits(arr[i])) {
                        i+=1;
                    }
                    data.setNumNodes(Integer.parseInt(arr[i]));
                }
                if (dataStarted) {
                    for (int i=0; i<arr.length; i++) {
                        if (NumberUtils.isDigits(arr[i])) {
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
