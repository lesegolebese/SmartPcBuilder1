/*
 * PcBuildItem.java
 * PcBuildItem POJO Entity implementing Builder Pattern
 * Author: Mnelisi Mabona (222062088)
 * Date: 27 June 2026
 */

package za.ac.cput.domain;

import jakarta.persistence.*;

@Entity
@Table(name = "pc_build_items")
public class PcBuildItem {

    @Id
    private String buildItemId;

    private String componentName;
    private int quantity;
    private double unitPrice;


    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "build_id")
    private PcBuild pcBuild;


    protected PcBuildItem() {
    }


    private PcBuildItem(Builder builder) {
        this.buildItemId = builder.buildItemId;
        this.componentName = builder.componentName;
        this.quantity = builder.quantity;
        this.unitPrice = builder.unitPrice;
        this.pcBuild = builder.pcBuild;
    }


    public String getBuildItemId() { return buildItemId; }
    public String getComponentName() { return componentName; }
    public int getQuantity() { return quantity; }
    public double getUnitPrice() { return unitPrice; }
    public PcBuild getPcBuild() { return pcBuild; }


    @Override
    public String toString() {
        return "PcBuildItem{" +
                "buildItemId='" + buildItemId + '\'' +
                ", componentName='" + componentName + '\'' +
                ", quantity=" + quantity +
                ", unitPrice=" + unitPrice +
                '}';
    }


    public static class Builder {
        private String buildItemId;
        private String componentName;
        private int quantity;
        private double unitPrice;
        private PcBuild pcBuild;

        public Builder setBuildItemId(String buildItemId) {
            this.buildItemId = buildItemId;
            return this;
        }

        public Builder setComponentName(String componentName) {
            this.componentName = componentName;
            return this;
        }

        public Builder setQuantity(int quantity) {
            this.quantity = quantity;
            return this;
        }

        public Builder setUnitPrice(double unitPrice) {
            this.unitPrice = unitPrice;
            return this;
        }

        public Builder setPcBuild(PcBuild pcBuild) {
            this.pcBuild = pcBuild;
            return this;
        }

        public Builder copy(PcBuildItem pcBuildItem) {
            this.buildItemId = pcBuildItem.buildItemId;
            this.componentName = pcBuildItem.componentName;
            this.quantity = pcBuildItem.quantity;
            this.unitPrice = pcBuildItem.unitPrice;
            this.pcBuild = pcBuildItem.pcBuild;
            return this;
        }

        public PcBuildItem build() {
            return new PcBuildItem(this);
        }
    }
}