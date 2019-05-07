package ua.od.UserService.commandmodel.infostructure.helpers;

public class UserWalletVerificationDto {
    private  String status;
    private  String useLogin;
    private  Double totalPrice;

    public UserWalletVerificationDto(String status, String useLogin, Double totalPrice) {
        this.status = status;
        this.useLogin = useLogin;
        this.totalPrice = totalPrice;
    }
    public UserWalletVerificationDto() {

    }


    public String getStatus() {
        return status;
    }

    public String getUseLogin() {
        return useLogin;
    }

    public Double getTotalPrice() {
        return totalPrice;
    }
}
