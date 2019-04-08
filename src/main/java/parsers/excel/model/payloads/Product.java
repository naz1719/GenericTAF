package parsers.excel.model.payloads;

public class Product {
    private String date;
    private Integer orderId;
    private String sku;
    private String transactionType;
    private String paymentType;
    private String paymentDetail;
    private Double amount;
    private String quantity;
    private String productTitle;


    @Override
    public String toString() {
        return "ProductEx{" +
                "date='" + date + '\'' +
                ", orderId='" + orderId + '\'' +
                ", sku='" + sku + '\'' +
                ", transactionType='" + transactionType + '\'' +
                ", paymentType='" + paymentType + '\'' +
                ", paymentDetail='" + paymentDetail + '\'' +
                ", amount='" + amount + '\'' +
                ", quantity='" + quantity + '\'' +
                ", productTitle='" + productTitle + '\'' +
                '}';
    }

    public void setOrderId(Integer orderId) {
        this.orderId = orderId;
    }

    public void setAmount(Double amount) {
        this.amount = amount;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
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
