package ua.od.InvoiceService.coreappi.events;

public class UserMoneyVerifiedEvent {

    private final String userInvoice;
    private final String userLogin;
    private final Double totalPrice;
    private final String stateOfInvoice;
    private final String stateOfPurchase;

    public UserMoneyVerifiedEvent(String userInvoice, String userLogin, Double totalPrice, String stateOfInvoice, String stateOfPurchase) {

        this.userInvoice = userInvoice;
        this.userLogin = userLogin;
        this.totalPrice = totalPrice;
        this.stateOfInvoice = stateOfInvoice;
        this.stateOfPurchase = stateOfPurchase;
    }

    public String getUserInvoice() {

        return userInvoice;
    }

    public String getUserLogin() {

        return userLogin;
    }

    public Double getTotalPrice() {

        return totalPrice;
    }

    public String getStateOfInvoice() {

        return stateOfInvoice;
    }

    public String getStateOfPurchase() {

        return stateOfPurchase;
    }
}
