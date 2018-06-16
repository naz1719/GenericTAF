package com.sample.project.bo;

import com.sample.project.po.SamplePO;

public class SampleBO extends BaseBO {
    private SamplePO samplePO;

    public SampleBO() {
        samplePO = new SamplePO();
    }

    public SampleBO clickOnBuildingBlocks(){
        step("Click on 'Publication 200 Building Blocks'");
        samplePO.act_clickBuildingBlocks();
        return this;
    }

}
