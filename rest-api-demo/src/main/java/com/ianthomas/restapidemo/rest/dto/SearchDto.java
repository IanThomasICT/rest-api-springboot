package com.ianthomas.restapidemo.rest.dto;

import java.io.Serializable;

public class SearchDto implements Serializable {
    private Long hitCount;
    private Object hits;

    public SearchDto(Long hitCount, Object hits) {
        this.hitCount = hitCount;
        this.hits = hits;
    }

    public Long getHitCount() {
        return hitCount;
    }

    public void setHitCount(Long hitCount) {
        this.hitCount = hitCount;
    }

    public Object getHits() {
        return hits;
    }

    public void setHits(Object hits) {
        this.hits = hits;
    }
}
