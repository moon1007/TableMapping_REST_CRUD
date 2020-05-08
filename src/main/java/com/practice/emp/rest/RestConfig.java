package com.practice.emp.rest;

import static com.practice.emp.utils.MyConstants.ADMIN_ROLE;
import static com.practice.emp.utils.MyConstants.APPLICATION_API_VERSION;
import static com.practice.emp.utils.MyConstants.USER_ROLE;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

import javax.annotation.security.DeclareRoles;
import javax.ws.rs.ApplicationPath;
import javax.ws.rs.core.Application;

@ApplicationPath(APPLICATION_API_VERSION)
@DeclareRoles({USER_ROLE, ADMIN_ROLE})
public class RestConfig extends Application {
   
    @Override
    public Map<String, Object> getProperties() {
        Map<String, Object> props = new HashMap<>();
        props.put("jersey.config.jsonFeature", "JacksonFeature");
        return props;
    }
}