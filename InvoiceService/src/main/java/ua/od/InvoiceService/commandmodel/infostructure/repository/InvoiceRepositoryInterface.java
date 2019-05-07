package ua.od.InvoiceService.commandmodel.infostructure.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface InvoiceRepositoryInterface extends JpaRepository<InvoiceEntity,Long> {
    InvoiceEntity findByUserLogin(String login);
}
