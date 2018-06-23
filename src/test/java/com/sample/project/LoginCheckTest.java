package com.sample.project;

import com.sample.core.core.driver.WebDriverManager;
import com.sample.project.bo.FrontBO;
import com.sample.core.core.injector.Injector;
import com.sample.core.testUtils.BaseTestClass;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.annotations.Test;

import static com.sample.constants.CommonConsts.WEB_SITE_URL;

public class LoginCheckTest extends BaseTestClass {

    @Injector
    private FrontBO frontBO;

    @Test(skipFailedInvocations = true)
    public void testCheckLogin() {
        frontBO.openPortal(WEB_SITE_URL);
        WebElement webElement = WebDriverManager.getDriver().findElement(By.name("q"));
        waitManager.fluentElementWait(webElement);
        webElement.sendKeys("Hey");
//        frontBO
//                .clickOnBuildingBlocks();

    }
}
