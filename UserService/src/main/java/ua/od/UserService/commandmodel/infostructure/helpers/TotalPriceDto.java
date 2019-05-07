package ua.od.UserService.commandmodel.infostructure.helpers;

public class TotalPriceDto {
    private  Double totalPrice;
    private  String useLogin;

    public TotalPriceDto(Double totalPrice, String useLogin) {
        this.totalPrice = totalPrice;
        this.useLogin = useLogin;
    }

    public TotalPriceDto() {

    }

    public Double getTotalPrice() {
        return totalPrice;
    }

    public String getUseLogin() {
        return useLogin;
    }
}
