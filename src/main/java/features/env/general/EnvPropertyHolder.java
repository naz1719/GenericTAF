package features.env.general;

import java.util.HashMap;
import java.util.Map;

public class EnvPropertyHolder {

    private static Map<String, Map<String, String>> ENV_PROPS = new HashMap<>();

    public static Map<String, Map<String, String>> getEnvPropertyMaps() {
        return ENV_PROPS;
    }


}
