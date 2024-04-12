package com.rahul.jobMicroservice.job.dto;

import com.rahul.jobMicroservice.job.Job;
import com.rahul.jobMicroservice.job.external.Company;
import lombok.Getter;
import lombok.Setter;


@Getter
@Setter
public class JobWithCompanyDTO {

    private Long id;
    private String title;
    private String description;
    private String minSalary;
    private String maxSalary;
    private String location;

    private Company company;



}
