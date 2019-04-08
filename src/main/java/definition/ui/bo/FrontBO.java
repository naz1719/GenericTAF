package definition.ui.bo;

import definition.ui.BaseBO;
import definition.ui.po.FrontPO;

public class FrontBO extends BaseBO {
    private FrontPO frontPO;

    public FrontBO() {
        frontPO = new FrontPO();
    }

    public FrontBO clickOnBuildingBlocks(){
        step("Click on 'Publication 200 Building Blocks'");
        frontPO.act_clickBuildingBlocks();
        return this;
    }

}
