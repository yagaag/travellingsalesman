package DataHandler;
import java.io.File;  // Import the File class
import java.io.FileNotFoundException;  // Import this class to handle errors
import java.util.ArrayList;
import java.util.Scanner;
import org.apache.commons.lang3.math.NumberUtils;

public class FileReader {

    private static String path = "dataset/";

    public static boolean validateFileExist(String filename) {
        File myFile = new File(path+filename);
        if (myFile.exists()) {
            return true;
        }
        return false;
    }

    public static RegionData readRegionFile(String regionName) {
        String fileName = DatasetManager.fetchRegionFile(regionName);
        RegionData data = new RegionData(fileName, regionName);
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
}
