package adaptation.ui.driver;

import features.env.general.GeneralProperties;
import features.env.properties.GeneralPropNames;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.remote.RemoteWebDriver;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.concurrent.TimeUnit;

import static adaptation.ui.driver.Drivers.*;


public class WebDriverFactory {

    protected String BROWSER_NAME = GeneralProperties.getGeneralCommonProperty(GeneralPropNames.browser);
    protected String IMPLICITLY_WAIT_TIMEOUT = GeneralProperties.getGeneralCommonProperty(GeneralPropNames.implicitly_wait_timeout);


    public WebDriver getDriverInstance() {
        Drivers driverType = Drivers.getDriverType(BROWSER_NAME);
        String hubURLSystemProperty = GeneralProperties.getGeneralCommonProperty(GeneralPropNames.hub_URL);

        if (!hubURLSystemProperty.equalsIgnoreCase("_hubURL_") && !hubURLSystemProperty.isEmpty()) {
            driverType = Drivers.REMOTE_WEB_DRIVER;
        }

        return getDriverInstance(driverType);
    }

    private WebDriver getDriverInstance(Drivers driverType) {

        switch (driverType) {
            case CHROME:
                System.setProperty(CHROME.getProperty(), CHROME.getDriverPath());
                WebDriver chrome = new ChromeDriver(CHROME.getDesiredCapabilities());
                chrome.manage().timeouts().implicitlyWait(Long.parseLong(IMPLICITLY_WAIT_TIMEOUT), TimeUnit.SECONDS);
                return chrome;
            case IE:
                System.setProperty(IE.getProperty(), IE.getDriverPath());
                WebDriver ieDriver = new InternetExplorerDriver(IE.getDesiredCapabilities());
                ieDriver.manage().timeouts().implicitlyWait(Long.parseLong(IMPLICITLY_WAIT_TIMEOUT), TimeUnit.SECONDS);
                ieDriver.manage().window().maximize();
                return ieDriver;
            case FIREFOX:
                System.setProperty(FIREFOX.getProperty(), FIREFOX.getDriverPath());
                WebDriver firefoxDriver = new FirefoxDriver(FIREFOX.getDesiredCapabilities());
                firefoxDriver.manage().timeouts().implicitlyWait(Long.parseLong(IMPLICITLY_WAIT_TIMEOUT), TimeUnit.SECONDS);
                return firefoxDriver;
//            case HTML_UNIT_DRIVER:
//                HtmlUnitDriver htmlUnitDriver = new HtmlUnitDriver();
//                htmlUnitDriver.setJavascriptEnabled(true);
//                return htmlUnitDriver;
//            case GHOST_DRIVER:
//                DesiredCapabilities desireCaps = new DesiredCapabilities();
//                System.setProperty(GHOST_DRIVER.getProperty(), GHOST_DRIVER.getDriverPath());
//                WebDriver ghostDriver = new PhantomJSDriver();
//                return ghostDriver;
            case REMOTE_WEB_DRIVER:
                WebDriver driver;
                URL hubUrl;
                try {
                    hubUrl = new URL(Drivers.REMOTE_WEB_DRIVER.getDriverPath());
                } catch (MalformedURLException e) {
                    throw new RuntimeException(e);
                }
                driver = new RemoteWebDriver(hubUrl, CHROME.getDesiredCapabilities());
                driver.manage().timeouts().implicitlyWait(Long.parseLong(IMPLICITLY_WAIT_TIMEOUT), TimeUnit.SECONDS);
                driver.manage().window().maximize();
                return driver;
            default:
                throw new RuntimeException("Unsupported driver type");
        }
    }


}