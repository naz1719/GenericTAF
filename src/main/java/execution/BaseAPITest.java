package execution;

import definition.constants.CommonConsts;
import execution.logger.TestListener;
import features.env.EnvInitializer;
import org.testng.ITestContext;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Listeners;

/**
 * Created by rishchenko on 19.08.2017.
 */

@Listeners(TestListener.class)
public class BaseAPITest {

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


}


