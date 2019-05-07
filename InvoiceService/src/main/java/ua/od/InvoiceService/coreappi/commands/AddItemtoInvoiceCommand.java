package ua.od.InvoiceService.coreappi.commands;

import org.axonframework.modelling.command.TargetAggregateIdentifier;
import ua.od.InvoiceService.coreappi.states.StateOfBill;
import ua.od.InvoiceService.coreappi.states.StateOfInvoice;


public class AddItemtoInvoiceCommand {

    @TargetAggregateIdentifier
    private String userInvoice;

    private final String userLogin;
    private final Long book;
    private final Double price;
    private final String stateOfInvoice;
    private final String stateOfPurchase;

    public AddItemtoInvoiceCommand(String userInvoice, String userLogin, Long book, Double price) {

        this.userInvoice = userInvoice;
        this.userLogin = userLogin;
        this.book = book;
        this.price = price;
        this.stateOfInvoice = StateOfInvoice.Сreated.toString();
        this.stateOfPurchase = StateOfBill.Unpaid.toString();
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
