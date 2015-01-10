package ua.com.rozetka.utils;


public class Logger {

    private static final org.apache.log4j.Logger APACHE_LOGGER = org.apache.log4j.Logger.getLogger(Logger.class);

    private static final String SEPARATOR = "***************************************************************************************";
    private static final String TEST_START = "TEST START: %s";
    private static final String TEST_FINISH = "TEST FINISH: %s";
    private static final String ELEMENT_PRESENT = "PRESENT: %s";
    private static final String ELEMENT_NOT_FOUND = "NOT FOUND: %s";
    private static final String INFO_LOG = "INFO: %s";
    private static final String EXCEPTION_LOG = "EXCEPTION: %s";
    private static final String ERROR_LOG = "ERROR: %s";
    private static final String BROWSER_INFO = "BROWSER: %s";

    public static void logBrowserSetUp(String browserName, int timeout, String appUrl) {
        APACHE_LOGGER.info(SEPARATOR);
        APACHE_LOGGER.info(String.format("Let's test! Browser: %s. Timeout: %s. Application URL: %s.",
                browserName, timeout, appUrl));
        APACHE_LOGGER.info(SEPARATOR);
    }

    public static void logTestStart(String testName) {
        APACHE_LOGGER.info(SEPARATOR);
        APACHE_LOGGER.info(String.format(TEST_START, testName));
    }

    public static void logTestFinish(String testName) {
        APACHE_LOGGER.info(String.format(TEST_FINISH, testName));
        APACHE_LOGGER.info(SEPARATOR);
    }

    public static void logElementPresent(String elementName) {
        APACHE_LOGGER.info(String.format(ELEMENT_PRESENT, elementName));
    }

    public static void logElementNotPresent(String elementName) {
        APACHE_LOGGER.info(String.format(ELEMENT_NOT_FOUND, elementName));
    }

    public static void info(String info) {
        APACHE_LOGGER.info(String.format(INFO_LOG, info));
    }

    public static void logException(String exceptionText) {
        APACHE_LOGGER.info(String.format(EXCEPTION_LOG, exceptionText));
    }

    public static void logError(String errorText) {
        APACHE_LOGGER.info(String.format(ERROR_LOG, errorText));
    }

    public static void logBrowserInfo(String info) {
        APACHE_LOGGER.info(String.format(BROWSER_INFO, info));
    }

}
