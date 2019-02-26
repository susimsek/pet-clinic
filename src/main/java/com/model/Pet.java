package com.model;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "t_pet")
public class Pet  extends BaseEntity{

    @Column(name = "name")
    private String name;

    @Column(name = "birth_date")
    private Date birthDate;

    @ManyToOne
    @JoinColumn(name = "owner_id")
    private Owner owner;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Date getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(Date birthDate) {
        this.birthDate = birthDate;
    }

    public Owner getOwner() {
        return owner;
    }

    public void setOwner(Owner owner) {
        this.owner = owner;
    }

    @Override
    public String toString() {
        return "Pet{" +
                "name='" + name + '\'' +
                ", birthDate=" + birthDate +
                ", owner=" + owner +
                '}';
    }
}
