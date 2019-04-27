package execution;

import definition.constants.CommonConsts;
import execution.logger.TestLogger;
import features.properties.EnvInitializer;
import org.testng.ITestContext;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;

import java.lang.reflect.Method;

public class BaseTest {
    protected TestLogger LOG;

    @BeforeSuite(alwaysRun = true)
    public void loadProperties(ITestContext iTestContext) throws Exception {
        String country = iTestContext.getSuite().getParameter("country");
        System.setProperty(CommonConsts.ESCAPE_PROPERTY, "false");
        try {
            //must be done only once (not in each suite)
            EnvInitializer.initEnvProperties();

        } catch (Throwable e) {
            throw new RuntimeException(e);
        }
    }

    @BeforeMethod
    public void initLogger(Method method) {
        LOG = TestLogger.getLogger(method.getName(), method.getDeclaringClass().getSimpleName());
    }

    @AfterMethod
    public void dropLogger() {
        LOG.drop();
    }


}
