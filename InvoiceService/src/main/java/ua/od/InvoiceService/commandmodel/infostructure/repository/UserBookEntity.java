package ua.od.InvoiceService.commandmodel.infostructure.repository;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

@Entity
@Table(name = "user_book")
public class UserBookEntity {

    @Id
    @NotNull
    @GeneratedValue
    private Long Id;

    private String userLogin;
    private Long bookId;

    public UserBookEntity(@NotNull Long id, String userLogin, Long bookId) {

        Id = id;
        this.userLogin = userLogin;
        this.bookId = bookId;
    }

    public UserBookEntity( String userLogin, Long bookId) {

        this.userLogin = userLogin;
        this.bookId = bookId;
    }

    public UserBookEntity() {

    }

    public Long getId() {

        return Id;
    }

    public String getUserLogin() {

        return userLogin;
    }

    public Long getBookId() {

        return bookId;
    }

    public void setId(Long id) {

        Id = id;
    }

    public void setUserLogin(String userLogin) {

        this.userLogin = userLogin;
    }

    public void setBookId(Long bookId) {

        this.bookId = bookId;
    }
}
