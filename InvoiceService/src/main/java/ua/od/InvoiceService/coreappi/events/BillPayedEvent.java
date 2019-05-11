package ua.od.InvoiceService.coreappi.events;

import java.util.List;

public class BillPayedEvent {

    private final String userInvoice;
    private final String userLogin;

    private final List<Long> bookList;
    private final Double totalPrice;
    private final String stateOfInvoice;
    private final String stateOfPurchase;

    public BillPayedEvent(String userInvoice, String userLogin, List<Long> bookList, Double totalPrice, String stateOfInvoice, String stateOfPurchase) {
        this.userInvoice = userInvoice;
        this.userLogin = userLogin;
        this.bookList = bookList;
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

    public List<Long> getBookList() {

        return bookList;
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
