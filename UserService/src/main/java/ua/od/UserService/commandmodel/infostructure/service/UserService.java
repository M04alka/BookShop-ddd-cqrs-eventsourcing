package ua.od.UserService.commandmodel.infostructure.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ua.od.UserService.commandmodel.infostructure.repository.UserEntity;
import ua.od.UserService.commandmodel.infostructure.repository.UserRepositoryInterface;

import javax.transaction.Transactional;

@Service
@Transactional
public class UserService {

    @Autowired
    UserRepositoryInterface userRepository;

    //Save user

    public void newUser(UserEntity userEntity) {

        userRepository.save(userEntity);
    }

    //Check if such login already exist

    public UserEntity checkLogin(String login){

        return userRepository.findByLogin(login);
    }

    //User verification

    public UserEntity verifyUser(String login,String password) {

        return userRepository.findByLoginAndPassword(login,password);
    }

    //Adding money service

    public void addMoney(Double ammount, String login){

        UserEntity user = userRepository.findByLogin(login);
        user.setBalance(user.getBalance()+ ammount);
        userRepository.save(user);
    }

    //Withdrawing money service

    public void withdrawMoney(Double ammount, String login){

        UserEntity user = userRepository.findByLogin(login);
        user.setBalance(user.getBalance() - ammount);
        userRepository.save(user);
    }


    //Delete user service

    public void deleteUser(String login){

        UserEntity user = userRepository.findByLogin(login);
        userRepository.deleteById(user.getId());
    }



}
