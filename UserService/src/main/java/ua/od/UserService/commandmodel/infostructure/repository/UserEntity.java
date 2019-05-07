package ua.od.UserService.commandmodel.infostructure.repository;

import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.Objects;

@Entity
@Table(name = "customer")
@DynamicUpdate
public class UserEntity {

    @Id
    @NotNull
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long Id;

    private String login;
    private String password;
    private Double balance;
    private String State;

    public UserEntity(@NotNull Long id, String login, String password, Double balance, String state) {
        Id = id;
        this.login = login;
        this.password = password;
        this.balance = balance;
        State = state;
    }

    public UserEntity( String login, String password, Double balance, String state) {
        this.login = login;
        this.password = password;
        this.balance = balance;
        State = state;
    }

    public UserEntity() { }

    public Long getId() {

        return Id;
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

        return State;
    }

    public void setBalance(Double balance) {

        this.balance = balance;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        UserEntity that = (UserEntity) o;
        return Objects.equals(Id, that.Id) &&
                Objects.equals(login, that.login) &&
                Objects.equals(password, that.password) &&
                Objects.equals(balance, that.balance) &&
                Objects.equals(State, that.State);
    }

    @Override
    public int hashCode() {
        return Objects.hash(Id, login, password, balance, State);
    }

    @Override
    public String toString() {
        return "UserEntity{" +
                "Id=" + Id +
                ", login='" + login + '\'' +
                ", password='" + password + '\'' +
                ", balance=" + balance +
                ", State='" + State + '\'' +
                '}';
    }
}
