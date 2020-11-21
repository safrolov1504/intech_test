package ru.interview.intech.subscriber.get.entities;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Table;

@Getter
@Setter
@Builder
public class Entity {

    @JsonProperty
    private int id;

    @JsonProperty
    private long msisdn;

    @JsonProperty
    private long timestamp;

    @JsonProperty
    private Action action;

    @Override
    public String toString() {
        return "Entity{" +
                "id=" + id +
                ", msisdn=" + msisdn +
                ", timestamp=" + timestamp +
                ", action=" + action +
                '}';
    }

    public void setAction(String name) {
        this.action = Action.getByName(name);
    }

    @Getter
    public enum Action{
        PURCHASE("PURCHASE",0),
        SUBSCRIPTION("SUBSCRIPTION",1);

        Action(String name, int code) {
            this.name = name;
            this.code = code;
        }

        private String name;
        private int code;

        public static Action getByName(String name){
            for (Action action:values()) {
                if(action.name.equals(name)){
                    return action;
                }
            }
            throw new RuntimeException("Не известное событие");
        }

        public static Action getByCode(int code){
            for (Action action:values()) {
                if(action.code == code){
                    return action;
                }
            }
            throw new RuntimeException("Не известное событие");
        }


        @Override
        public String toString() {
            return name;
        }
    }
}
