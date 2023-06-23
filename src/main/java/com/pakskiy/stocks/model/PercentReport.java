package com.pakskiy.stocks.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.Data;

@Data
@Entity
public class PercentReport {
    @Id
    private Integer id;
    private String companyName;
    private String percent;

    @Override
    public String toString() {
        return id + " | " + companyName + " | " + percent;
    }
}
