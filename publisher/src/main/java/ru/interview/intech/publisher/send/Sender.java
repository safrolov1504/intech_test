package ru.interview.intech.publisher.send;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;
import ru.interview.intech.publisher.ApplicationProperty;
import ru.interview.intech.publisher.send.entities.Entity;

import java.io.IOException;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Date;

@Slf4j
@Component
@Scope("prototype")
public class Sender implements Runnable{

    private ApplicationProperty applicationProperty;
    private int i;
    private ObjectMapper obj;

    @Autowired
    public Sender(ApplicationProperty applicationProperty) {
        this.applicationProperty = applicationProperty;
        i = 0;
        this.obj = new ObjectMapper();
    }

    @Override
    public void run() {
        log.debug("Начала генерации нового сообщения");
        Entity entity = creatEntity();
        String json = creatJson(entity);
        sendMessage(json);
        try {
            Thread.sleep(Long.parseLong(applicationProperty.getSendDelay()));
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }


    private void sendMessage(String json) {
        try {
            log.info("Отправка сообщения: [{}]", json);
            URL url = new URL(applicationProperty.getUrl());
            HttpURLConnection con = (HttpURLConnection)url.openConnection();
            con.setRequestMethod("POST");
            con.setRequestProperty("Content-Type", "application/json; utf-8");
            con.setRequestProperty("Accept", "application/json");
            con.setDoOutput(true);
            OutputStream os = con.getOutputStream();
            byte[] input = json.getBytes("utf-8");
            os.write(input, 0, input.length);

            int code = con.getResponseCode();
            log.info("Получен ответ с кодом [{}]",code);
        } catch (IOException e) {
            log.error("Ошибка отправки [{}]", e.getMessage());
            //e.printStackTrace();
        }
    }

    private Entity creatEntity(){
        Date date = new Date();
        Entity entity = Entity.builder()
                .id(i++)
                .msisdn(generateRandom(100000,0))
                .timestamp(date.getTime())
                .build();
        entity.setAction(generateRandom(10,0));
        log.debug(entity.toString());
        return entity;
    }

    private String creatJson(Entity entity){
        try {
            String jsonStr = obj.writeValueAsString(entity);
            log.debug(jsonStr);
            return jsonStr;
        } catch (JsonProcessingException e) {
            log.error(e.getMessage());
            e.printStackTrace();
        }
        throw new RuntimeException("Все плохо");
    }

    private int generateRandom(int max, int min){
        return (int) (Math.random() * ((max - min) + 1)) + min;
    }

}
