package ua.od.UserService.coreappi.commands;

import org.axonframework.modelling.command.TargetAggregateIdentifier;

public class AddMoneyToBalanceCommand {

    @TargetAggregateIdentifier
    private final String login;

    private final Double ammount;

    public AddMoneyToBalanceCommand(String login, Double ammount) {

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
