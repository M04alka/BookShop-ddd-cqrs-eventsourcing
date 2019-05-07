package ua.od.UserService.coreappi.commands;

import org.axonframework.modelling.command.TargetAggregateIdentifier;

public class WithdrawMoneyCommand {

    @TargetAggregateIdentifier
    private final String login;

    private final Double ammount;

    public WithdrawMoneyCommand(String login, Double ammount) {

        this.login = login;
        this.ammount = ammount;
    }

    public String getLogin() {

        return login;
    }

    public Double getAmmount() {

        return ammount;
    }
}
