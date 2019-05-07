package ua.od.InvoiceService.commandmodel.infostructure.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.KafkaHeaders;
import org.springframework.messaging.Message;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.stereotype.Service;
import ua.od.InvoiceService.commandmodel.infostructure.helpers.TotalPriceDto;
import ua.od.InvoiceService.commandmodel.infostructure.repository.InvoiceEntity;
import ua.od.InvoiceService.commandmodel.infostructure.repository.InvoiceRepositoryInterface;

import javax.transaction.Transactional;


@Service
@Transactional
public class InvoiceService {

    @Autowired
    InvoiceRepositoryInterface invoiceRepository;

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

    //Check ammount of money on client

    public void checkMoney(TotalPriceDto totalPriceDto){

        Message<TotalPriceDto> message = MessageBuilder
                .withPayload(totalPriceDto)
                .setHeader(KafkaHeaders.TOPIC, TOPIC)
                .build();
        kafkaUserLogedInEvent.send(message);
    }

}
