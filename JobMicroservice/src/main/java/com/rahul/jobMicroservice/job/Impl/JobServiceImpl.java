package com.rahul.jobMicroservice.job.Impl;


import com.rahul.jobMicroservice.job.Job;
import com.rahul.jobMicroservice.job.JobRepository;
import com.rahul.jobMicroservice.job.JobService;
import com.rahul.jobMicroservice.job.dto.JobWithCompanyDTO;
import com.rahul.jobMicroservice.job.external.Company;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;
import java.util.ListIterator;
import java.util.Optional;


@Service
public class JobServiceImpl implements JobService {

   // private List<Job> jobs = new ArrayList<>();



    @Autowired
    JobRepository jobRepository;


    @Autowired
    RestTemplate restTemplate;


    private long nextId  = 1L ;


    public JobServiceImpl(JobRepository jobRepository) {
        this.jobRepository = jobRepository;
    }

    @Override
    public List<JobWithCompanyDTO> findAll() {

        List<Job> jobs = jobRepository.findAll();

        List<JobWithCompanyDTO> jobWithCompanyDTOs = new ArrayList<>();

        RestTemplate restTemplate = new RestTemplate();

        // for each job object we are going to pass the comany object as well

        for (Job job : jobs){
            JobWithCompanyDTO jobWithCompanyDTO = new JobWithCompanyDTO();
            jobWithCompanyDTO.setJob(job);
            Company company = restTemplate.
                    getForObject("http://COMPANY-SERVICE:8081/companies/"+job.getCompanyId(), Company.class);
            jobWithCompanyDTO.setCompany(company);
            jobWithCompanyDTOs.add(jobWithCompanyDTO);
        }



//        Company company = restTemplate.getForObject("http://localhost:8081/companies/1" , Company.class);
//        System.out.println("COMPANY : " + company.getName());
//        System.out.println("COMPANY : " + company.getId());
//        System.out.println("COMPANY : " + company.getDescription());


//        return jobRepository.findAll();


        return jobWithCompanyDTOs;
    }

    @Override
    public void createJob(Job job ) {
        job.setId(nextId++);
        jobRepository.save(job);
    }

    @Override
    public Job getJobByJobId(Long id) {
       return jobRepository.findById(id).orElse(null);
    }


    @Override
    public boolean deleteById(Long id) {
        Optional<Job> jobOptional = jobRepository.findById(id);
        if (jobOptional.isPresent()) {
            try {
                jobRepository.deleteById(id);
                return true;
            } catch (Exception e) {
                // Handle any potential exceptions
                return false;
            }
        } else {
            // Entity with the given ID doesn't exist, so deletion is unsuccessful
            return false;
        }
    }

    @Override
    public boolean updateJob(Long id, Job updatedJob) {

      Optional<Job> jobOptional = jobRepository.findById(id);
      if (jobOptional.isPresent()){
          Job job = jobOptional.get();
          job.setTitle(updatedJob.getTitle());
          job.setDescription(updatedJob.getDescription());
          job.setLocation(updatedJob.getLocation());
          job.setMaxSalary(updatedJob.getMaxSalary());
          job.setMinSalary(updatedJob.getMinSalary());
          jobRepository.save(job);
          return true;
      }

      return false;

    }



}













