package com.sample.core.testUtils;

import com.sample.constants.CommonConsts;
import com.sample.core.core.driver.WebDriverManager;
import com.sample.core.core.injector.Injector;
import com.sample.core.utils.PropertiesLoader;
import com.sample.core.utils.WaitManager;
import org.testng.annotations.*;

import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.lang.reflect.Method;


@Listeners(TestListener.class)
public abstract class BaseTestClass {

    protected WaitManager waitManager = new WaitManager();

    protected PropertiesLoader propertiesLoader = new PropertiesLoader(CommonConsts.PATH_TO_CONFIGURATION_PROPERTIES);

    protected TestLogger LOG;

    @BeforeClass
    public void browserInstantiate() {
        System.setProperty(CommonConsts.ESCAPE_PROPERTY, "false");
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
        LOG.drop();
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
