package io.vdubovsky.redisdemo.model;

import lombok.Data;
import lombok.experimental.Accessors;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.UUID;

@Data
@Accessors(chain = true)
@Entity
@Table(name = "users")
public class User {
    @Id
    private UUID id;

    private String name;
}
