package com.rahul.companyMicroService.company;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CompanyRepository extends JpaRepository<Company , Long> {
    List<Company> findByName(String name);
}
