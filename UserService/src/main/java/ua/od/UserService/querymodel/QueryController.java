package ua.od.UserService.querymodel;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("user")
public class QueryController {

    @GetMapping("/{login}/balance")
    public BalanceDto getBalance(@PathVariable String login){

        QueryService queryService = new QueryService();
        return queryService.getBalance(login);
    }

}
