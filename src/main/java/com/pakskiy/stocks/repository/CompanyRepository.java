package com.pakskiy.stocks.repository;

import com.pakskiy.stocks.model.CompanyEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
@Repository
public interface CompanyRepository extends JpaRepository<CompanyEntity, String> {}
