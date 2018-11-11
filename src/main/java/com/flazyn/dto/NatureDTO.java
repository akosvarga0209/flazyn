package com.flazyn.dto;

public class NatureDTO {
    private Long id;
    private String description;

    //Spring-nek kell az üres konstruktor, különben nem tudja beparsolni a kliensről jövő json-t
    public NatureDTO() {
    }

    public NatureDTO(long id, String description) {
        this.id = id;
        this.description = description;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
