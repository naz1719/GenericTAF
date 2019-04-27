package definition.ui;

import adaptation.ui.driver.WebDriverManager;
import execution.logger.TestLogger;
import features.wait.UIWaitManager;
import org.openqa.selenium.support.PageFactory;

public class BasePO {
    protected UIWaitManager waitManager = UIWaitManager.getInstance();
    protected TestLogger LOG = TestLogger.getLogger();

    public BasePO() {
        PageFactory.initElements(WebDriverManager.getDriver(), this);
    }
}
