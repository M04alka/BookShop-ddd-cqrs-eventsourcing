package ua.od.InvoiceService.coreappi.commands;


import org.axonframework.modelling.command.TargetAggregateIdentifier;
import ua.od.InvoiceService.commandmodel.infostructure.helpers.MoneyChecker;
import ua.od.InvoiceService.coreappi.states.StateOfBill;
import ua.od.InvoiceService.coreappi.states.StateOfInvoice;

public class PayBillCommand {

    @TargetAggregateIdentifier
    private final String userInvoice;
    private final String userLogin;


    private final Double totalPrice;
    private final String stateOfInvoice;
    private final String stateOfPurchase;
    private final String verification;

    public PayBillCommand(String userInvoice, String userLogin,Double totalPrice,String verification) {

        this.userInvoice = userInvoice;
        this.userLogin = userLogin;
        this.totalPrice =  totalPrice;
        this.stateOfInvoice = StateOfInvoice.Сreated.toString();
        this.stateOfPurchase = StateOfBill.New.toString();
        this.verification = verification;
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

    public String getVerification() {

        return verification;
    }
}
