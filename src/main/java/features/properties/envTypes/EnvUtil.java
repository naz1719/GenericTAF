package features.properties.envTypes;

public class EnvUtil {
    public static final String ENV_TYPE = checkEnvType(System.getProperty("envType")).toLowerCase();


    private static String checkEnvType(String envValue) {
        for (EnvironmentType environmentType : EnvironmentType.values()) {
            if (environmentType.getEnvType().equalsIgnoreCase(envValue)) {
                break;
            }
        }
        if (envValue == null) {
            String error = "Can't start automation without Environment type. EnvType is [" + envValue + "]. Supported only " + getEnvOptions();
            throw new RuntimeException(error);
        }
        return envValue;

    }

    private static String getEnvOptions() {
        StringBuffer stringBuffer = new StringBuffer();
        for (EnvironmentType environmentType : EnvironmentType.values()) {
            stringBuffer.append("[");
            stringBuffer.append(environmentType.getEnvType());
            stringBuffer.append("]");
        }
        return stringBuffer.toString();
    }
}
