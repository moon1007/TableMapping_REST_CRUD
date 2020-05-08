package com.practice.emp.utils;

import java.util.HashMap;
import java.util.Map;
import static com.practice.emp.utils.MyConstants.*;

import javax.security.enterprise.identitystore.Pbkdf2PasswordHash;

import org.glassfish.soteria.identitystores.hash.Pbkdf2PasswordHashImpl;

public class PBKDF2HashGenerator {

    public static void main(String[] args) {

        Pbkdf2PasswordHash pbAndjPasswordHash = new Pbkdf2PasswordHashImpl();

        Map<String, String> pbAndjProperties = new HashMap<>();
        pbAndjProperties.put(PROPERTY_ALGORITHM, DEFAULT_PROPERTY_ALGORITHM);
        pbAndjProperties.put(PROPERTY_ITERATIONS, DEFAULT_PROPERTY_ITERATIONS);
        pbAndjProperties.put(PROPERTY_SALTSIZE, DEFAULT_SALT_SIZE);
        pbAndjProperties.put(PROPERTY_KEYSIZE, DEFAULT_KEY_SIZE);
        pbAndjPasswordHash.initialize(pbAndjProperties);
        String pwHash = pbAndjPasswordHash.generate(args[0].toCharArray());
        System.out.printf("Hash for %s is %s%n", args[0], pwHash);
    }
}