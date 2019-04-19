package execution;

import definition.constants.CommonConsts;
import features.env.EnvInitializer;
import org.testng.ITestContext;
import org.testng.annotations.BeforeSuite;

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


