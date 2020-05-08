package com.practice.emp.security;

import static com.practice.emp.utils.MyConstants.PU_NAME;
import static java.util.Collections.emptySet;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import javax.ejb.Singleton;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.security.enterprise.identitystore.Pbkdf2PasswordHash;

import com.practice.emp.models.EmployeePojo;
import com.practice.emp.models.SecurityRole;
import com.practice.emp.models.SecurityUser;

@Singleton
public class CustomIdentityStoreJPAHelper {

    @PersistenceContext(name=PU_NAME)
    protected EntityManager em;

    @Inject
    protected Pbkdf2PasswordHash pbAndjPasswordHash;

    public SecurityUser findUserByName(String username) {
        SecurityUser user = null;
        user = em.find(SecurityUser.class, 1);
        
        System.out.println("User name is : "+user.getUsername());

        return user;
    }

    public Set<String> findRoleNamesForUser(String username) {
        Set<String> rolenames = emptySet();

        SecurityRole copyOfSecurityRole = em.find(SecurityRole.class, 1);
        String staticValue = null;
        staticValue = copyOfSecurityRole.getRoleName();
        rolenames.add(staticValue);
        
        System.out.println("rolename is :" + rolenames);
    
        return rolenames;
    }

    @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public void saveSecurityUser(SecurityUser user) {
        em.persist(user);
    }

    @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public void saveSecurityRole(SecurityRole role) {
        em.persist(role);
    }
}