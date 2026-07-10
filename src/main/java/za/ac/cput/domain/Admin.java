/* Admin.java
   Admin POJO entity implementing Builder Pattern
   Author: Matinisa Lubisi (222527269)
   Date: 21 June 2026 */

package za.ac.cput.domain;

public class Admin {
    private String adminId;
    private String firstName;
    private String middleName;
    private String lastName;
    private String email;
    private String password;

    protected Admin() {}

    protected Admin(Builder builder) {
        this.adminId = builder.adminId;
        this.firstName = builder.firstName;
        this.middleName = builder.middleName;
        this.lastName = builder.lastName;
        this.email = builder.email;
        this.password = builder.password;
    }

    public String getAdminId() { return adminId; }
    public String getFirstName() { return firstName; }
    public String getMiddleName() { return middleName; }
    public String getLastName() { return lastName; }
    public String getEmail() { return email; }
    public String getPassword() { return password; }

    @Override
    public String toString() {
        return "Admin{" +
                "adminId='" + adminId + '\'' +
                ", firstName='" + firstName + '\'' +
                ", middleName='" + middleName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", email='" + email + '\'' +
                ", password='" + password + '\'' +
                '}';
    }

    public static class Builder {
        private String adminId;
        private String firstName;
        private String middleName;
        private String lastName;
        private String email;
        private String password;

        public Builder setAdminId(String adminId) {
            this.adminId = adminId;
            return this;
        }

        public Builder setFirstName(String firstName) {
            this.firstName = firstName;
            return this;
        }

        public Builder setMiddleName(String middleName) {
            this.middleName = middleName;
            return this;
        }

        public Builder setLastName(String lastName) {
            this.lastName = lastName;
            return this;
        }

        public Builder setEmail(String email) {
            this.email = email;
            return this;
        }

        public Builder setPassword(String password) {
            this.password = password;
            return this;
        }

        public Builder copy(Admin admin) {
            this.adminId = admin.adminId;
            this.firstName = admin.firstName;
            this.middleName = admin.middleName;
            this.lastName = admin.lastName;
            this.email = admin.email;
            this.password = admin.password;
            return this;
        }

        public Admin build() {
            return new Admin(this);
        }
    }
}
