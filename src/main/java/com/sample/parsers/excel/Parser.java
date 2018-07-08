package com.sample.parsers.excel;


import com.sample.parsers.excel.core.BaseExcel;
import com.sample.parsers.excel.model.excel.ExcelModelTitleProcessor;
import com.sample.parsers.excel.model.excel.ProductEx;
import com.sample.parsers.excel.model.payloads.ProductList;

import java.util.List;

import static com.sample.constants.CommonConsts.*;
import static com.sample.parsers.fileUtils.FileUtilsWrapper.getExcel;

public class Parser {

    static {
//        copyFileUsingApacheCommonsIO(getExcel(), OUTPUT_DIRECTORY);
        FILE_PATH = OUTPUT_DIRECTORY + "\\" + getExcel();
    }

    private BaseExcel baseExcel = new BaseExcel(FILE_PATH).openFile();

    public List<ProductEx> retrieveAllProducts() {
        List<ProductEx> products = baseExcel.unmarshal(ProductEx.class, SHEET1, 1);
        return products;
    }

    public List<ProductList> retrieveAllProductsWithTitles() {
        List<ProductEx> products = baseExcel.unmarshal(ProductEx.class, SHEET1, 6);
        return ExcelModelTitleProcessor.processAllProducts(products);
    }
}
