package project.po;

import execution.logger.TestLogger;
import features.wait.WaitManager;
import adaptation.ui.driver.WebDriverManager;
import org.openqa.selenium.support.PageFactory;

public class BasePO {
    protected WaitManager waitManager = new WaitManager();
    protected TestLogger LOG = TestLogger.getLogger();

    public BasePO() {
        PageFactory.initElements(WebDriverManager.getDriver(), this);
    }
}
