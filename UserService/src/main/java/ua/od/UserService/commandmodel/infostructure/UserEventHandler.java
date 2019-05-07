package ua.od.UserService.commandmodel.infostructure;

import org.axonframework.commandhandling.gateway.CommandGateway;
import org.axonframework.eventhandling.EventHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.KafkaHeaders;
import org.springframework.messaging.Message;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.stereotype.Component;
import ua.od.UserService.commandmodel.infostructure.helpers.CurrencyState;
import ua.od.UserService.commandmodel.infostructure.helpers.TotalPriceDto;
import ua.od.UserService.commandmodel.infostructure.helpers.UserWalletVerificationDto;
import ua.od.UserService.commandmodel.infostructure.repository.UserEntity;
import ua.od.UserService.commandmodel.infostructure.service.UserService;
import ua.od.UserService.coreappi.commands.WithdrawMoneyCommand;
import ua.od.UserService.coreappi.events.*;

@Component
public class UserEventHandler {

    @Autowired
    UserService userService;

    @Autowired
    private KafkaTemplate<String, UserLogedInEvent> kafkaUserLogedInEvent;
    private final String TOPIC = "UserLogedIn";

    @Autowired
    private KafkaTemplate<String, UserWalletVerificationDto> userWalletVerificationDtoTemplate;
    private final String STRING_TOPIC = "VerifyMoneyAmmount";

    @Autowired
    private final CommandGateway commandGateway;

    public UserEventHandler(CommandGateway commandGateway) {

        this.commandGateway = commandGateway;
    }

    //Save new user when event happens

    @EventHandler
    public void on(UserCreatedEvent event){

        userService.newUser(new UserEntity(event.getLogin(),event.getPassword(),event.getBalance(),event.getState()));
    }

    //Sand message to create invoice when user login event happen first time

    @EventHandler
    public void on(UserLogedInEvent event){

        Message<UserLogedInEvent> message = MessageBuilder
                .withPayload(event)
                .setHeader(KafkaHeaders.TOPIC, TOPIC)
                .build();
        kafkaUserLogedInEvent.send(message);
    }

    //Update user balance +

    @EventHandler
    public void on(MoneyAddedToBalanceEvent event){

        userService.addMoney(event.getAmmount(),event.getLogin());
    }

    //Update user balance -

    @EventHandler
    public void on(MoneyWithdrawedEvent event){

        userService.withdrawMoney(event.getAmmount(),event.getLogin());
    }

    //Delete user from db when delete event happens

    @EventHandler
    public void on(UserDeletedEvent event) {

        userService.deleteUser(event.getLogin());
    }

    //Listener for verification user money ammount

    @KafkaListener(topics = "CheckMoney", groupId = "InvoiceBill", containerFactory = "kafkaListenerContainerFactory")
    public void consume(TotalPriceDto totalPriceDto){
        UserEntity userEntity = userService.checkLogin(totalPriceDto.getUseLogin());
        if(userEntity.getBalance()>=totalPriceDto.getTotalPrice()){

            commandGateway.send(
                    new WithdrawMoneyCommand(
                            totalPriceDto.getUseLogin(),
                            totalPriceDto.getTotalPrice()
                    )
            );

            Message< UserWalletVerificationDto> message = MessageBuilder
                    .withPayload(new UserWalletVerificationDto(CurrencyState.Accept.toString(),totalPriceDto.getUseLogin(),totalPriceDto.getTotalPrice()))
                    .setHeader(KafkaHeaders.TOPIC, STRING_TOPIC)
                    .build();
            userWalletVerificationDtoTemplate.send(message);
        }

        else {

            Message< UserWalletVerificationDto> message = MessageBuilder
                .withPayload(new UserWalletVerificationDto(CurrencyState.Decline.toString(),totalPriceDto.getUseLogin(),totalPriceDto.getTotalPrice()))
                .setHeader(KafkaHeaders.TOPIC, STRING_TOPIC)
                .build();
            userWalletVerificationDtoTemplate.send(message);
        }


    }


}
