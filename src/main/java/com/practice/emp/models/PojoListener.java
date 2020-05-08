package com.practice.emp.models;

import java.time.LocalDateTime;

import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;

public class PojoListener {
    @PrePersist
    public void onPersist(PojoBase p) {
        LocalDateTime now = LocalDateTime.now();
        p.setCreated(now);
        p.setUpdated(now);
    }

    /**
     * @param emp set timestamp for updated date
     */
    @PreUpdate
    public void onUpdate(PojoBase p) {
        LocalDateTime now = LocalDateTime.now();
        p.setUpdated(now);
    }
}