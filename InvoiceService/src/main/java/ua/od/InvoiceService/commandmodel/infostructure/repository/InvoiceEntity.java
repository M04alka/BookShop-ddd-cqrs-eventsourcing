package ua.od.InvoiceService.commandmodel.infostructure.repository;

import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;


@Entity
@Table(name = "invoice")
@DynamicUpdate
public class InvoiceEntity {

    @Id
    @NotNull
    @GeneratedValue
    private Long Id;

    private String userLogin;
    private Double price;

    public InvoiceEntity(@NotNull Long id,String userLogin,Double price) {

        Id = id;
        this.userLogin = userLogin;
        this.price = price;
    }

    public InvoiceEntity(String userLogin,Double price) {

        this.userLogin = userLogin;
        this.price = price;
    }

    public InvoiceEntity() {

    }

    public Long getId() {
        return Id;
    }

    public void setId(Long id) {
        Id = id;
    }

    public String getUserLogin() {
        return userLogin;
    }

    public void setUserLogin(String userLogin) {
        this.userLogin = userLogin;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }
}
