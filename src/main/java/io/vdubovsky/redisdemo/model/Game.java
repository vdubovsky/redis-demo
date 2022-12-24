package io.vdubovsky.redisdemo.model;

import lombok.Data;
import lombok.experimental.Accessors;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;
import java.util.UUID;

@Data
@Accessors(chain = true)
@Entity
@Table(name = "game")
public class Game implements Serializable {

    @Id
    private UUID id;

    private String name;

    private Boolean isFinished;

}
