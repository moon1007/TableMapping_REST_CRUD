package com.practice.emp.ejb;

import java.io.Serializable;
import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.transaction.Transactional;

import com.practice.emp.models.EmployeePojo;

@Stateless
public class SomeBean implements Serializable {
    private static final long serialVersionUID = 1L;

    @PersistenceContext(name = "assignment4-PU")
    protected EntityManager em;

    /**
     * Default constructor.
     */
    public SomeBean() {
    }

    public List<EmployeePojo> findAllEmployees() {

        TypedQuery<EmployeePojo> allEmpsQuery = em.createQuery("SELECT e FROM Employee e", EmployeePojo.class);
        return allEmpsQuery.getResultList();
    }

    @Transactional
    public EmployeePojo persistEmployee(EmployeePojo employee) {
        em.persist(employee);
        return employee;
    }

    public EmployeePojo findEmployeeByPrimaryKey(int empPK) {
        return em.find(EmployeePojo.class, empPK);
    }

    @Transactional
    public EmployeePojo mergeEmployee(EmployeePojo employeeWithUpdates) {
        EmployeePojo employeeToBeUpdated = findEmployeeByPrimaryKey(employeeWithUpdates.getId());
        if (employeeToBeUpdated != null) {
            EmployeePojo mergedEmployeePojo = em.merge(employeeWithUpdates);
            em.flush();
            return mergedEmployeePojo;
        }
        return employeeToBeUpdated;
    }

    @Transactional
    public void removeEmployee(int employeeId) {
        EmployeePojo employee = findEmployeeByPrimaryKey(employeeId);
        
        em.remove(employee);
        
    }

}
