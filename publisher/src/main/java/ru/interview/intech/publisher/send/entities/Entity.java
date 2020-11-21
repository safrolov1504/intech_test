package ru.interview.intech.publisher.send.entities;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class Entity {
    private int id;
    private long msisdn;
    private long timestamp;
    private Action action;

    public void setAction(int code) {
        this.action = Action.getByCode(code);
    }

    @Override
    public String toString() {
        return "Entity{" +
                "id=" + id +
                ", msisdn=" + msisdn +
                ", timestamp=" + timestamp +
                ", action=" + action +
                '}';
    }

    private  enum Action{
        PURCHASE("PURCHASE",0),
        SUBSCRIPTION("SUBSCRIPTION",1);

        Action(String name, int code) {
            this.name = name;
            this.code = code;
        }

        private String name;
        private int code;

        public static Action getByCode(int code){
            if(code > 5)
                return PURCHASE;
            else
                return SUBSCRIPTION;
        }

        @Override
        public String toString() {
            return name;
        }
    }
}
