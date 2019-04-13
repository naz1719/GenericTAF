package adaptation.ui.driver;

import definition.constants.CommonConsts;
import features.propertyLoader.PropertiesLoader;
import org.openqa.selenium.UnexpectedAlertBehaviour;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.htmlunit.HtmlUnitDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.phantomjs.PhantomJSDriver;
import org.openqa.selenium.remote.CapabilityType;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.concurrent.TimeUnit;


public class WebDriverFactory {

    protected PropertiesLoader propertiesLoader = new PropertiesLoader(CommonConsts.PATH_TO_CONFIGURATION_PROPERTIES);


    public WebDriver getDriverInstance() {
        Drivers driverType = Drivers.getDriverType(propertiesLoader.getBrowserName());
        String hubURLSystemProperty = propertiesLoader.getHubURLSystemProperty();

        if (hubURLSystemProperty != null && !hubURLSystemProperty.isEmpty()) {
            driverType = Drivers.REMOTE_WEB_DRIVER;
        }

        return getDriverInstance(driverType);
    }

    private WebDriver getDriverInstance(Drivers driverType) {

        switch (driverType) {

            case CHROME:
                System.setProperty("webdriver.chrome.driver",
                        propertiesLoader.getChromeDriverPath());
                WebDriver chrome = new ChromeDriver(getChromeCapabilities());
                chrome.manage().timeouts().implicitlyWait(propertiesLoader.getImplicitlyWaitTimeout(), TimeUnit.SECONDS);
                return chrome;

            case IE:
                System.setProperty("webdriver.ie.driver",
                        propertiesLoader.getInternetExplorerDriver_32Path());
                WebDriver ieDriver = new InternetExplorerDriver(getInternetExplorerCapabilities());
                ieDriver.manage().timeouts().implicitlyWait(propertiesLoader.getImplicitlyWaitTimeout(), TimeUnit.SECONDS);
                ieDriver.manage().window().maximize();
                return ieDriver;

            case FIREFOX:
                System.setProperty("webdriver.gecko.driver", propertiesLoader.getFirefoxDriverPath());
                WebDriver firefoxDriver = new FirefoxDriver(getFirefoxCapabilities());
                firefoxDriver.manage().timeouts().implicitlyWait(propertiesLoader.getImplicitlyWaitTimeout(), TimeUnit.SECONDS);
                return firefoxDriver;
            case HTML_UNIT_DRIVER:
                HtmlUnitDriver htmlUnitDriver = new HtmlUnitDriver();
                htmlUnitDriver.setJavascriptEnabled(true);
                return htmlUnitDriver;

            case GHOST_DRIVER:
                DesiredCapabilities DesireCaps = new DesiredCapabilities();
//                DesireCaps.setCapability(PhantomJSDriverService.PHANTOMJS_EXECUTABLE_PATH_PROPERTY, propertiesLoader.getGhostDriverPath());
                System.setProperty("phantomjs.binary.path", propertiesLoader.getGhostDriverPath());

                WebDriver ghostDriver = new PhantomJSDriver();
                return ghostDriver;

            case REMOTE_WEB_DRIVER:
                WebDriver driver = null;
                URL url = null;
                try {
                    String hubURL = propertiesLoader.getHubURLSystemProperty();
                    url = new URL(hubURL);
                } catch (MalformedURLException e) {
                    e.printStackTrace();
                }
                driver = new RemoteWebDriver(url, getChromeCapabilities());
                driver.manage().timeouts().implicitlyWait(propertiesLoader.getImplicitlyWaitTimeout(), TimeUnit.SECONDS);
                driver.manage().window().maximize();
                return driver;
            default:
                throw new RuntimeException("Unsupported driver type");
        }
    }

    private DesiredCapabilities getChromeCapabilities() {
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

    private DesiredCapabilities getFirefoxCapabilities() {
        DesiredCapabilities firefoxCapabilities = DesiredCapabilities.firefox();
        FirefoxOptions firefoxOptions = new FirefoxOptions();
        firefoxCapabilities.setCapability(FirefoxOptions.FIREFOX_OPTIONS, firefoxOptions);
        return firefoxCapabilities;
    }

    private DesiredCapabilities getInternetExplorerCapabilities() {
        DesiredCapabilities internetExplorerCapabilities = DesiredCapabilities.internetExplorer();
        internetExplorerCapabilities.setCapability("webdriver.ie.version", "11");
        internetExplorerCapabilities.setCapability(CapabilityType.ACCEPT_SSL_CERTS, true);
        internetExplorerCapabilities.setCapability(CapabilityType.UNEXPECTED_ALERT_BEHAVIOUR, UnexpectedAlertBehaviour.IGNORE);
        internetExplorerCapabilities.setCapability("ignoreProtectedModeSettings", true);
        return internetExplorerCapabilities;
    }
}