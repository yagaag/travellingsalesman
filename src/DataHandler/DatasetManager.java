package DataHandler;

/**
 * This class DatasetManager stores the titles of the datasets available to demonstrate the Travelling Salesman problem
 *
 * @author yagaa
 * @version 1.0.0
 * @see <a href="https://www.math.uwaterloo.ca/tsp/world/countries.html">Data Source</a>
 * @see <a href="http://elib.zib.de/pub/mp-testdata/tsp/tsplib/atsp/index.html">Data Source</a>
 */
public abstract class DatasetManager {

    private static String[] regions = {"Argentina", "Burma", "Canada", "China", "Djibouti", "Egypt", "Finland",
                                "Greece", "Honduras", "Ireland", "Italy", "Japan", "Kazakhstan", "Luxembourg",
                                "Morocco", "Nicaragua", "Oman", "Panama", "Qatar", "Rwanda", "Sweden", "Tanzania",
                                "Uruguay", "Vietnam", "Western Sahara", "Yemen", "Zimbabwe"};
    private static String[] regionFiles = {"ar_inv9152.tsp", "bm33708.tsp", "ca4663.tsp", "ch71009.tsp", "dj38.tsp", "eg7146.tsp",
                                "fi10639.tsp", "gr9882.tsp", "ho14473.tsp", "ei8246.tsp", "it16862.tsp", "ja9847.tsp",
                                "kz9976.tsp", "lu980.tsp", "mo14185.tsp", "nu3496.tsp", "mu1979.tsp", "pm8079.tsp",
                                "qa194.tsp", "rw1621.tsp", "sw24978.tsp", "tz6117.tsp", "uy734.tsp", "vm22775.tsp",
                                "wi29.tsp", "ym7663.tsp", "zi929.tsp"};
    private static String[] matrixFiles = {"br17.atsp", "ft53.atsp", "ft70.atsp", "ftv170.atsp", "ftv33.atsp", "ftv35.atsp",
                                "ftv38.atsp", "ftv44.atsp", "ftv47.atsp", "ftv55.atsp", "ftv64.atsp", "ftv70.atsp",
                                "kro124p.atsp", "p43.atsp", "rbg323.atsp", "rbg358.atsp", "rbg403.atsp", "rbg443.atsp",
                                "ry48p.atsp"};

    /**
     * Fetches all the regions available in the database
     *
     * @return List of regions
     */
    public static String[] fetchAllRegions() {
        return regions;
    }

    /**
     * Fetches a particular symmetric data file that corresponds to a particular region
     *
     * @param region Region whose file is needed
     * @return filename of the region
     */
    public static String fetchSymmetricFile(String region) {
        for (int i = 0; i<regions.length; i++) {
            if (region == regions[i]) {
                return regionFiles[i];
            }
        }
        return "Invalid Region";
    }

    /**
     * Fetches all the asymmetric files available in the database
     *
     * @return List of asymmetric files
     */
    public static String[] fetchAllAsymmetricFiles() {
        return matrixFiles;
    }
}