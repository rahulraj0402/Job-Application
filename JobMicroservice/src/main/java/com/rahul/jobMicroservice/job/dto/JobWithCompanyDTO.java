package com.rahul.jobMicroservice.job.dto;

import com.rahul.jobMicroservice.job.Job;
import com.rahul.jobMicroservice.job.external.Company;
import lombok.Getter;
import lombok.Setter;


@Getter
@Setter
public class JobWithCompanyDTO {
// this will be shown to the users

    private Job job;
    private Company company;



}
