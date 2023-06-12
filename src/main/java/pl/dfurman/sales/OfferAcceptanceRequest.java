package pl.dfurman.sales;

public class OfferAcceptanceRequest {
    String firstName;
    String lastName;
    String email;

    public String getFirstName() {
        return firstName;
    }

    public OfferAcceptanceRequest setFirstName(String firstName) {
        this.firstName = firstName;
        return this;
    }

    public String getLastName() {
        return lastName;
    }

    public OfferAcceptanceRequest setLastName(String lastName) {
        this.lastName = lastName;
        return this;
    }

    public String getEmail() {
        return email;
    }

    public OfferAcceptanceRequest setEmail(String email) {
        this.email = email;
        return this;
    }
}
