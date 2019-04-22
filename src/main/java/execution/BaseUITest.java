package execution;

import adaptation.ui.driver.WebDriverManager;
import execution.injector.Injector;
import features.wait.UIWaitManager;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;

import java.lang.annotation.Annotation;
import java.lang.reflect.Field;


public abstract class BaseUITest extends BaseTest{
    protected UIWaitManager waitManager = UIWaitManager.getInstance();

    @BeforeMethod
    public void injectFields() {
        createInstance(this.getClass());
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



    public void createInstance(Class<? extends BaseUITest> clazz) {
        Field[] fields = clazz.getDeclaredFields();
        for (Field field : fields) {
            Annotation[] annotations = field.getAnnotations();
            for (Annotation annotation : annotations) {
                if (annotation instanceof Injector) {
                    try {
                        LOG.debug("Trying to create instance " + field.getType());
                        Object object = field.getType().newInstance();
                        field.setAccessible(true);
                        field.set(this, object);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            }
        }
    }
}
