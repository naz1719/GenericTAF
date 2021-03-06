package adaptation.parsers.excel;


import adaptation.parsers.excel.core.BaseExcel;
import adaptation.parsers.excel.model.excel.ExcelModelTitleProcessor;
import adaptation.parsers.excel.model.excel.ProductEx;
import adaptation.parsers.excel.model.payloads.ProductList;
import adaptation.parsers.file.FileUtilsWrapper;

import java.util.List;

import static definition.constants.CommonConsts.*;

public class Main {

    static {
//        copyFile(getExcelList(), OUTPUT_DIRECTORY);
        FILE_PATH = OUTPUT_DIRECTORY + "\\" + FileUtilsWrapper.getExcelList().get(0);
    }

    private BaseExcel baseExcel = new BaseExcel(FILE_PATH).openFile();

    public static void main(String[] args) {
        List<ProductEx> productExes = new Main().retrieveAllProducts();

    }

    public List<ProductEx> retrieveAllProducts() {
        List<ProductEx> products = baseExcel.unmarshal(ProductEx.class, SHEET1, 1);
        return products;
    }

    public List<ProductList> retrieveAllProductsWithTitles() {
        List<ProductEx> products = baseExcel.unmarshal(ProductEx.class, SHEET1, 6);
        return ExcelModelTitleProcessor.processAllProducts(products);
    }
}
