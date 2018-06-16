package com.sample.project;

import com.sample.core.core.driver.WebDriverManager;
import com.sample.project.bo.SampleBO;
import com.sample.core.core.injector.Injector;
import com.sample.core.testUtils.BaseTestClass;
import org.openqa.selenium.By;
import org.testng.annotations.Test;

import static com.sample.constants.CommonConsts.WEB_SITE_URL;

public class LoginCheckTest extends BaseTestClass {

    @Injector
    private SampleBO sampleBO;

    @Test(skipFailedInvocations = true)
    public void testCheckLogin() {
        sampleBO.openPortal(WEB_SITE_URL);
        waitManager.fluentElementWait(WebDriverManager.getDriver().findElement(By.name("q")));
//        sampleBO
//                .clickOnBuildingBlocks();

    }
}
