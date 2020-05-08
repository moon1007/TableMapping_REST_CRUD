package com.practice.emp.models;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import javax.persistence.AttributeOverride;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

/**
 * Project class
 */
@Entity
@Table(name="PROJECT")
@AttributeOverride(name = "id", column = @Column(name="PROJ_ID"))
public class ProjectPojo extends PojoBase implements Serializable {
    /** explicit set serialVersionUID */
    private static final long serialVersionUID = 1L;

    protected String description;
    protected String name;
   
    protected List<EmployeePojo> employees  = new ArrayList<>();

    @ManyToMany(mappedBy="projects")
    public List<EmployeePojo> getEmployees() {
        return employees;
    }

    public void setEmployees(List<EmployeePojo> employees) {
        this.employees = employees;
    }

    public ProjectPojo() {
        super();
    }
    
    public String getDescription() {
        return description;
    }


    public void setDescription(String description) {
        this.description = description;
    }


    public String getName() {
        return name;
    }


    public void setName(String name) {
        this.name = name;
    }


    

}