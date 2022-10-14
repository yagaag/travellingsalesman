package DataHandler;
import static DataHandler.Algorithm.*;

/**
 * Base class for storing dataset values
 *
 * @author yagaa
 * @version 1.0.0
 * @see SymmetricData
 * @see AsymmetricData
 */
public abstract class Data {
    protected String filename;
    protected Algorithm algorithm = NEAREST_NEIGHBOR;

    /**
     * Initializes Data
     * @param name File name of the data loaded into the object
     */
    public Data(String name) {
        filename = name;
    }

    /**
     * Sets the algorithm to be used to solve the problem
     * @param algorithm The selected algorithm
     */
    public void setAlgorithm(Algorithm algorithm) {
        this.algorithm = algorithm;
    }
}
