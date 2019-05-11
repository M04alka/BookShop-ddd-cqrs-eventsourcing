package ua.od.InvoiceService.commandmodel.infostructure;

public class UserLogedInEventDto {

    private String uid;
    private String login;
    private String password;
    private String state;

    public UserLogedInEventDto(String uid, String login, String password, String state) {
        this.uid = uid;
        this.login = login;
        this.password = password;
        this.state = state;
    }

    public UserLogedInEventDto() {

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
