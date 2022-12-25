package DataHandler;

/**
 * This enum Algorithm stores the different types of algorithms to solve the Travelling Salesman problem
 *
 * @author yagaa
 * @version 1.0.0
 */
public enum AsymmetricAlgorithmTypes implements AlgorithmTypes {

    NEAREST_NEIGHBOR("Nearest Neighbor"), FARTHEST_NEIGHBOR("Farthest Neighbor");

    public String name;

    /**
     * @param s The String associated with the enum
     */
    AsymmetricAlgorithmTypes(String s) {
        this.name = s;
    }

    public static AsymmetricAlgorithmTypes fromString(String text) {
        for (AsymmetricAlgorithmTypes alg : AsymmetricAlgorithmTypes.values()) {
            if (alg.name.equalsIgnoreCase(text)) {
                return alg;
            }
        }
        return null;
    }
}
