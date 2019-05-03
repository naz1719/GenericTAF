package adaptation.ui.driver;

import definition.constants.CommonConsts;
import features.properties.EnvInitializer;
import features.properties.enums.GeneralPropNames;
import features.properties.enums.OperationSystems;
import org.openqa.selenium.UnexpectedAlertBehaviour;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.remote.CapabilityType;
import org.openqa.selenium.remote.DesiredCapabilities;

public enum Drivers {

    CHROME("webdriver.chrome.driver", "chrome", "chromedriver", getChromeCapabilities()),
    IE("webdriver.ie.driver", "internet explorer", "IEDriverServer32", getInternetExplorerCapabilities()),
    FIREFOX("webdriver.gecko.driver", "firefox", "geckodriver", getFirefoxCapabilities()),
    REMOTE_WEB_DRIVER(),
    HTML_UNIT_DRIVER(null, "html unit driver", null, null),
    GHOST_DRIVER("phantomjs.binary.path", "ghost driver", "phantomjs", null);

    private String property;
    private String driverValue;
    private String driverPath;
    private DesiredCapabilities desiredCapabilities;

    Drivers() {
    }

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

    public DesiredCapabilities getDesiredCapabilities() {
        return desiredCapabilities;
    }

    public String getProperty() {
        return property;
    }

    public String getDriverPath() {
        String os =  EnvInitializer.getProperty(GeneralPropNames.OPERATION_SYSTEM.getValue());
        String absoluteDriverPath = null;
        if (os.equalsIgnoreCase(OperationSystems.WINDOWS.getOperationSystem())) {
            absoluteDriverPath = CommonConsts.COMMON_DRIVER_PATH+"windows/"+ driverPath + ".exe";
        } else if (os.equalsIgnoreCase(OperationSystems.LINUX.getOperationSystem())) {
            absoluteDriverPath = CommonConsts.COMMON_DRIVER_PATH+"linux/"+ driverPath;
        } else {
            throw new RuntimeException("The framework doesn't support os: [" + os+"]");
        }
        return absoluteDriverPath;
    }

    public String getDriverValue() {
        return driverValue;
    }
}
