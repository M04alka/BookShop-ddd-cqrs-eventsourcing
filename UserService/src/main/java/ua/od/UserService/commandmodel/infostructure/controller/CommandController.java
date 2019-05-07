package ua.od.UserService.commandmodel.infostructure.controller;

import org.axonframework.commandhandling.callbacks.LoggingCallback;
import org.axonframework.commandhandling.gateway.CommandGateway;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import ua.od.UserService.commandmodel.infostructure.helpers.CredentialsDto;
import ua.od.UserService.coreappi.commands.*;


@RestController
@RequestMapping("user")
public class CommandController {

    @Autowired
    private final CommandGateway commandGateway;

    public CommandController(CommandGateway commandGateway) {

        this.commandGateway = commandGateway;
    }

    //Create new user

    @PostMapping("/new")
      public void newUser(@RequestBody CredentialsDto credentials){

        commandGateway.send(
                new CreateUserCommand(
                        credentials.getLogin(),
                        credentials.getPassword()
                ),
                LoggingCallback.INSTANCE
        );
    }

    //LogIn into user

    @PostMapping("/login")
    public void loginUser(@RequestBody CredentialsDto credentials){

        commandGateway.send(
                new UserLogInCommand(
                        credentials.getLogin(),
                        credentials.getPassword()
                ),
                LoggingCallback.INSTANCE);
    }

    //LogOut from user

    @PostMapping("{login}/logout")
    public void logoutUser(@PathVariable String login){

        commandGateway.send(
                new UserLogOutCommand(
                        login
                )
        );
    }

    //Delete user

    @PostMapping("{login}/delete")
    public void deleteUser(@PathVariable String login){

        commandGateway.send(
                new DeleteUserCommand(
                        login
                )
        );
    }

    //Add money to user

    @PostMapping("{login}/addmoney/{amount}")
    public void addMoney(@PathVariable String login,@PathVariable Double amount){

        commandGateway.send(
                new AddMoneyToBalanceCommand(
                        login,
                        amount
                )
        );
    }

    //Just for Testing
    //Manual withdraw money from user

    @PostMapping("{login}/withdraw/{amount}")
    public void withdrawMoney(@PathVariable String login,@PathVariable Double amount){

        commandGateway.send(
                new WithdrawMoneyCommand(
                        login,
                        amount
                )
        );
    }

}
