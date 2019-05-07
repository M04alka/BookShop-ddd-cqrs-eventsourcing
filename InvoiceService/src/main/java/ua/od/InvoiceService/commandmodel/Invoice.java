package ua.od.InvoiceService.commandmodel;

import org.axonframework.commandhandling.CommandHandler;
import org.axonframework.eventsourcing.EventSourcingHandler;
import org.axonframework.modelling.command.AggregateIdentifier;
import org.axonframework.modelling.command.AggregateLifecycle;
import org.axonframework.spring.stereotype.Aggregate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import ua.od.InvoiceService.commandmodel.infostructure.helpers.MoneyChecker;
import ua.od.InvoiceService.commandmodel.infostructure.helpers.TotalPriceDto;
import ua.od.InvoiceService.commandmodel.infostructure.service.InvoiceService;
import ua.od.InvoiceService.coreappi.commands.AddItemtoInvoiceCommand;
import ua.od.InvoiceService.coreappi.commands.CreateInvoiceCommand;
import ua.od.InvoiceService.coreappi.commands.PayBillCommand;
import ua.od.InvoiceService.coreappi.commands.VerifyUserMoneyCommand;
import ua.od.InvoiceService.coreappi.events.BillPayedEvent;
import ua.od.InvoiceService.coreappi.events.InvoiceCreatedEvent;
import ua.od.InvoiceService.coreappi.events.ItemAddedEvent;
import ua.od.InvoiceService.coreappi.events.UserMoneyVerifiedEvent;
import ua.od.InvoiceService.coreappi.states.StateOfInvoice;


import java.util.ArrayList;
import java.util.List;


@Aggregate
public class Invoice {

    @AggregateIdentifier
    private String userInvoice;

    private List<Long> bookList;
    private Double totalPrice;
    private String stateOfInvoice;
    private String stateOfPurchase;

    public Invoice() {

    }

    //Command for create Invoice

    @CommandHandler
    public Invoice(CreateInvoiceCommand command, InvoiceService invoiceService) {

        if(invoiceService.getInvoice(command.getUserInvoice())!=null){
            throw new IllegalStateException("This user already has invoice!");
        }
        AggregateLifecycle.apply(
                new InvoiceCreatedEvent(
                        command.getUserInvoice(),
                        command.getUserLogin(),
                        command.getBookList(),
                        command.getTotalPrice(),
                        command.getStateOfInvoice(),
                        command.getStateOfPurchase()
                )
        );
    }

    //Event for create Invoice

    @EventSourcingHandler
    public void on(InvoiceCreatedEvent event){
        this.userInvoice = event.getUserInvoice();
        this.bookList = event.getBookList();
        this.totalPrice = event.getTotalPrice();
        this.stateOfInvoice = event.getStateOfInvoice();
        this.stateOfPurchase = event.getStateOfPurchase();
    }


    //Coomand to add item to the Bill

    @CommandHandler
    private void handle(AddItemtoInvoiceCommand command){

        AggregateLifecycle.apply(
                new ItemAddedEvent(
                        command.getUserInvoice(),
                        command.getUserLogin(),
                        command.getBook(),
                        command.getPrice(),
                        command.getStateOfInvoice(),
                        command.getStateOfPurchase()
                )
        );
    }

    //Event to add item to the Bill

    @EventSourcingHandler
    private void on(ItemAddedEvent event){

        this.userInvoice = event.getUserInvoice();
        this.bookList.add(event.getBook());
        this.totalPrice = this.totalPrice + event.getPrice();
        this.stateOfInvoice = event.getStateOfInvoice();
        this.stateOfPurchase = event.getStateOfPurchase();

    }

    //Coomand to verify ammount of money

    @CommandHandler
    public void handle(VerifyUserMoneyCommand command, InvoiceService invoiceService){

        if(this.totalPrice.equals(0d)){
            throw new IllegalStateException("Your invoice is empty!");
        }

        invoiceService.checkMoney(
                new TotalPriceDto(
                        command.getTotalPrice(),
                        command.getUserLogin()
                )
        );
    }

    //Event to verify ammount of money

    @EventSourcingHandler
    public void on(UserMoneyVerifiedEvent event)
    {

        this.userInvoice = event.getUserInvoice();
        this.totalPrice = event.getTotalPrice();
        this.stateOfInvoice = event.getStateOfInvoice();
        this.stateOfPurchase = event.getStateOfPurchase();
    }

    //Coomand to Pay the Bill

    @CommandHandler
    public void handle(PayBillCommand command){

        if(command.getVerification().equals(MoneyChecker.Decline.toString())) {
            throw new IllegalStateException("You don't have enought money!");
        }
        System.out.println(this.stateOfPurchase);
        AggregateLifecycle.apply(
                new BillPayedEvent(
                        command.getUserInvoice(),
                        command.getUserLogin(),
                        command.getTotalPrice(),
                        command.getStateOfPurchase()
                )
        );
    }

    //Event to Pay the Bill

    @EventSourcingHandler
    public void on(BillPayedEvent event){

        this.userInvoice = event.getUserInvoice();
        this.bookList = new ArrayList<>();
        this.totalPrice = 0d;
        this.stateOfInvoice = event.getStateOfInvoice();
        this.stateOfPurchase = event.getStateOfPurchase();
    }
}

