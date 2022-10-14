package DataHandler;

/**
 * This enum DatasetType stores the different datasets/variants of the Travelling Salesman problem that the App can solve
 *
 * @author yagaa
 * @version 1.0.0
 */
public enum DatasetType {

    SYMMETRIC ("Symmetric"), ASYMMETRIC("Asymmetric");

    public String name;

    /**
     * @param s The String associated with the enum
     */
    DatasetType(String s) {
        this.name = s;
    }
}