package com.pakskiy.stocks.model;

import jakarta.persistence.*;
import lombok.*;

import java.time.ZonedDateTime;

@Entity
@Builder(toBuilder = true)
@AllArgsConstructor(access = AccessLevel.PACKAGE)
@NoArgsConstructor(access = AccessLevel.PACKAGE)
@Getter
@Setter
@Table(name = "quotes")
public class Quote {
    @GeneratedValue(strategy = GenerationType.IDENTITY, generator = "id_quote_sequence")
    @SequenceGenerator(name = "id_quote_sequence", allocationSize = 1)
    @Id
    @Column(name = "id")
    private Long id;
    @Column(name = "company_id")
    private String companyId;
    @Column(name = "volume")
    private Long volume;
    @Column(name = "previous_volume")
    private Long previousVolume;
    @Column(name = "latest_price")
    private Double latestPrice;
    @Column(name = "previous_latest_price")
    private Double previousLatestPrice;
    @Column(name = "created_at")
    private ZonedDateTime created_at = ZonedDateTime.now();
    @Column(name = "updated_at")
    private ZonedDateTime updated_at = ZonedDateTime.now();
}