/* Address.java
   Address POJO entity implementing Builder Pattern
   Author: Lesego Lebese (222371196)
   Date: 21 June 2026 */

package za.ac.cput.domain;

public class Address {
    private Long addressId;
    private String streetName;
    private String suburb;
    private String city;
    private String province;
    private String postalCode;
    private String country;

    protected Address() {}

    protected Address(Builder builder) {
        this.addressId = builder.addressId;
        this.streetName = builder.streetName;
        this.suburb = builder.suburb;
        this.city = builder.city;
        this.province = builder.province;
        this.postalCode = builder.postalCode;
        this.country = builder.country;
    }

    public Long getAddressId() { return addressId; }
    public String getStreetName() { return streetName; }
    public String getSuburb() { return suburb; }
    public String getCity() { return city; }
    public String getProvince() { return province; }
    public String getPostalCode() { return postalCode; }
    public String getCountry() { return country; }

    @Override
    public String toString() {
        return "Address{" +
                "addressId=" + addressId +
                ", streetName='" + streetName + '\'' +
                ", suburb='" + suburb + '\'' +
                ", city='" + city + '\'' +
                ", province='" + province + '\'' +
                ", postalCode='" + postalCode + '\'' +
                ", country='" + country + '\'' +
                '}';
    }

    public static class Builder {
        private Long addressId;
        private String streetName;
        private String suburb;
        private String city;
        private String province;
        private String postalCode;
        private String country;

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

        public Builder setPostalCode(String postalCode) {
            this.postalCode = postalCode;
            return this;
        }

        public Builder setCountry(String country) {
            this.country = country;
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
            return this;
        }

        public Address build() {
            return new Address(this);
        }
    }
}