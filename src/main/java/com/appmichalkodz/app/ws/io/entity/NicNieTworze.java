package com.appmichalkodz.app.ws.io.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.io.Serializable;

@Entity(name="nicsieniestworzy")
public class NicNieTworze implements Serializable {
    //spring.jpa.hibernate.ddl-auto=none - i teraz nie sworzy sie tabelka bo to ustawione jest
    private static final long serialVersionUID = 7614980697295481703L;

    @GeneratedValue
    @Id
    private long id;


    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }
}
