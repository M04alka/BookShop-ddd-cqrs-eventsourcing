package ua.od.UserService.querymodel;

import org.springframework.beans.factory.annotation.Autowired;
import ua.od.UserService.commandmodel.infostructure.repository.UserEntity;
import ua.od.UserService.commandmodel.infostructure.repository.UserRepositoryInterface;

public class QueryService {
    @Autowired
    UserRepositoryInterface userRepository;

    public BalanceDto getBalance(String login){

        UserEntity user = userRepository.findByLogin(login);
        BalanceDto balanceDto = new BalanceDto(user.getLogin(),user.getBalance());
        return  balanceDto;
    }
}
