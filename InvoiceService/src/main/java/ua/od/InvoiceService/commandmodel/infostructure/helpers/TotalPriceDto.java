package ua.od.InvoiceService.commandmodel.infostructure.helpers;

public class TotalPriceDto {
    private final Double totalPrice;
    private final String useLogin;

    public TotalPriceDto(Double totalPrice, String useLogin) {

        this.totalPrice = totalPrice;
        this.useLogin = useLogin;
    }

    public Double getTotalPrice() {

        return totalPrice;
    }

    public String getUseLogin() {

        return useLogin;
    }
}
