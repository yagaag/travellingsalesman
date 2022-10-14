package ViewHandler;

/**
 * CustomTimer class provides options of time delays for animation
 *
 * @author yagaa
 * @version 1.0.0
 */
public abstract class CustomTimer {

    /**
     * Creates a time delay of specified milliseconds and handles InterruptedExceptions
     *
     * @param t The time delay needed in milliseconds
     */
    public static void timeDelay(long t) {
        try {
            Thread.sleep(t);
        } catch (InterruptedException e) {}
    }
}
