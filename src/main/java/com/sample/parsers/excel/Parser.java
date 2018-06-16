package com.sample.parsers.excel;


import com.sample.parsers.excel.core.BaseExcel;
import com.sample.parsers.excel.model.excel.ExcelModelTitleProcessor;
import com.sample.parsers.excel.model.excel.ProductEx;
import com.sample.parsers.excel.model.payloads.ProductList;

import java.util.List;

import static com.sample.constants.CommonConsts.FILE_PATH;
import static com.sample.constants.CommonConsts.OUTPUT_DIRECTORY;
import static com.sample.constants.CommonConsts.SAMPLE_SHEET;
import static com.sample.parsers.fileUtils.Utils.copyFileUsingApacheCommonsIO;
import static com.sample.parsers.fileUtils.Utils.getExcelPath;

public class Parser {

//    static {
//        copyFileUsingApacheCommonsIO(getExcelPath(), OUTPUT_DIRECTORY);
//        FILE_PATH = OUTPUT_DIRECTORY + "\\" + getExcelPath();
//    }

    private BaseExcel baseExcel = new BaseExcel(FILE_PATH).openFile();

    public List<ProductList> retrieveAllProductsWithTitles() {
        List<ProductEx> products = baseExcel.unmarshal(ProductEx.class, SAMPLE_SHEET, 6);
        return ExcelModelTitleProcessor.processAllProducts(products);
    }
}
