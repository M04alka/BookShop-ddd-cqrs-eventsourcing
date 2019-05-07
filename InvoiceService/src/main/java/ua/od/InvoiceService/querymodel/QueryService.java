package ua.od.InvoiceService.querymodel;


import org.springframework.beans.factory.annotation.Autowired;
import ua.od.InvoiceService.commandmodel.infostructure.repository.InvoiceEntity;
import ua.od.InvoiceService.commandmodel.infostructure.repository.InvoiceRepositoryInterface;

public class QueryService {

    @Autowired
    InvoiceRepositoryInterface invoiceRepositoryInterface;

    public BillDto getBill(String login){
        InvoiceEntity invoice = invoiceRepositoryInterface.findByUserLogin(login);
        BillDto balanceDto = new BillDto(invoice.getUserLogin(),invoice.getPrice());
        return  balanceDto;
    }
}
