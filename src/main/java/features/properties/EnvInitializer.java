package features.properties;

import execution.logger.AllureLogger;
import io.qameta.allure.restassured.AllureRestAssured;
import io.restassured.RestAssured;

import java.util.Properties;

public class EnvInitializer {
    private static final String GENERAL_PROPERTIES_FILE = "general.properties";
    private static EnvInitializer instance;
    private static Properties PROPERTIES;

    static {
        RestAssured.filters(new AllureRestAssured());
    }

    private EnvInitializer() {
    }

    public static EnvInitializer initEnvProperties() throws Exception {
        if (instance == null) {
            instance = new EnvInitializer();
            AllureLogger.logToslf4j = true;
            AllureLogger.logSoapCallToAllure = true;
            PROPERTIES = PropertyLoader.loadProperties(EnvInitializer.GENERAL_PROPERTIES_FILE);
        }
        return instance;
    }


    public static String getProperty(String key) {
        return PropertyLoader.getProperty(key, PROPERTIES);
    }

    //    private static void validateSmokeProperties() {
    //Validate is credential defined in general property file
//        if (gmap.get("azure.account.name").equals("__azure_account_name__") || gmap.get("azure.account.key").equals("__azure_account_key__")) {
//            throw new RuntimeException("ATTENTION: Could you set correct credentials for azure in general.properties file");
//        }
//    }
}



