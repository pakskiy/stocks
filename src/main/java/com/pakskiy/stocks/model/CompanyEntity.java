package com.pakskiy.stocks.model;

import jakarta.persistence.*;
import lombok.*;

import java.time.ZonedDateTime;
@Entity
@Setter
@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "companies")
public class CompanyEntity {
    @Id
    @Column(name = "id")
    private String id;
    @Column(name = "name")
    private String name;
    @Column(name = "created_at")
    private ZonedDateTime created_at = ZonedDateTime.now();
}
