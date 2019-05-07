package ua.od.InvoiceService.coreappi.events;


public class ItemAddedEvent {

    private final String userInvoice;
    private final String userLogin;
    private final Long book;
    private final Double price;
    private final String stateOfInvoice;
    private final String stateOfPurchase;

    public ItemAddedEvent(String userInvoice,String userLogin, Long book, Double price,String stateOfInvoice,String stateOfPurchase) {

        this.userInvoice = userInvoice;
        this.userLogin = userLogin;
        this.book = book;
        this.price = price;
        this.stateOfInvoice = stateOfInvoice;
        this.stateOfPurchase = stateOfPurchase;
    }

    public String getUserInvoice() {

        return userInvoice;
    }

    public String getUserLogin() {

        return userLogin;
    }

    public Long getBook() {

        return book;
    }

    public Double getPrice() {

        return price;
    }

    public String getStateOfInvoice() {

        return stateOfInvoice;
    }

    public String getStateOfPurchase() {

        return stateOfPurchase;
    }
}
