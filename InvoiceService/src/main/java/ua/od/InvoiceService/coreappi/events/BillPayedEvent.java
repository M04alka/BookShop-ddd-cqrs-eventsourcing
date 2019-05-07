package ua.od.InvoiceService.coreappi.events;

import ua.od.InvoiceService.coreappi.states.StateOfBill;
import ua.od.InvoiceService.coreappi.states.StateOfInvoice;

public class BillPayedEvent {

    private final String userInvoice;
    private final String userLogin;

    private final Double totalPrice;
    private final String stateOfInvoice;
    private final String stateOfPurchase;

    public BillPayedEvent(String userInvoice, String userLogin, Double totalPrice, String stateOfPurchase) {

        this.userInvoice = userInvoice;
        this.userLogin = userLogin;
        this.totalPrice = totalPrice;
        this.stateOfInvoice = getStateOfInvoice();
        this.stateOfPurchase = stateOfPurchase;
    }

    public String getUserLogin() {

        return userLogin;
    }

    public String getUserInvoice() {

        return userInvoice;
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
