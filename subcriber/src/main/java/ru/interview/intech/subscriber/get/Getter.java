package ru.interview.intech.subscriber.get;


import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import ru.interview.intech.subscriber.get.connection.DAO;
import ru.interview.intech.subscriber.get.entities.Entity;
import ru.interview.intech.subscriber.get.entities.EntityPurchase;
import ru.interview.intech.subscriber.get.entities.EntitySubscription;
import ru.interview.intech.subscriber.get.entities.Message;

@RestController
@Slf4j
public class Getter {

    @PostMapping("/send")
    public void getMessage(@RequestBody Entity entity){
        Message message = new Message(entity.toString());
        log.info("Полученно сообщение [{}]",message.toString());
        DAO<Message> sessionDAOMessage = new DAO<>(Message.class);
        sessionDAOMessage.add(message);
        String nameTable = null;

        switch (entity.getAction()){
            case PURCHASE:
                DAO<EntityPurchase> sessionDAOPurchase = new DAO<>(EntityPurchase.class);
                sessionDAOPurchase.add(new EntityPurchase(entity, message));
                nameTable = EntityPurchase.nameTable;
                break;
            case SUBSCRIPTION:
                DAO<EntitySubscription> sessionDAOSubscriber = new DAO<>(EntitySubscription.class);
                sessionDAOSubscriber.add(new EntitySubscription(entity, message));
                nameTable = EntitySubscription.nameTable;
                break;
        }
        log.info("Сообщение сохранено в таблице: [{}]", nameTable);
    }
}
