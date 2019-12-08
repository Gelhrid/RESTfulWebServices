package com.appmichalkodz.app.ws.io.entity;

import javax.persistence.Entity;
import java.io.Serializable;

@Entity(name = "automatyczna")
public class AutomatzcynaTabelka implements Serializable {
    private static final long serialVersionUID = -5887549244896710573L;
    private Long id;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
}
