package adaptation.parsers.excel.model.excel;


import adaptation.parsers.excel.annotation.ExcelCell;

public class ProductEx {
    @ExcelCell(0)
    private String date;

    @ExcelCell(1)
    private String orderId;

    @ExcelCell(2)
    private String sku;

    @ExcelCell(3)
    private String transactionType;

    @ExcelCell(4)
    private String paymentType;

    @ExcelCell(5)
    private String paymentDetail;

    @ExcelCell(6)
    private String amount;

    @ExcelCell(7)
    private String quantity;

    @ExcelCell(8)
    private String productTitle;

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getOrderId() {
        return orderId;
    }

    public void setOrderId(String orderId) {
        this.orderId = orderId;
    }

    public String getSku() {
        return sku;
    }

    public void setSku(String sku) {
        this.sku = sku;
    }

    public String getTransactionType() {
        return transactionType;
    }

    public void setTransactionType(String transactionType) {
        this.transactionType = transactionType;
    }

    public String getPaymentType() {
        return paymentType;
    }

    public void setPaymentType(String paymentType) {
        this.paymentType = paymentType;
    }

    public String getPaymentDetail() {
        return paymentDetail;
    }

    public void setPaymentDetail(String paymentDetail) {
        this.paymentDetail = paymentDetail;
    }

    public String getAmount() {
        return amount;
    }

    public void setAmount(String amount) {
        this.amount = amount;
    }

    public String getQuantity() {
        return quantity;
    }

    public void setQuantity(String quantity) {
        this.quantity = quantity;
    }

    public String getProductTitle() {
        return productTitle;
    }

    public void setProductTitle(String productTitle) {
        this.productTitle = productTitle;
    }
}
