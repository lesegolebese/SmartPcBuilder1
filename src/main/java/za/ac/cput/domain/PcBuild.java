/*
 * PcBuild.java
 * PcBuild POJO Entity implementing Builder Pattern
 * Author: Mnelisi Mabona (222062088)
 * Date: 27 June 2026
 */

package za.ac.cput.domain;



import jakarta.persistence.*;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "pc_builds")
public class PcBuild {

    @Id
    private String buildId;

    private String buildName;
    private double totalPrice;
    private String buildDescription;


    @OneToMany(mappedBy = "pcBuild", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.EAGER)
    private List<PcBuildItem> pcBuildItems = new ArrayList<>();


    protected PcBuild() {
    }


    private PcBuild(Builder builder) {
        this.buildId = builder.buildId;
        this.buildName = builder.buildName;
        this.totalPrice = builder.totalPrice;
        this.buildDescription = builder.buildDescription;
        this.pcBuildItems = builder.pcBuildItems;
    }


    public String getBuildId() { return buildId; }
    public String getBuildName() { return buildName; }
    public double getTotalPrice() { return totalPrice; }
    public String getBuildDescription() { return buildDescription; }
    public List<PcBuildItem> getPcBuildItems() { return pcBuildItems; }


    @Override
    public String toString() {
        return "PcBuild{" +
                "buildId='" + buildId + '\'' +
                ", buildName='" + buildName + '\'' +
                ", totalPrice=" + totalPrice +
                ", buildDescription='" + buildDescription + '\'' +
                '}';
    }


    public static class Builder {
        private String buildId;
        private String buildName;
        private double totalPrice;
        private String buildDescription;
        private List<PcBuildItem> pcBuildItems = new ArrayList<>();

        public Builder setBuildId(String buildId) {
            this.buildId = buildId;
            return this;
        }

        public Builder setBuildName(String buildName) {
            this.buildName = buildName;
            return this;
        }

        public Builder setTotalPrice(double totalPrice) {
            this.totalPrice = totalPrice;
            return this;
        }

        public Builder setBuildDescription(String buildDescription) {
            this.buildDescription = buildDescription;
            return this;
        }

        public Builder setPcBuildItems(List<PcBuildItem> pcBuildItems) {
            this.pcBuildItems = pcBuildItems;
            return this;
        }

        public Builder copy(PcBuild pcBuild) {
            this.buildId = pcBuild.buildId;
            this.buildName = pcBuild.buildName;
            this.totalPrice = pcBuild.totalPrice;
            this.buildDescription = pcBuild.buildDescription;
            this.pcBuildItems = pcBuild.pcBuildItems;
            return this;
        }

        public PcBuild build() {
            return new PcBuild(this);
        }

        public Builder setCreatedDate(LocalDate createdDate) {
            return null;
        }
    }
}