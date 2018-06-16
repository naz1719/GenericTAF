package com.sample.project.po;


import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class SamplePO extends BasePO {

    @FindBy(id = "item_tcm:64-1112-2")
    private WebElement buildingBlocks;


    public void act_clickBuildingBlocks() {
         buildingBlocks.click();
    }

}
