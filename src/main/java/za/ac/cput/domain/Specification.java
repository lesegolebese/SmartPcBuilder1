package za.ac.cput.domain;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

/* Specification.java
   Specification POJO class

   Author: Lerato (222381582)

   Date: 21 June 2026
*/

@Entity
@Table(name = "specifications")
public class Specification {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long specificationId;

    @Column(name = "spec_key")
    private String specKey;
    @Column(name = "spec_value")
    private String specValue;

    protected Specification() {}

    protected Specification(Builder builder) {
        this.specificationId = builder.specificationId;
        this.specKey = builder.specKey;
        this.specValue = builder.specValue;
    }

    public Long getSpecificationId() {
        return specificationId;
    }

    public String getSpecKey() {
        return specKey;
    }

    public String getSpecValue() {
        return specValue;
    }

    @Override
    public String toString() {
        return "Specification{" +
                "specificationId=" + specificationId +
                ", specKey='" + specKey + '\'' +
                ", specValue='" + specValue + '\'' +
                '}';
    }

    public static class Builder {
        private Long specificationId;
        private String specKey;
        private String specValue;

        public Builder setSpecificationId(Long specificationId) {
            this.specificationId = specificationId;
            return this;
        }

        public Builder setSpecKey(String specKey) {
            this.specKey = specKey;
            return this;
        }

        public Builder setSpecValue(String specValue) {
            this.specValue = specValue;
            return this;
        }

        public Builder copy(Specification specification) {
            this.specificationId = specification.specificationId;
            this.specKey = specification.specKey;
            this.specValue = specification.specValue;
            return this;
        }

        public Specification build() {
            return new Specification(this);
        }
    }
}