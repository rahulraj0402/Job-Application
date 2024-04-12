package com.rahul.jobMicroservice.job.mapper;

import com.rahul.jobMicroservice.job.Job;
import com.rahul.jobMicroservice.job.dto.JobWithCompanyDTO;
import com.rahul.jobMicroservice.job.external.Company;

public class JobMapper {

    // this class helps us to map the job and company to jobsWithCompanyDTO

    public static JobWithCompanyDTO mapWithJobWithCompanyDTO(Job job, Company company){

        JobWithCompanyDTO jobWithCompanyDTO = new JobWithCompanyDTO();
        jobWithCompanyDTO.setId(job.getId());
        jobWithCompanyDTO.setTitle(job.getTitle());
        jobWithCompanyDTO.setDescription(job.getDescription());
        jobWithCompanyDTO.setLocation(job.getLocation());
        jobWithCompanyDTO.setMaxSalary(job.getMaxSalary());
        jobWithCompanyDTO.setMinSalary(job.getMinSalary());
        jobWithCompanyDTO.setCompany(company);

        return jobWithCompanyDTO;
    }
}
