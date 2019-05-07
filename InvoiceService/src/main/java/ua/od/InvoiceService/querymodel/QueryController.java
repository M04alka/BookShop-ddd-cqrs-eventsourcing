package ua.od.InvoiceService.querymodel;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("invoice")
public class QueryController {


    @GetMapping("/{login}/bill")
    public BillDto getBalance(@PathVariable String login){
        QueryService queryService = new QueryService();
        return queryService.getBill(login);
    }
}
