/* Address.java
   Address POJO entity implementing Builder Pattern
   Author: Lesego Lebese (222371196)
   Date: 22 June 2026 *///

package za.ac.cput.domain;

import jakarta.persistence.*;

@Entity
@Table(name = "Addresses")
public class Address {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long addressId;

    private String streetName;
    private String suburb;
    private String city;
    private String province;
    private short postalCode; // Aligned with Short.parseShort() in factory
    private String country;   // Included from factory signature

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User user;

    protected Address() {
    }

    private Address(Builder builder) {
        this.addressId = builder.addressId;
        this.streetName = builder.streetName;
        this.suburb = builder.suburb;
        this.city = builder.city;
        this.province = builder.province;
        this.postalCode = builder.postalCode;
        this.country = builder.country;
        this.user = builder.user;
    }

    // GETTERS
    public Long getAddressId() { return addressId; }
    public String getStreetName() { return streetName; }
    public String getSuburb() { return suburb; }
    public String getCity() { return city; }
    public String getProvince() { return province; }
    public short getPostalCode() { return postalCode; }
    public String getCountry() { return country; }
    public User getUser() { return user; }

    @Override
    public String toString() {
        return "Address{" +
                "addressId=" + addressId +
                ", streetName='" + streetName + '\'' +
                ", suburb='" + suburb + '\'' +
                ", city='" + city + '\'' +
                ", province='" + province + '\'' +
                ", postalCode=" + postalCode +
                ", country='" + country + '\'' +
                '}';
    }

    public static class Builder {
        private Long addressId;
        private String streetName;
        private String suburb;
        private String city;
        private String province;
        private short postalCode;
        private String country;
        private User user;

        public Builder setAddressId(Long addressId) {
            this.addressId = addressId;
            return this;
        }

        public Builder setStreetName(String streetName) {
            this.streetName = streetName;
            return this;
        }

        public Builder setSuburb(String suburb) {
            this.suburb = suburb;
            return this;
        }

        public Builder setCity(String city) {
            this.city = city;
            return this;
        }

        public Builder setProvince(String province) {
            this.province = province;
            return this;
        }

        public Builder setPostalCode(short postalCode) {
            this.postalCode = postalCode;
            return this;
        }

        public Builder setCountry(String country) {
            this.country = country;
            return this;
        }

        public Builder setUser(User user) {
            this.user = user;
            return this;
        }

        public Builder copy(Address address) {
            this.addressId = address.addressId;
            this.streetName = address.streetName;
            this.suburb = address.suburb;
            this.city = address.city;
            this.province = address.province;
            this.postalCode = address.postalCode;
            this.country = address.country;
            this.user = address.user;
            return this;
        }

        public Address build() {
            return new Address(this);
        }
    }
}