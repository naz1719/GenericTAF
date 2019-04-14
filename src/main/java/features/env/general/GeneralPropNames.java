package features.env.general;

public enum GeneralPropNames {

    // configuration properties, UI Web Drivers config
    hub_URL("hubURL"),
    browser("browser"),
    implicitly_wait_timeout("implicitlyWaitTimeout"),
    log_level_type("logLevelType");


    private String prop;

    GeneralPropNames(String prop) {
        this.prop = prop;
    }

    public String getValue() {
        return prop;
    }

}