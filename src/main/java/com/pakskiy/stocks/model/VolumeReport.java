package com.pakskiy.stocks.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.Data;

@Data
@Entity
public class VolumeReport {
    @Id
    private Integer id;
    private String companyName;
    private String volume;

    @Override
    public String toString() {
        return id + " | " + companyName + " | " + volume;
    }
}
