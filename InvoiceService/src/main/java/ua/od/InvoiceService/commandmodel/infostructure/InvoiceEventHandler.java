package ua.od.InvoiceService.commandmodel.infostructure;

import org.axonframework.commandhandling.gateway.CommandGateway;
import org.axonframework.eventhandling.EventHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;
import ua.od.InvoiceService.commandmodel.infostructure.helpers.UserWalletVerificationDto;
import ua.od.InvoiceService.commandmodel.infostructure.repository.InvoiceEntity;
import ua.od.InvoiceService.commandmodel.infostructure.service.InvoiceService;
import ua.od.InvoiceService.coreappi.commands.CreateInvoiceCommand;
import ua.od.InvoiceService.coreappi.commands.PayBillCommand;
import ua.od.InvoiceService.coreappi.events.BillPayedEvent;
import ua.od.InvoiceService.coreappi.events.InvoiceCreatedEvent;
import ua.od.InvoiceService.coreappi.events.ItemAddedEvent;
import ua.od.InvoiceService.coreappi.events.UserLogedInEvent;

@Component
public class InvoiceEventHandler {

   @Autowired
   InvoiceService invoiceService;

    @Autowired
    private final CommandGateway commandGateway;

    public InvoiceEventHandler(CommandGateway commandGateway) {

        this.commandGateway = commandGateway;
    }

    //Listener that create an invoice for user when he login

    @KafkaListener(topics = "UserLogedIn", groupId = "Invoice", containerFactory = "kafkaListenerContainerFactory")
    public void consume(UserLogedInEvent event){

        String userInvoice = event.getLogin() + "Invoice";
        commandGateway.send(
                new CreateInvoiceCommand(
                        userInvoice,
                        event.getLogin()
                )
        );
    }

    //Listener that verify ammount of money that user has

    @KafkaListener(topics = "VerifyMoneyAmmount", groupId = "BillCheck", containerFactory = "kafkaListenerContainerUserWalletVerificationDtoFactory")
    public void consume(UserWalletVerificationDto userWalletVerificationDto){

        String userInvoice = userWalletVerificationDto.getUseLogin() + "Invoice";
        commandGateway.send(
                new PayBillCommand(
                        userInvoice,
                        userWalletVerificationDto.getUseLogin(),
                        userWalletVerificationDto.getTotalPrice(),
                        userWalletVerificationDto.getStatus()
                )
        );
    }

    //Save some invoice data to db when invoice created

    @EventHandler
    public void on(InvoiceCreatedEvent event){

        invoiceService.saveInvoice(
                new InvoiceEntity(
                        event.getUserLogin(),
                        event.getTotalPrice()
                )
        );
    }

    //Update Invoice price when item added

    @EventHandler
    public void on(ItemAddedEvent event){

        InvoiceEntity entity = invoiceService.getInvoice(event.getUserLogin());
        entity.setPrice(entity.getPrice() + event.getPrice());
        invoiceService.saveInvoice(entity);
    }

    //Withdraw money for purchase

    @EventHandler
    public void on(BillPayedEvent event){

        InvoiceEntity entity = invoiceService.getInvoice(event.getUserLogin());
        entity.setPrice(0d);
        invoiceService.saveInvoice(entity);
    }



}
