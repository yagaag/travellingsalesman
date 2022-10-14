package DataHandler;

/**
 * This enum Algorithm stores the different types of algorithms to solve the Travelling Salesman problem
 *
 * @author yagaa
 * @version 1.0.0
 */
public enum Algorithm {

    NEAREST_NEIGHBOR("Nearest Neighbor");

    public String name;

    /**
     * @param s The String associated with the enum
     */
    Algorithm(String s) {
        this.name = s;
    }
}
