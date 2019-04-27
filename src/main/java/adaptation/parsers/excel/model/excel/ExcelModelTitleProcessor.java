package adaptation.parsers.excel.model.excel;


import adaptation.parsers.excel.model.payloads.ProductList;

import java.util.ArrayList;
import java.util.List;

import static java.util.stream.Collectors.toList;

public class ExcelModelTitleProcessor {

    public static List<ProductList> processAllProducts(List<ProductEx> products) {
        List<String> uniqueTitles = getTitle(products);
        List<ProductList> pvModelsList = new ArrayList<>();

        uniqueTitles.forEach(title -> {
            List<ProductEx> pvList = getAllExcelRowPvForProductID(products, title);
            ProductList pvModel = transformToPvModel(pvList, title);

            pvModelsList.add(pvModel);
        });

        return pvModelsList;
    }


    public static ProductList processFirstPV(List<ProductEx> products) {
        List<ProductList> pvModelsList = processAllProducts(products);
        return pvModelsList.get(0);
    }


    private static List<String> getTitle(List<ProductEx> products) {
        List<String> allProductIds = new ArrayList<>();
        products.forEach(product -> allProductIds.add(product.getProductTitle()));

        return allProductIds.stream().distinct().collect(toList());
    }


    private static List<ProductEx> getAllExcelRowPvForProductID(List<ProductEx> products, String id) {
        return products
                .stream()
                .filter(x -> x.getProductTitle().equalsIgnoreCase(id))
                .collect(toList());
    }


    private static ProductList transformToPvModel(List<ProductEx> excelRowPVs, String title) {
        ProductList pv = new ProductList();
        pv.setProducts(excelRowPVs);
        pv.setProductTitle(title);
        return pv;
    }


}
