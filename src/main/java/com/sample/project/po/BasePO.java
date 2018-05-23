package com.sample.project.po;

import com.sample.project.testUtils.TestLogger;
import com.sample.project.utils.WaitManager;
import com.sample.project.core.driver.WebDriverManager;
import org.openqa.selenium.support.PageFactory;

public class BasePO {
    protected WaitManager waitManager = new WaitManager();
    protected TestLogger LOG = TestLogger.getLogger();

    public BasePO() {
        PageFactory.initElements(WebDriverManager.getDriver(), this);
    }
}
