package DataHandler;

/**
 * This enum Algorithm stores the different types of algorithms to solve the Travelling Salesman problem
 *
 * @author yagaa
 * @version 1.0.0
 */
public enum SymmetricAlgorithmTypes implements AlgorithmTypes {

    NEAREST_NEIGHBOR("Nearest Neighbor");

    public String name;

    /**
     * @param s The String associated with the enum
     */
    SymmetricAlgorithmTypes(String s) {
        this.name = s;
    }

    public static SymmetricAlgorithmTypes fromString(String text) {
        for (SymmetricAlgorithmTypes alg : SymmetricAlgorithmTypes.values()) {
            if (alg.name.equalsIgnoreCase(text)) {
                return alg;
            }
        }
        return null;
    }
}
