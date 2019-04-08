package project;

import adaptation.ui.driver.WebDriverManager;
import project.bo.FrontBO;
import adaptation.ui.injector.Injector;
import execution.BaseTestClass;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.annotations.Test;

import static constants.CommonConsts.WEB_SITE_URL;

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
