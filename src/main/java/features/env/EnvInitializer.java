package features.env;

import execution.logger.AllureLogger;
import features.env.general.EnvPropertyHolder;
import features.utils.CustomUtils;
import io.qameta.allure.restassured.AllureRestAssured;
import io.restassured.RestAssured;

import java.io.File;
import java.io.FileReader;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

public class EnvInitializer {
    private static EnvInitializer instance;

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
            initEnvGeneralProperties();
        }
        return instance;
    }


    private static void initEnvGeneralProperties() throws Exception {

        Map envProps = EnvPropertyHolder.getEnvPropertyMaps();
        Properties gprop = new Properties();
        gprop.load(new FileReader(new File(CustomUtils.getGeneralPropFilePath())));
        HashMap gmap = new HashMap(gprop);

        //Validate is credential defined in general property file
//        if (gmap.get("azure.account.name").equals("__azure_account_name__") || gmap.get("azure.account.key").equals("__azure_account_key__")) {
//            throw new RuntimeException("ATTENTION: Could you set correct credentials for azure in general.properties file");
//        }

        envProps.put(PropertiesMap.general, gmap);
    }
}



