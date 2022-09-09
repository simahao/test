package logtest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class MyApp {

    // Define a static logger variable so that it references the
    // Logger instance named "MyApp".
    private static final Logger LOGGER = LoggerFactory.getLogger(MyApp.class);

    public static void main(final String... args) {

        // Set up a simple configuration that logs on the console.

        LOGGER.debug("Entering application.");
        Bar bar = new Bar();
        if (!bar.doIt()) {
            LOGGER.error("Didn't do it.");
        }
        LOGGER.info("Exiting application.");
    }
}
