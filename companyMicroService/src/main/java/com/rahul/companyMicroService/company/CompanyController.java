package com.rahul.companyMicroService.company;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("companies")
public class CompanyController {
    private CompanyService companyService;

    public CompanyController(CompanyService companyService) {
        this.companyService = companyService;
    }

    @GetMapping
    public ResponseEntity<List<Company>> getAllCompanies(){
         List<Company> list =companyService.getAllCompanies();
         if (!list.isEmpty()){
             return new ResponseEntity<>(list , HttpStatus.OK);
         }else {
             return new  ResponseEntity<>(HttpStatus.NOT_FOUND);
         }

    }

    @PutMapping("/{id}")
    public ResponseEntity<String> updateCompany(@PathVariable Long id , @RequestBody Company company){

        boolean updated = companyService.updateCompany(company , id);
        if (updated){
            return new ResponseEntity<>("Updated" , HttpStatus.OK);
        }
        return new ResponseEntity<>("The id you have entered is not fount" , HttpStatus.NOT_FOUND);
    }

    @PostMapping
    public ResponseEntity<String> createCompany(@RequestBody Company company){

        companyService.createCompany(company);
        return new ResponseEntity<>("Company added" , HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Company> getCompanyById(@PathVariable Long id){
      Company company = companyService.getCompanyById(id);
      if (company != null){
          return new ResponseEntity<>(company , HttpStatus.OK);
      }else{
          return new ResponseEntity<>( HttpStatus.NOT_FOUND);
      }

    }


    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteCompanyById(@PathVariable Long id){
        boolean isDeleted = companyService.deleteCompanyById(id);
        if (isDeleted){
            return new ResponseEntity<>("Company Deleted", HttpStatus.OK);
        }else{
            return new ResponseEntity<>("Company not found" , HttpStatus.NOT_FOUND);
        }
    }


//    @GetMapping("/{name}")
//    public ResponseEntity<List<Company>> findByName(@PathVariable String name){
//        List<Company> companies = companyService.findByName(name);
//        if (companies != null){
//            return //   make this function 5 : 04
//        }
//    }



}
