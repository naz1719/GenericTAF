package execution;

import execution.logger.TestListener;
import org.testng.annotations.Listeners;

@Listeners(TestListener.class)
public abstract class BaseAPITest extends  BaseTest{


}


