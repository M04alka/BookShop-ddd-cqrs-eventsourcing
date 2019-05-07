package ua.od.UserService.coreappi.events;

public class UserLogedOutEvent {

    private final String login;
    private final String state;

    public UserLogedOutEvent(String login, String state) {

        this.login = login;
        this.state = state;
    }

    public String getLogin() {

        return login;
    }

    public String getState() {

        return state;
    }
}
