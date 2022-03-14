package com.ianthomas.restapidemo.rest.dto;

import java.io.Serializable;

public class HitDto implements Serializable {
    private String entityId;        // UUID for entity
    private String id;              // common id
    private String field;
    private String name;            // name identifier for search result (company name, employee full name, etc.)
    private Float searchScore;

    public HitDto(String entityId, String id, String field, String name) {
        this.entityId = entityId;
        this.id = id;
        this.field = field;
        this.name = name;
    }

    public String getEntityId() {
        return entityId;
    }

    public void setEntityId(String entityId) {
        this.entityId = entityId;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getField() {
        return field;
    }

    public void setField(String field) {
        this.field = field;
    }

    public Float getSearchScore() {
        return searchScore;
    }

    public void setSearchScore(Float searchScore) {
        this.searchScore = searchScore;
    }
}
