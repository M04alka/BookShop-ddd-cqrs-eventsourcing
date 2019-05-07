package ua.od.UserService.coreappi.Exeptions;

public class SuchLoginAlreadyExistExeption extends Exception {

    public SuchLoginAlreadyExistExeption(String message) {
        super(message);
    }
}
