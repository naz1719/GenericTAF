package execution;

import adaptation.ui.driver.WebDriverManager;
import adaptation.ui.injector.Injector;
import definition.constants.CommonConsts;
import execution.logger.TestListener;
import execution.logger.TestLogger;
import features.env.EnvInitializer;
import features.wait.UIWaitManager;
import org.testng.ITestContext;
import org.testng.annotations.*;

import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.lang.reflect.Method;


@Listeners(TestListener.class)
public abstract class BaseTestClass {

    protected UIWaitManager waitManager = new UIWaitManager();

    protected TestLogger LOG;

    @BeforeSuite(alwaysRun = true)
    public void configureEnvProperties(ITestContext iTestContext) throws Exception {
        String country = iTestContext.getSuite().getParameter("country");
        System.setProperty(CommonConsts.ESCAPE_PROPERTY, "false");
        try {
            //must be done only once (not in each suite)
            EnvInitializer.initEnvProperties();

        } catch (Throwable e) {
            throw new RuntimeException(e);
        }
    }

    public void step(String message) {
        LOG.info(message);
    }


    @BeforeMethod
    public void beforeMethod(Method method) {
        LOG = TestLogger.getLogger(method.getName(), method.getDeclaringClass().getSimpleName());
        createInstance();
    }

    @AfterMethod
    public void dropDriver() {
        WebDriverManager.stop();
        LOG.drop();
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


    private void createInstance() {
        Class<?> clazz = this.getClass();
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
