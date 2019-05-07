package ua.od.UserService.commandmodel;

import org.axonframework.commandhandling.CommandHandler;
import org.axonframework.eventhandling.EventHandler;
import org.axonframework.eventsourcing.EventSourcingHandler;
import org.axonframework.modelling.command.AggregateIdentifier;
import org.axonframework.modelling.command.AggregateLifecycle;
import org.axonframework.spring.stereotype.Aggregate;
import ua.od.UserService.commandmodel.infostructure.service.UserService;
import ua.od.UserService.coreappi.Exeptions.SuchLoginAlreadyExistExeption;
import ua.od.UserService.coreappi.Exeptions.VerificationFailedExeption;
import ua.od.UserService.coreappi.States.State;
import ua.od.UserService.coreappi.commands.*;
import ua.od.UserService.coreappi.events.*;

//User Aggregate

@Aggregate
public class User {

    @AggregateIdentifier
    private String login;

    private String password;
    private Double balance;
    private String state;

    public User() {

    }

    //Command for creating new user

    @CommandHandler
    public User(CreateUserCommand command, UserService userService) throws SuchLoginAlreadyExistExeption {
        if(userService.checkLogin(command.getLogin())!=null) {
            throw new SuchLoginAlreadyExistExeption("User with such login { " +command.getLogin() + "} already exist!");
        }
        AggregateLifecycle.apply(
                new UserCreatedEvent(
                        command.getLogin(),
                        command.getPassword(),
                        command.getBalance(),
                        command.getState()
                )
        );
    }

    //Event for creating new user

    @EventSourcingHandler
    public void on(UserCreatedEvent event) {
        this.login = event.getLogin();
        this.password = event.getPassword();
        this.balance = event.getBalance();
        this.state = event.getState();
    }

    //Command for LogIn

    @CommandHandler
    public void handle(UserLogInCommand command, UserService userService) throws VerificationFailedExeption {

        if (userService.verifyUser(command.getLogin(),command.getPassword()).equals(null)) {

            throw new VerificationFailedExeption("Login or password is incorrect");
        }

        if(this.state.equals(State.LogedIn.toString()) ){

            throw new IllegalStateException("You are alredy loged in!");
        }

        AggregateLifecycle.apply(
             new UserLogedInEvent(
                     command.getLogin(),
                     command.getPassword(),
                     command.getState()
             )
        );

    }

    //Event for LogIn

    @EventHandler
    public void on(UserLogedInEvent event){

        this.login = event.getLogin();
        this.state = event.getState();
    }

    //Command for LogOut

    @CommandHandler
    public void handle(UserLogOutCommand command) {

        if(!this.state.equals(State.LogedIn.toString())){

            throw new IllegalStateException("You cant log out!");
        }

        AggregateLifecycle.apply(
                new UserLogedOutEvent(
                        command.getLogin(),
                        command.getState()
                )
        );
    }

    //Event for LogOut

    @EventSourcingHandler
    public void on(UserLogedOutEvent event){

        this.login = event.getLogin();
        this.state = event.getState();
    }

    //Command for Add money

    @CommandHandler
    private void handle(AddMoneyToBalanceCommand command, UserService userService){

        if(userService.checkLogin(command.getLogin())==null) {
            throw new IllegalStateException("Such user doesn't exist!");
        }

        AggregateLifecycle.apply(new MoneyAddedToBalanceEvent(command.getLogin(),command.getAmmount()));
    }

    //Event for Add money

    @EventSourcingHandler
    private void on(MoneyAddedToBalanceEvent event){

        this.balance = this.balance + event.getAmmount();
    }

    //Command for Withdraw money

    @CommandHandler
    private void handle(WithdrawMoneyCommand command,UserService userService){

        if(userService.checkLogin(command.getLogin())==null) {

            throw new IllegalStateException("Such user doesn't exist!");
        }

        if(this.balance< command.getAmmount()){

            throw new IllegalStateException("Not enought money to withdraw a " + command.getAmmount().toString() + " !");
        }

        if(!this.state.equals(State.LogedIn.toString())){

            throw new IllegalStateException("You should log into account to purchase books!");
        }
        AggregateLifecycle.apply(new MoneyWithdrawedEvent(command.getLogin(),command.getAmmount()));
    }

    //Event for Withdraw money

    @EventSourcingHandler
    private void on(MoneyWithdrawedEvent event){

        this.balance = this.balance - event.getAmmount();
    }

    //Command for deleting user

    @CommandHandler
    private void handle(DeleteUserCommand command){

        AggregateLifecycle.apply(new UserDeletedEvent(command.getLogin(),command.getState()));
    }

    //Event for deleting user

    @EventSourcingHandler
    private void on(UserDeletedEvent event){

        this.state = event.getState();
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
