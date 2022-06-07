package logtest;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class Bar {
    static final Logger LOGGER = LogManager.getLogger(Bar.class.getName());

    public boolean doIt() {
        LOGGER.entry();
        LOGGER.error("Did it again!");
        return LOGGER.exit(false);
    }
}
