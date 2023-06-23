package com.pakskiy.stocks.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;

import java.time.ZonedDateTime;

@Entity
@Table(name = "quotes")
@Data
public class Quote {
    @Id
    @Column(name = "symbol")
    private String symbol;
    @Column(name = "volume")
    private Long volume;
    @Column(name = "previous_volume")
    private Long previousVolume;
    @Column(name = "latest_price")
    private Double latestPrice;
    @Column(name = "previous_latest_price")
    private Double previousLatestPrice;
    @Column(name = "company_name")
    private String companyName;
    @Column(name = "created_at")
    private ZonedDateTime created_at = ZonedDateTime.now();
    @Column(name = "updated_at")
    private ZonedDateTime updated_at = ZonedDateTime.now();
}