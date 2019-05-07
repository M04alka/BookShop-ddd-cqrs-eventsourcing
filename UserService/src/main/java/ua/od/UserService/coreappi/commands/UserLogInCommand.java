package ua.od.UserService.coreappi.commands;

import org.axonframework.modelling.command.TargetAggregateIdentifier;
import ua.od.UserService.coreappi.States.State;

public class UserLogInCommand {

    @TargetAggregateIdentifier
    private final String login;

    private final String password;
    private final String state;

    public UserLogInCommand(String login, String password) {

        this.login = login;
        this.password = password;
        this.state = State.LogedIn.toString();
    }

    public String getLogin() {

        return login;
    }

    public String getPassword() {

        return password;
    }

    public String getState() {

        return state;
    }
}
