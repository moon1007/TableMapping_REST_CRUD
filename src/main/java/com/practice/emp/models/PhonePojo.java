package com.practice.emp.models;

import javax.persistence.AttributeOverride;
import javax.persistence.Column;
import javax.persistence.DiscriminatorColumn;
import javax.persistence.Entity;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity(name="Phone")
@Table(name="PHONE")
@AttributeOverride(name = "id", column = @Column(name="PHONE_ID"))
@Inheritance(strategy=InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name="PHONE_TYPE", length=1)
public abstract class PhonePojo extends PojoBase { //add abstract when it is needed

    protected String areaCode;
    protected String phoneNumber;
    protected String phoneType;

    protected EmployeePojo owningEmployee;

    public PhonePojo() {
        super();
    }
    
    @Column (name="AREACODE")
    public String getAreaCode() {
        return areaCode;
    }


    public void setAreaCode(String areaCode) {
        this.areaCode = areaCode;
    }


    @Column (name="PHONENUMBER")
    public String getPhoneNumber() {
        return phoneNumber;
    }


    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }


    @Column(name="PHONE_TYPE")
    public String getPhoneType() {
        return phoneType;
    }


    public void setPhoneType(String phoneType) {
        this.phoneType = phoneType;
    }

    @ManyToOne
    @JoinColumn(name="OWNING_EMP_ID")
    public EmployeePojo getOwningEmployee() {
        return owningEmployee;
    }

    public void setOwningEmployee(EmployeePojo owningEmployee) {
        this.owningEmployee = owningEmployee;
    }

    

}