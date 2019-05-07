package ua.od.UserService.coreappi.commands;

import org.axonframework.modelling.command.TargetAggregateIdentifier;
import ua.od.UserService.coreappi.States.State;


public class CreateUserCommand {

    @TargetAggregateIdentifier
    private final String login;

    private final String password;
    private final Double balance;
    private final String state;

    public CreateUserCommand(String login, String password) {

        this.login = login;
        this.password = password;
        this.balance = 0.d;
        this.state = State.Created.toString();
    }

    public String getLogin() {

        return login;
    }

    public String getPassword() {

        return password;
    }

    public Double getBalance() {

        return balance;
    }

    public String getState() {

        return state;
    }
}
