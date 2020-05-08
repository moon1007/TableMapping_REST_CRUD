package com.practice.emp.models;

import java.io.Serializable;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

@Entity
@DiscriminatorValue(value="M")
public class MobilePhone extends PhonePojo implements Serializable {
    private static final long serialVersionUID = 1L;

    protected String provider;

    public MobilePhone() {
        super();
    }

    public String getProvider() {
        return provider;
    }

    public void setProvider(String provider) {
        this.provider = provider;
    }
    
    

}