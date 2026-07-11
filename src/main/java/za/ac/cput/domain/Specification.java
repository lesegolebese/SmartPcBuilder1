package za.ac.cput.domain;

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

    private String key;
    private String value;

    protected Specification() {}

    protected Specification(Builder builder) {
        this.specificationId = builder.specificationId;
        this.key = builder.key;
        this.value = builder.value;
    }

    public Long getSpecificationId() {
        return specificationId;
    }

    public String getKey() {
        return key;
    }

    public String getValue() {
        return value;
    }

    @Override
    public String toString() {
        return "Specification{" +
                "specificationId=" + specificationId +
                ", key='" + key + '\'' +
                ", value='" + value + '\'' +
                '}';
    }

    public static class Builder {
        private Long specificationId;
        private String key;
        private String value;

        public Builder setSpecificationId(Long specificationId) {
            this.specificationId = specificationId;
            return this;
        }

        public Builder setKey(String key) {
            this.key = key;
            return this;
        }

        public Builder setValue(String value) {
            this.value = value;
            return this;
        }

        public Builder copy(Specification specification) {
            this.specificationId = specification.specificationId;
            this.key = specification.key;
            this.value = specification.value;
            return this;
        }

        public Specification build() {
            return new Specification(this);
        }
    }
}