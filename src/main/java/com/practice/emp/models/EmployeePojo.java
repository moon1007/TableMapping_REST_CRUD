package com.practice.emp.models;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.AttributeOverride;
import javax.persistence.AttributeOverrides;
import javax.persistence.CascadeType;
import javax.persistence.CollectionTable;
import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity(name = "Employee")
@Table(name = "EMPLOYEE")
@EntityListeners({ PojoListener.class })
@AttributeOverride(name = "id", column = @Column(name="EMP_ID"))
public class EmployeePojo extends PojoBase implements Serializable {
    /** explicit set serialVersionUID */
    private static final long serialVersionUID = 1L;

    protected String firstName;
    protected String lastName;
    protected String email;
    protected String title;
    protected Double salary;
    protected AddressPojo address; // 1:1
    protected List<PhonePojo> phones = new ArrayList<>(); // 1:M
    protected List<ProjectPojo> projects = new ArrayList<>(); // M:N
    protected List<EmployeeTask> tasks = new ArrayList<>(); //embeddable M:1

    
    @ElementCollection
    @CollectionTable(name="EMPLOYEE_TASKS", joinColumns=@JoinColumn(name="OWNING_EMP_ID"))
    @AttributeOverrides(
            @AttributeOverride(name="description", column=@Column(name="TASK_DESCRIPTION")))
    public List<EmployeeTask> getTasks() {
        return tasks;
    }
    public void setTasks(List<EmployeeTask> tasks) {
        this.tasks = tasks;
    }
       
    
    //M:N
    @ManyToMany
    @JoinTable(name="EMP_PROJ", joinColumns=@JoinColumn(name="EMP_ID", referencedColumnName="EMP_ID"), inverseJoinColumns=@JoinColumn(name="PROJ_ID", referencedColumnName="PROJ_ID"))
    public List<ProjectPojo> getProjects() {
        return projects;
    }
    public void setProjects(List<ProjectPojo> projects) {
        this.projects = projects;
    }
    
    public void addProject(ProjectPojo project) {
       getProjects().add(project);
       project.getEmployees().add(this);
    }
    
    
    
    // 1:M
    @OneToMany (mappedBy ="owningEmployee", cascade=CascadeType.ALL, orphanRemoval=true)
    public List<PhonePojo> getPhones() {
        return phones;
    }
    public void setPhones(List<PhonePojo> phones) {
        this.phones = phones;
    }
  
    //1:1
    @OneToOne
    @JoinColumn(name="ADDR_ID")
    public AddressPojo getAddress() {
        return address;
    }
    
    public void setAddress(AddressPojo address) {
        this.address = address;
    }
    
    // JPA requires each @Entity class have a default constructor
    public EmployeePojo() {
        super();
    }
    
    
    /**
     * @return the value for firstName
     */
    @Column(name = "FNAME")
   
    public String getFirstName() {
        return firstName;
    }
    /**
     * @param firstName new value for firstName
     */
    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    /**
     * @return the value for lastName
     */
    @Column(name = "LNAME")
   
    public String getLastName() {
        return lastName;
    }
    /**
     * @param lastName new value for lastName
     */
    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    /**
     * @return the value for email
     */
 
    public String getEmail() {
        return email;
    }
    /**
     * @param email new value for email
     */
    public void setEmail(String email) {
        this.email = email;
    }

    /**
     * @return the value for title
     */
 
    public String getTitle() {
        return title;
    }
    /**
     * @param title new value for title
     */
    public void setTitle(String title) {
        this.title = title;
    }

    /**
     * @return the value for salary
     */
  
    public Double getSalary() {
        return salary;
    }
    /**
     * @param salary new value for salary
     */
    public void setSalary(Double salary) {
        this.salary = salary;
    }
    
    
    public void addPhone(PhonePojo p) {
        getPhones().add(p);
        p.setOwningEmployee(this);
    }
}