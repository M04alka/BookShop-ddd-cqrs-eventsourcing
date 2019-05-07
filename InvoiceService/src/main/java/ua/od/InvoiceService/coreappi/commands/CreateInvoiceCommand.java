package ua.od.InvoiceService.coreappi.commands;

import org.axonframework.modelling.command.TargetAggregateIdentifier;
import ua.od.InvoiceService.coreappi.states.StateOfBill;
import ua.od.InvoiceService.coreappi.states.StateOfInvoice;

import java.util.ArrayList;
import java.util.List;

public class CreateInvoiceCommand {

    @TargetAggregateIdentifier
    private String userInvoice;

    private final String userLogin;
    private final List<Long> bookList;
    private final Double totalPrice;
    private final String stateOfInvoice;
    private final String stateOfPurchase;

    public CreateInvoiceCommand(String userInvoice,String userLogin) {

        this.userInvoice = userInvoice;
        this.userLogin = userLogin;
        this.bookList = new ArrayList<Long>();
        this.totalPrice = 0d;
        this.stateOfInvoice = StateOfInvoice.Сreated.toString();
        this.stateOfPurchase = StateOfBill.New.toString();
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
