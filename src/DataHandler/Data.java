package DataHandler;

import static DataHandler.Algorithm.NEAREST_NEIGHBOR;

public class Data {
    protected String filename;
    private boolean regionAssociation;

    protected Algorithm algorithm = NEAREST_NEIGHBOR;

    public Data(String name, boolean region) {
        filename = name;
        regionAssociation = region;
    }

    public void setAlgorithm(Algorithm algorithm) {
        this.algorithm = algorithm;
    }
}
