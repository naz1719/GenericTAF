package com.sample.project.testUtils;

import com.sample.project.constants.CommonConsts;
import com.sample.project.core.driver.WebDriverManager;
import com.sample.project.core.injector.Injector;
import com.sample.project.utils.PropertiesLoader;
import com.sample.project.utils.WaitManager;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Listeners;

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
