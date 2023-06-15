package pl.dfurman.sales.reservation;

public class AcceptOffer {
    String email;
    String fname;

    public String getEmail() {
        return email;
    }

    public String getFname() {        return fname;
    }

    public AcceptOffer(String email, String fname) {
        this.email = email;
        this.fname=fname;
    }
}
