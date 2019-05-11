package ua.od.InvoiceService.commandmodel.infostructure.service;

import jdk.nashorn.internal.runtime.options.Option;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.KafkaHeaders;
import org.springframework.messaging.Message;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.stereotype.Service;
import ua.od.InvoiceService.commandmodel.infostructure.helpers.TotalPriceDto;
import ua.od.InvoiceService.commandmodel.infostructure.repository.InvoiceEntity;
import ua.od.InvoiceService.commandmodel.infostructure.repository.InvoiceRepositoryInterface;
import ua.od.InvoiceService.commandmodel.infostructure.repository.UserBookEntity;
import ua.od.InvoiceService.commandmodel.infostructure.repository.UserBookRepository;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;


@Service
@Transactional
public class InvoiceService {

    @Autowired
    InvoiceRepositoryInterface invoiceRepository;
    @Autowired
    UserBookRepository userBookRepository;

    @Autowired
    private KafkaTemplate<String, TotalPriceDto> kafkaUserLogedInEvent;
    private final String TOPIC = "CheckMoney";


    //Check invoice id

    public InvoiceEntity getInvoice(String login){

        return invoiceRepository.findByUserLogin(login);
    }

    //Save new Invoice id

    public InvoiceEntity saveInvoice(InvoiceEntity invoiceEntity){

        return invoiceRepository.save(invoiceEntity);
    }

    public UserBookEntity checkUserBooks(String userLogin, Long bookId){
        return userBookRepository.findByUserLoginAndBookId(userLogin,bookId);
    }

    //Check ammount of money on client

    public void checkMoney(TotalPriceDto totalPriceDto){

        Message<TotalPriceDto> message = MessageBuilder
                .withPayload(totalPriceDto)
                .setHeader(KafkaHeaders.TOPIC, TOPIC)
                .build();
        kafkaUserLogedInEvent.send(message);
    }

    public void orderList(String userLogin,List<Long> bookList){

        for (int i = 0; i<bookList.size();i++){
            userBookRepository.save(new UserBookEntity(userLogin,bookList.get(i)));
        }
    }

}
