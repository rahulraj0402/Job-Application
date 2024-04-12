package com.rahul.jobMicroservice.job.Impl;


import com.rahul.jobMicroservice.job.Job;
import com.rahul.jobMicroservice.job.JobRepository;
import com.rahul.jobMicroservice.job.JobService;
import com.rahul.jobMicroservice.job.dto.JobWithCompanyDTO;
import com.rahul.jobMicroservice.job.external.Company;
import com.rahul.jobMicroservice.job.mapper.JobMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;


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


        return jobs.stream().map(this::convertToDTO)
                .collect(Collectors.toList());

//      [ here above we have used streams for each job we are finding the corresponding companies and collecting as list . ] ;
//      [ for each job convertToDTO function is invoking
    }


    private JobWithCompanyDTO convertToDTO(Job job){

//            JobWithCompanyDTO jobWithCompanyDTO = new JobWithCompanyDTO();
//            jobWithCompanyDTO.setJob(job);  // this will not work since we have modified the DTO |
        // for above we can create a mapper class for setting the jobs
       // RestTemplate restTemplate = new RestTemplate();

            Company company = restTemplate.
                    getForObject("http://COMPANY-SERVICE:8081/companies/"+job.getCompanyId(), Company.class);

            JobWithCompanyDTO jobWithCompanyDTO = JobMapper.mapWithJobWithCompanyDTO(job , company);


            jobWithCompanyDTO.setCompany(company);

            return jobWithCompanyDTO;


    }

    @Override
    public void createJob(Job job ) {
        job.setId(nextId++);
        jobRepository.save(job);
    }

    @Override
    public JobWithCompanyDTO getJobByJobId(Long id) {

       Job job =  jobRepository.findById(id).orElse(null);
       return convertToDTO(job);
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













