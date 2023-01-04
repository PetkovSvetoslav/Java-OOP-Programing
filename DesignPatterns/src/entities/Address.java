package entities;

import core.Clone;

public class Address implements Clone<Address> {
    private String firstName;
    private String lastName;
    private String email;
    private String phoneNumber;
    private String city;

    public static class Builder {

        private String firstName;
        private String lastName;
        private String email;
        private String phoneNumber;
        private String city;

        private Builder(String firstName, String lastName) {
            this.firstName = firstName;
            this.lastName = lastName;
        }

        public static Builder newBuilder(String firstName, String lastName) {
            return new Builder(firstName, lastName);
        }

        public Builder withEmail(String email) {
            this.email = email;
            return this;
        }

        public Builder withPhoneNumber(String phoneNumber) {
            this.phoneNumber = phoneNumber;
            return this;
        }

        public Builder withCity(String city) {
            this.city = city;
            return this;
        }

        public Address build() {
            Address address = new Address();
            address.firstName = this.firstName;
            address.lastName = this.lastName;
            address.email = this.email;
            address.phoneNumber = this.phoneNumber;
            address.city = this.city;

            return address;
        }
    }

    @Override
    public Address clone() {
        return Builder.newBuilder(this.firstName, this.lastName)
                .withEmail(this.email)
                .withPhoneNumber(this.phoneNumber)
                .withCity(this.city)
                .build();
    }

    @Override
    public String toString() {
        return "First name: " + this.firstName + System.lineSeparator() +
                "LastName: " + this.lastName + System.lineSeparator() +
                "Email: " + this.email + System.lineSeparator() +
                "PhoneNumber: " + this.phoneNumber + System.lineSeparator() +
                "City: " + this.city;
    }
}
