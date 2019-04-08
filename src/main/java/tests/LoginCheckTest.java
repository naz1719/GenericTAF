package tests;

import adaptation.ui.driver.WebDriverManager;
import definition.ui.bo.FrontBO;
import adaptation.ui.injector.Injector;
import execution.BaseTestClass;
import features.wait.WaitManager;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.annotations.Test;

import static definition.constants.CommonConsts.WEB_SITE_URL;

public class LoginCheckTest extends BaseTestClass {

    @Injector
    private FrontBO frontBO;

    @Test(skipFailedInvocations = true)
    public void testCheckLogin() throws InterruptedException {
        frontBO.openPortal(WEB_SITE_URL);
        WebElement webElement = WebDriverManager.getDriver().findElement(By.name("q"));
        waitManager.fluentElementWait(webElement);
        webElement.sendKeys("Hey");
        webElement.submit();

        WaitManager.sleepTimeOut(4000);

//        frontBO
//                .clickOnBuildingBlocks();

    }
}
