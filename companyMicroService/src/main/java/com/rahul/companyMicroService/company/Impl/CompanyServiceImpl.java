package com.rahul.companyMicroService.company.Impl;


import com.netflix.discovery.converters.Auto;
import com.rahul.companyMicroService.company.Company;
import com.rahul.companyMicroService.company.CompanyService;
import com.rahul.companyMicroService.company.CompanyRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;



import java.util.List;
import java.util.Optional;

@Service
public class CompanyServiceImpl implements CompanyService {

    @Autowired
    private CompanyRepository companyRepository;


    public CompanyServiceImpl(CompanyRepository companyRepository) {
        this.companyRepository = companyRepository;
    }


    @Override
    public List<Company> getAllCompanies() {
        return companyRepository.findAll();
    }

    @Override
    public boolean updateCompany(Company updatedCompany , Long id) {
        Optional<Company> companyOptional = companyRepository.findById(id);
        if (companyOptional.isPresent()){
            Company company = companyOptional.get();
            company.setDescription(updatedCompany.getDescription());
            company.setName(updatedCompany.getName());
            companyRepository.save(company);
            return true;
        }

        return false;
    }

    @Override
    public void createCompany(Company company) {
      companyRepository.save(company);
    }

    @Override
    public Company getCompanyById(Long id) {

        return companyRepository.findById(id).orElse(null);
    }

    @Override
    public boolean deleteCompanyById(Long id) {
        Optional<Company> companyOptional = companyRepository.findById(id);
        if (companyOptional.isPresent()){
            try {
                companyRepository.deleteById(id);
                return true;
            }catch (Exception e){
                return false;
            }
        }else {
            return false;
        }
    }

    @Override
    public List<Company> findByName(String name) {
        return companyRepository.findByName(name);
    }

}