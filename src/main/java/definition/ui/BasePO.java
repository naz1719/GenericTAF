package definition.ui;

import execution.logger.TestLogger;
import features.wait.UIWaitManager;
import adaptation.ui.driver.WebDriverManager;
import org.openqa.selenium.support.PageFactory;

public class BasePO {
    protected UIWaitManager waitManager = new UIWaitManager();
    protected TestLogger LOG = TestLogger.getLogger();

    public BasePO() {
        PageFactory.initElements(WebDriverManager.getDriver(), this);
    }
}
