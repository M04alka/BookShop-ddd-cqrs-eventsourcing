package ua.od.UserService.coreappi.events;

public class MoneyWithdrawedEvent  {

    private final String login;
    private final Double ammount;

    public MoneyWithdrawedEvent(String login, Double ammount) {

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
