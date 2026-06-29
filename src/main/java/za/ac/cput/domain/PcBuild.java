/*
 * PcBuild.java
 * PcBuild POJO Entity implementing Builder Pattern
 * Author: Mnelisi Mabona (222062088)
 * Date: 27 June 2026
 */

package za.ac.cput.domain;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class PcBuild {

    private Long buildId;
    private String buildName;
    private LocalDate createdDate;
    private User user;
    private List<PcBuildItem> buildItems;

    protected PcBuild() {
    }

    protected PcBuild(Builder builder) {
        this.buildId = builder.buildId;
        this.buildName = builder.buildName;
        this.createdDate = builder.createdDate;
        this.user = builder.user;
        this.buildItems = builder.buildItems != null
                ? builder.buildItems
                : new ArrayList<>();
    }

    public Long getBuildId() {
        return buildId;
    }

    public String getBuildName() {
        return buildName;
    }

    public LocalDate getCreatedDate() {
        return createdDate;
    }

    public User getUser() {
        return user;
    }

    public List<PcBuildItem> getBuildItems() {
        return buildItems;
    }

    @Override
    public String toString() {
        return "PcBuild{" +
                "buildId=" + buildId +
                ", buildName='" + buildName + '\'' +
                ", createdDate=" + createdDate +
                ", user=" + user +
                ", buildItems=" + buildItems +
                '}';
    }

    public static class Builder {

        private Long buildId;
        private String buildName;
        private LocalDate createdDate;
        private User user;
        private List<PcBuildItem> buildItems;

        public Builder setBuildId(Long buildId) {
            this.buildId = buildId;
            return this;
        }

        public Builder setBuildName(String buildName) {
            this.buildName = buildName;
            return this;
        }

        public Builder setCreatedDate(LocalDate createdDate) {
            this.createdDate = createdDate;
            return this;
        }

        public Builder setUser(User user) {
            this.user = user;
            return this;
        }

        public Builder setBuildItems(List<PcBuildItem> buildItems) {
            this.buildItems = buildItems;
            return this;
        }

        public Builder copy(PcBuild pcBuild) {
            this.buildId = pcBuild.buildId;
            this.buildName = pcBuild.buildName;
            this.createdDate = pcBuild.createdDate;
            this.user = pcBuild.user;
            this.buildItems = pcBuild.buildItems;
            return this;
        }

        public PcBuild build() {
            return new PcBuild(this);
        }
    }
}