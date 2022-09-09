package logtest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Bar {
    static final Logger LOGGER = LoggerFactory.getLogger(Bar.class.getName());

    public boolean doIt() {
        LOGGER.error("Did it again!");
        return true;
    }
}
