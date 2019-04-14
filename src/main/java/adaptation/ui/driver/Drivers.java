package adaptation.ui.driver;

import definition.constants.CommonConsts;
import org.openqa.selenium.UnexpectedAlertBehaviour;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.remote.CapabilityType;
import org.openqa.selenium.remote.DesiredCapabilities;

public enum Drivers {

    CHROME("webdriver.chrome.driver", "chrome", CommonConsts.COMMON_DRIVER_PATH + "chromedriver.exe", getChromeCapabilities()),
    IE("webdriver.ie.driver", "internet explorer", CommonConsts.COMMON_DRIVER_PATH + "IEDriverServer32.exe", getInternetExplorerCapabilities()),
    FIREFOX("webdriver.gecko.driver", "firefox", CommonConsts.COMMON_DRIVER_PATH + "geckodriver.exe", null),
    REMOTE_WEB_DRIVER(null, "remote web driver", "http://localhost:5555/wd/hub", getChromeCapabilities()),
    HTML_UNIT_DRIVER(null, "html unit driver", null, null),
    GHOST_DRIVER("phantomjs.binary.path", "ghost driver", CommonConsts.COMMON_DRIVER_PATH + "phantomjs.exe", null);

    private String property;
    private String driverValue;
    private String driverPath;
    private DesiredCapabilities desiredCapabilities;


    Drivers(String property, String driverValue, String driverPath, DesiredCapabilities desiredCapabilities) {
        this.property = property;
        this.driverValue = driverValue;
        this.driverPath = driverPath;
        this.desiredCapabilities = desiredCapabilities;
    }

    public static Drivers getDriverType(String driverValue) {
        for (Drivers drivers : Drivers.values()) {
            if (drivers.getDriverValue().equalsIgnoreCase(driverValue)) {
                return drivers;
            }
        }
        throw new RuntimeException("Driver " + driverValue + " doesn't support, review name of driver");
    }


    public DesiredCapabilities getDesiredCapabilities() {
        return desiredCapabilities;
    }

    private static DesiredCapabilities getChromeCapabilities() {
        DesiredCapabilities chromeCapabilities = DesiredCapabilities.chrome();
        chromeCapabilities.setCapability(CapabilityType.ACCEPT_SSL_CERTS, true);
        chromeCapabilities.setCapability(CapabilityType.UNEXPECTED_ALERT_BEHAVIOUR,
                UnexpectedAlertBehaviour.ACCEPT);
        chromeCapabilities.setCapability("browserConnectionEnabled", true);

        ChromeOptions chromeOptions = new ChromeOptions();
        chromeOptions.addArguments("--no-sandbox");
        chromeCapabilities.setCapability(ChromeOptions.CAPABILITY, chromeOptions);
        return chromeCapabilities;
    }

    private static DesiredCapabilities getFirefoxCapabilities() {
        DesiredCapabilities firefoxCapabilities = DesiredCapabilities.firefox();
        FirefoxOptions firefoxOptions = new FirefoxOptions();
        firefoxCapabilities.setCapability(FirefoxOptions.FIREFOX_OPTIONS, firefoxOptions);
        return firefoxCapabilities;
    }

    private static DesiredCapabilities getInternetExplorerCapabilities() {
        DesiredCapabilities internetExplorerCapabilities = DesiredCapabilities.internetExplorer();
        internetExplorerCapabilities.setCapability("webdriver.ie.version", "11");
        internetExplorerCapabilities.setCapability(CapabilityType.ACCEPT_SSL_CERTS, true);
        internetExplorerCapabilities.setCapability(CapabilityType.UNEXPECTED_ALERT_BEHAVIOUR, UnexpectedAlertBehaviour.IGNORE);
        internetExplorerCapabilities.setCapability("ignoreProtectedModeSettings", true);
        return internetExplorerCapabilities;
    }

    public String getProperty() {
        return property;
    }

    public String getDriverPath() {
        return driverPath;
    }

    public String getDriverValue() {
        return driverValue;
    }
}
