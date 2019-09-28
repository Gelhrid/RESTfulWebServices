package com.appmichalkodz.app.ws.io.entity;

import javax.persistence.Entity;
import javax.persistence.Id;
import java.io.Serializable;

@Entity
public class Dupa implements Serializable {

    private static final long serialVersionUID = -5669385735091783153L;

    @Id
    private Long cosik;
    private String fifek;
    private String fifek32323;
}
