package project.po;

import core.testUtils.TestLogger;
import core.utils.WaitManager;
import core.core.driver.WebDriverManager;
import org.openqa.selenium.support.PageFactory;

public class BasePO {
    protected WaitManager waitManager = new WaitManager();
    protected TestLogger LOG = TestLogger.getLogger();

    public BasePO() {
        PageFactory.initElements(WebDriverManager.getDriver(), this);
    }
}
