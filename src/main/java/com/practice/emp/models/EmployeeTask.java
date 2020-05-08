package com.practice.emp.models;

import java.time.LocalDateTime;
import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.Embedded;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

//JPA Annotations
@Table(name="EMPLOYEE_TASKS")
@Embeddable
public class EmployeeTask {

    protected String description;
    protected LocalDateTime start;
    protected LocalDateTime endDate;
    protected boolean done;
    
    protected EmployeePojo employee;
    
    @ManyToOne
    public EmployeePojo getEmployee() {
        return employee;
    }

    public void setEmployee(EmployeePojo employee) {
        this.employee = employee;
    }

    public EmployeeTask() {
    }

    @Column(name="TASK_DESCRIPTION")
    public String getDescription() {
        return description;
    }
    public void setDescription(String description) {
        this.description = description;
    }
    
    @Column(name="TASK_START")
    public LocalDateTime getStart() {
        return start;
    }

    public void setStart(LocalDateTime start) {
        this.start = start;
    }

    @Column(name="TASK_END_DATE")
    public LocalDateTime getEndDate() {
        return endDate;
    }

    public void setEndDate(LocalDateTime endDate) {
        this.endDate = endDate;
    }

    @Column(name="TASK_DONE")
    public boolean isDone() {
        return done;
    }

    public void setDone(boolean done) {
        this.done = done;
    }

}