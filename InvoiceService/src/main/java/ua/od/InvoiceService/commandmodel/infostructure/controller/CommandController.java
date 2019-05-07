package ua.od.InvoiceService.commandmodel.infostructure.controller;

import org.axonframework.commandhandling.gateway.CommandGateway;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import ua.od.InvoiceService.commandmodel.infostructure.helpers.ItemDto;
import ua.od.InvoiceService.commandmodel.infostructure.repository.InvoiceEntity;
import ua.od.InvoiceService.commandmodel.infostructure.service.InvoiceService;
import ua.od.InvoiceService.coreappi.commands.AddItemtoInvoiceCommand;
import ua.od.InvoiceService.coreappi.commands.PayBillCommand;
import ua.od.InvoiceService.coreappi.commands.VerifyUserMoneyCommand;

@RestController
@RequestMapping("invoice")
public class CommandController {

    @Autowired
    InvoiceService invoiceService;

    @Autowired
    private final CommandGateway commandGateway;

    public CommandController(CommandGateway commandGateway) {
        this.commandGateway = commandGateway;
    }

    //Controler for adding some book using ut id to invoice

    @PostMapping("/{login}/addbook")
    public void addBookToInvoice(@PathVariable String login, @RequestBody ItemDto itemDto){

        String userInvoice = login + "Invoice";
        commandGateway.send(new AddItemtoInvoiceCommand(userInvoice, login,itemDto.getBookId(), itemDto.getBookPrice()));
    }

    //Controler for pay bill

    @PostMapping("/{login}/paybill")
    public void payBill(@PathVariable  String login){

        InvoiceEntity entity = invoiceService.getInvoice(login);
        String userInvoice = login + "Invoice";
        commandGateway.send(new VerifyUserMoneyCommand(userInvoice,login,entity.getPrice()));
    }
}
