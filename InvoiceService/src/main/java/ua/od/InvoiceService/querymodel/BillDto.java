package ua.od.InvoiceService.querymodel;

public class BillDto {
    private final String login;
    private final Double bill;

    public BillDto(String login, Double bill) {

        this.login = login;
        this.bill = bill;
    }

    public String getLogin() {

        return login;
    }

    public Double getBill() {

        return bill;
    }
}
