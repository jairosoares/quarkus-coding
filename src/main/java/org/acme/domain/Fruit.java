package org.acme.domain;

import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "fruit")
public class Fruit extends AppEntityBase {

    public String name;

}
