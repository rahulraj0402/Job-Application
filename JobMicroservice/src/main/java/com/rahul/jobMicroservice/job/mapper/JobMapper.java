package com.rahul.jobMicroservice.job.mapper;

import com.rahul.jobMicroservice.job.Job;
import com.rahul.jobMicroservice.job.dto.JobDTO;
import com.rahul.jobMicroservice.job.external.Company;
import com.rahul.jobMicroservice.job.external.Review;

import java.util.List;

public class JobMapper {

    // this class helps us to map the job and company to jobsWithCompanyDTO

    public static JobDTO mapWithJobWithCompanyDTO(Job job, Company company , List<Review> reviews){

        JobDTO jobDTO = new JobDTO();
        jobDTO.setId(job.getId());
        jobDTO.setTitle(job.getTitle());
        jobDTO.setDescription(job.getDescription());
        jobDTO.setLocation(job.getLocation());
        jobDTO.setMaxSalary(job.getMaxSalary());
        jobDTO.setMinSalary(job.getMinSalary());

//       [ here we are setting the company for better JSON view ] or to avoide the nested json  ]
        jobDTO.setCompany(company);

//       [ setting the review ]
        jobDTO.setReview(reviews);


        return jobDTO;
    }
}

