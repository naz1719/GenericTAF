package adaptation.ui.driver;

public enum Drivers {
    CHROME("chrome"),
    IE("internet explorer"),
    FIREFOX("firefox"),
    REMOTE_WEB_DRIVER("remote web driver"),
    HTML_UNIT_DRIVER("html unit driver"),
    GHOST_DRIVER("ghost driver"),
    NULL("null driver");

    private String driverValue;

    Drivers(String driverValue) {
        this.driverValue = driverValue;
    }

    public static Drivers getDriverType(String driverValue) {
        for (Drivers drivers : Drivers.values()) {
            if (drivers.getDriverValue().equalsIgnoreCase(driverValue)) {
                return drivers;
            }
        }
        return NULL;
    }

    public String getDriverValue() {
        return driverValue;
    }
}
