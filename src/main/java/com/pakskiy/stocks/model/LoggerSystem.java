package com.pakskiy.stocks.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.ZonedDateTime;

@Entity
@Getter
@Setter
@Table(name = "logger_system")
public class LoggerSystem {
    @GeneratedValue(strategy = GenerationType.IDENTITY, generator = "id_log_sequence")
    @SequenceGenerator(name = "id_log_sequence", allocationSize = 1)
    @Id
    private Long id;
    private String symbolId;
    private String data;
    private ZonedDateTime createdAt = ZonedDateTime.now();
}