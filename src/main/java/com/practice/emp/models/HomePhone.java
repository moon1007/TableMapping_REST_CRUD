package com.practice.emp.models;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

@Entity
@DiscriminatorValue(value="H")
public class HomePhone extends PhonePojo implements Serializable {
    /** explicit set serialVersionUID */
    private static final long serialVersionUID = 1L;

    protected String googleMapDirections;

    public HomePhone() {
        super();
    }

    @Column (name="MAP_COORDS")
    public String getGoogleMapDirections() {
        return googleMapDirections;
    }

    public void setGoogleMapDirections(String googleMapDirections) {
        this.googleMapDirections = googleMapDirections;
    }
    
    
}