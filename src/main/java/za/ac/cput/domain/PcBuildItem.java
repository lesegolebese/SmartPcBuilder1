/*
 * PcBuildItem.java
 * PcBuildItem POJO Entity implementing Builder Pattern
 * Author: Mnelisi Mabona (222062088)
 * Date: 27 June 2026
 */

package za.ac.cput.domain;

public class PcBuildItem {

    private Long buildItemId;
    private int quantity;
    private Product product;

    protected PcBuildItem() {
    }

    protected PcBuildItem(Builder builder) {
        this.buildItemId = builder.buildItemId;
        this.quantity = builder.quantity;
        this.product = builder.product;
    }

    public Long getBuildItemId() {
        return buildItemId;
    }

    public int getQuantity() {
        return quantity;
    }

    public Product getProduct() {
        return product;
    }

    @Override
    public String toString() {
        return "PcBuildItem{" +
                "buildItemId=" + buildItemId +
                ", quantity=" + quantity +
                ", product=" + product +
                '}';
    }

    public static class Builder {

        private Long buildItemId;
        private int quantity;
        private Product product;

        public Builder setBuildItemId(Long buildItemId) {
            this.buildItemId = buildItemId;
            return this;
        }

        public Builder setQuantity(int quantity) {
            this.quantity = quantity;
            return this;
        }

        public Builder setProduct(Product product) {
            this.product = product;
            return this;
        }

        public Builder copy(PcBuildItem item) {
            this.buildItemId = item.buildItemId;
            this.quantity = item.quantity;
            this.product = item.product;
            return this;
        }

        public PcBuildItem build() {
            return new PcBuildItem(this);
        }
    }
}