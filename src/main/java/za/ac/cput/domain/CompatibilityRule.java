/* CompatibilityRule.java
   CompatibilityRule POJO entity implementing Builder Pattern
   Author: Matinisa Lubisi (222527269)
   Date: 21 June 2026 */

package za.ac.cput.domain;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "compatibility_rules")
public class CompatibilityRule {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long ruleId;
    private String componentType1;
    private String description;
    private String ruledescription;

    protected CompatibilityRule() {}

    protected CompatibilityRule(Builder builder) {
        this.ruleId = builder.ruleId;
        this.componentType1 = builder.componentType1;
        this.description = builder.description;
        this.ruledescription = builder.ruledescription;
    }

    public Long getRuleId() {
        return ruleId;
    }

    public String getComponentType1() { return componentType1; }
    public String getDescription() { return description; }
    public String getRuledescription() { return ruledescription; }

    @Override
    public String toString() {
        return "CompatibilityRule{" +
                "ruleId='" + ruleId + '\'' +
                ", componentType1='" + componentType1 + '\'' +
                ", description='" + description + '\'' +
                ", ruledescription='" + ruledescription + '\'' +
                '}';
    }

    public static class Builder {
        private Long ruleId;
        private String componentType1;
        private String description;
        private String ruledescription;

        public Builder setRuleId(Long ruleId) {
            this.ruleId = ruleId;
            return this;
        }

        public Builder setComponentType1(String componentType1) {
            this.componentType1 = componentType1;
            return this;
        }

        public Builder setDescription(String description) {
            this.description = description;
            return this;
        }

        public Builder setRuledescription(String ruledescription) {
            this.ruledescription = ruledescription;
            return this;
        }

        public Builder copy(CompatibilityRule compatibilityRule) {
            this.ruleId = compatibilityRule.ruleId;
            this.componentType1 = compatibilityRule.componentType1;
            this.description = compatibilityRule.description;
            this.ruledescription = compatibilityRule.ruledescription;
            return this;
        }

        public CompatibilityRule build() {
            return new CompatibilityRule(this);
        }
    }
}
