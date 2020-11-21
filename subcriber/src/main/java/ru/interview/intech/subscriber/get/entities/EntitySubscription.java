package ru.interview.intech.subscriber.get.entities;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;

@javax.persistence.Entity
@NoArgsConstructor
@Getter
@Setter
@Table(name = EntitySubscription.nameTable, schema = "intech")
public class EntitySubscription implements Serializable {

    public static final String nameTable = "\"SUBSCRIPTION\"";

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_entity", unique = true, nullable = false)
    private int idEntity;

    @Column(name = "id")
    private int id;

    @Column(name = "nn_msisdn")
    private long msisdn;

    @Column(name = "nn_timestamp")
    private long timestamp;

    @Column(name = "nm_action")
    private String action;

    @OneToOne
    @JoinColumn(name = "id_message", referencedColumnName = "id")
    private Message message;

    public EntitySubscription(Entity entity, Message message) {
        this.id = entity.getId();
        this.msisdn = entity.getMsisdn();
        this.timestamp = entity.getTimestamp();
        this.action = entity.getAction().getName();
        this.message = message;
    }
}
