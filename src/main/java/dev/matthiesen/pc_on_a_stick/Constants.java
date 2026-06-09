package dev.matthiesen.pc_on_a_stick;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class Constants {
    public static final String MOD_ID = "pc_on_a_stick";
    public static final String MOD_NAME = "Cobblemon PC on a Stick";

    public static Logger LOGGER = LogManager.getLogger(MOD_NAME);

    public static void createInfoLog(String message) {
        LOGGER.info(message);
    }

    public static void createErrorLog(String message, Throwable throwable) {
        LOGGER.error(message, throwable);
    }
}
