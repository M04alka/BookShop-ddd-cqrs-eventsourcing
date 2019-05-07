package ua.od.UserService.coreappi.events;

public class UserCreatedEvent {

    private final String login;
    private final String password;
    private final Double balance;
    private final String state;

    public UserCreatedEvent(String login, String password, Double balance, String state) {

        this.login = login;
        this.password = password;
        this.balance = balance;
        this.state = state;
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
