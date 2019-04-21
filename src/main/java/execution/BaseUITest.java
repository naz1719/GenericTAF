package execution;

import adaptation.ui.driver.WebDriverManager;
import adaptation.ui.injector.InjectorBhv;
import features.wait.UIWaitManager;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;


public abstract class BaseUITest extends BaseTest{
    protected UIWaitManager waitManager = UIWaitManager.getInstance();

    @BeforeMethod
    public void injectFields() {
        InjectorBhv.getInstance().createInstance();
    }

    @AfterMethod
    public void dropDriver() {
        WebDriverManager.stop();
    }

//    @AfterClass
//    public void dropAllDrivers() {
//        try {
//            Runtime.getRuntime().exec("taskkill /IM chromedriver.exe /F");
//            Runtime.getRuntime().exec("taskkill /IM chrome.exe /F");
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//    }



}
