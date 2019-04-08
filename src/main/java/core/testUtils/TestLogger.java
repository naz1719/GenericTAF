package core.testUtils;

import constants.CommonConsts;
import core.utils.PropertiesLoader;
import io.qameta.allure.Step;
import org.apache.log4j.*;

public class TestLogger {
    private static TestLogger logger;
    private Logger LOG;
    private PropertiesLoader propertiesLoader = new PropertiesLoader(CommonConsts.PATH_TO_CONFIGURATION_PROPERTIES);

    private TestLogger(String testName, String className) {
        LOG = Logger.getLogger(testName);
        LOG.setLevel(Level.toLevel(propertiesLoader.getLogType()));
        BasicConfigurator.resetConfiguration();

        FileAppender fileAppender = new FileAppender();
        String fileName =String.format("%s\\testUtils-output\\logs\\%s\\%s.log", System.getProperty("user.dir"), className, testName);
        fileAppender.setFile(fileName);
        fileAppender.setLayout(new PatternLayout("[%-5p] %d{HH:mm:ss} %c: %m%n"));
        fileAppender.setAppend(false);
        fileAppender.setName("FileAppender");
        fileAppender.setThreshold(Level.DEBUG);
        fileAppender.activateOptions();
        LOG.addAppender(fileAppender);

        ConsoleAppender consoleAppender = new ConsoleAppender();
        consoleAppender.setLayout(new PatternLayout("[%-5p] %d{HH:mm:ss} %c: %m%n"));
        consoleAppender.setName("ConsoleAppender");
        consoleAppender.setThreshold(Level.INFO);
        consoleAppender.activateOptions();
        LOG.addAppender(consoleAppender);
    }

    public static TestLogger getLogger(String testName, String className) {
        if (logger == null) {
            logger = new TestLogger(testName, className);
        }
        return logger;
    }

    public static TestLogger getLogger() {
        if (logger == null) {
            System.out.println("getLogger(name) is not executed, testUtils name is not set up");
        }
        return logger;
    }

    @Step("{0}")
    public void info(Object message) {
        LOG.info(message);
    }

    public void debug(Object message) {
        LOG.debug(message);
    }

    public void error(String message) {
        LOG.error(message);
    }

    public void drop() {
        if(logger != null)
        logger = null;
    }
}
