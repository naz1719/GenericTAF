package com.sample.project.po;


import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class SamplePO extends BasePO {

    @FindBy(id = "item_tcm:64-1112-2")
    private WebElement buildingBlocks;

    @FindBy(xpath = "//*[@id='AvocadoToolsPage_switch']//td[@class='tablabel']")
    private WebElement avocadoToolsTab;

    @FindBy(id = "AvocadoBtn")
    private WebElement avocadoBtn;


    public void act_clickBuildingBlocks() {
         buildingBlocks.click();
    }
    public void act_clickAvocadoToolsTab() {
         avocadoToolsTab.click();
    }
    public void act_clickAvocadoBtn() {
        avocadoBtn.click();
    }
}
