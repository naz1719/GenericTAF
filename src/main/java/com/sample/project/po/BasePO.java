package com.sample.project.po;

import com.sample.core.testUtils.TestLogger;
import com.sample.core.utils.WaitManager;
import com.sample.core.core.driver.WebDriverManager;
import org.openqa.selenium.support.PageFactory;

public class BasePO {
    protected WaitManager waitManager = new WaitManager();
    protected TestLogger LOG = TestLogger.getLogger();

    public BasePO() {
        PageFactory.initElements(WebDriverManager.getDriver(), this);
    }
}
