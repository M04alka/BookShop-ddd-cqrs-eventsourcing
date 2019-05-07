package ua.od.InvoiceService.coreappi.events;

public class UserLogedInEvent {

    private final String uid;
    private final String login;
    private final String password;
    private final String state;

    public UserLogedInEvent(String uid, String login, String password, String state) {

        this.uid = uid;
        this.login = login;
        this.password = password;
        this.state = state;
    }

    public String getUid() {

        return uid;
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
