package definition.ui.po;


import definition.ui.BasePO;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class FrontPO extends BasePO {

    @FindBy(id = "item_tcm:64-1112-2")
    private WebElement buildingBlocks;


    public void act_clickBuildingBlocks() {
         buildingBlocks.click();
    }

}
