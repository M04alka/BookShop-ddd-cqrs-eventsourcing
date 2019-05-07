package ua.od.UserService.commandmodel.infostructure.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface UserRepositoryInterface extends JpaRepository<UserEntity,Long> {

    UserEntity findByLoginAndPassword(String login,String password);
    UserEntity findByLogin(String login);
}
