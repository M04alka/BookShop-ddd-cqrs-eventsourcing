package ua.od.InvoiceService.commandmodel.infostructure.repository;

import org.springframework.data.jpa.repository.JpaRepository;

public interface UserBookRepository extends JpaRepository<UserBookEntity,Long> {
    UserBookEntity findByUserLoginAndBookId(String userLogin, Long bookId);
}
