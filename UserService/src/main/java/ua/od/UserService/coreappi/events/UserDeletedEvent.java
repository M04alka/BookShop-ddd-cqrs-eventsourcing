package ua.od.UserService.coreappi.events;

public class UserDeletedEvent {

    private final String login;
    private final String state;

    public UserDeletedEvent( String login, String state) {

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

