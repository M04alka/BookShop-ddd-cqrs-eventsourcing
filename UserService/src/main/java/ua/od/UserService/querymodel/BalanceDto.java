package ua.od.UserService.querymodel;

public class BalanceDto {

    private final String login;
    private final Double balance;

    public BalanceDto(String login, Double balance) {

        this.login = login;
        this.balance = balance;
    }

    public String getLogin() {

        return login;
    }

    public Double getBalance() {

        return balance;
    }
}
