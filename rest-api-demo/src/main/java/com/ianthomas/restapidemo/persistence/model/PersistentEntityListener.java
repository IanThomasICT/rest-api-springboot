package com.ianthomas.restapidemo.persistence.model;

import javax.persistence.PrePersist;
import java.util.UUID;

public class PersistentEntityListener {
    @PrePersist
    public void prePersist(PersistentEntity e) {
        if (e.getEntityId() == null || e.getEntityId().trim().isEmpty()) {
            e.setEntityId(UUID.randomUUID().toString());
        }
    }
}
