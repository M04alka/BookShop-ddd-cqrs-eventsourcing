package ua.od.UserService.coreappi.events;

public class UserLogedInEvent {

    private final String login;
    private final String password;
    private final String state;

    public UserLogedInEvent(String login, String password, String state) {

        this.login = login;
        this.password = password;
        this.state = state;
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
