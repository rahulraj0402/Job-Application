package com.rahul.companyMicroService.company;

import java.util.List;

public interface CompanyService {
    List<Company> getAllCompanies ();
    boolean updateCompany(Company company , Long id);
    void createCompany(Company company);

    Company getCompanyById(Long id);
    boolean deleteCompanyById(Long id);

    List<Company> findByName(String name);

}
