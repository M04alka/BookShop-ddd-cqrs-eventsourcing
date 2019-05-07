package ua.od.UserService.coreappi.events;

public class MoneyAddedToBalanceEvent {

    private final String login;
    private final Double ammount;

    public MoneyAddedToBalanceEvent(String login, Double ammount) {

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
