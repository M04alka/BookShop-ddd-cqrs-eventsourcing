package ua.od.UserService.coreappi.commands;

import org.axonframework.modelling.command.TargetAggregateIdentifier;
import ua.od.UserService.coreappi.States.State;

public class DeleteUserCommand {

    @TargetAggregateIdentifier
    private final String login;

    private final String state;

    public DeleteUserCommand(String login) {

        this.login = login;
        this.state = State.Deleted.toString();
    }

    public String getLogin() {

        return login;
    }

    public String getState() {

        return state;
    }

}
