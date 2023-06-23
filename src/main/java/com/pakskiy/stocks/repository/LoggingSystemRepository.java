package com.pakskiy.stocks.repository;

import com.pakskiy.stocks.model.LoggerSystem;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LoggingSystemRepository extends JpaRepository<LoggerSystem, Long> {}
