package za.ac.cput.domain;
/* Product.java
   Product POJO class

   Author: Lerato (222381582)

   Date: 21 June 2026
*/

//
public class Product {

    private Long productId;
    private String productName;
    private String description;
    private Double price;
    private int stockQuantity;

    protected Product() {}

    protected Product(Builder builder) {
        this.productId = builder.productId;
        this.productName = builder.productName;
        this.description = builder.description;
        this.price = builder.price;
        this.stockQuantity = builder.stockQuantity;
    }

    public Long getProductId() {
        return productId;
    }

    public String getProductName() {
        return productName;
    }

    public String getDescription() {
        return description;
    }

    public Double getPrice() {
        return price;
    }

    public int getStockQuantity() {
        return stockQuantity;
    }

    @Override
    public String toString() {
        return "Product{" +
                "productId=" + productId +
                ", productName='" + productName + '\'' +
                ", description='" + description + '\'' +
                ", price=" + price +
                ", stockQuantity=" + stockQuantity +
                '}';
    }

    public static class Builder {
        private Long productId;
        private String productName;
        private String description;
        private Double price;
        private int stockQuantity;

        public Builder setProductId(Long productId) {
            this.productId = productId;
            return this;
        }

        public Builder setProductName(String productName) {
            this.productName = productName;
            return this;
        }

        public Builder setDescription(String description) {
            this.description = description;
            return this;
        }

        public Builder setPrice(Double price) {
            this.price = price;
            return this;
        }

        public Builder setStockQuantity(int stockQuantity) {
            this.stockQuantity = stockQuantity;
            return this;
        }

        public Builder copy(Product product) {
            this.productId = product.productId;
            this.productName = product.productName;
            this.description = product.description;
            this.price = product.price;
            this.stockQuantity = product.stockQuantity;
            return this;
        }

        public Product build() {
            return new Product(this);
        }
    }
}

