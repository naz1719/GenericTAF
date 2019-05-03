package features.properties.enums;

public enum GeneralPropNames {

    // UI Web Drivers config
    BROWSER("BROWSER"),
    OPERATION_SYSTEM("OPERATION_SYSTEM"),
    IMPLICITLY_WAIT_TIMEOUT("IMPLICITLY_WAIT_TIMEOUT"),
    HUB_URL("HUB_URL"),
    LOG_LEVEL_TYPE("LOG_LEVEL_TYPE");


    private String prop;

    GeneralPropNames(String prop) {
        this.prop = prop;
    }

    public String getValue() {
        return prop;
    }

}