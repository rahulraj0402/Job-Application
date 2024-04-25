package com.rahul.jobMicroservice.job.Impl;

import com.rahul.jobMicroservice.job.Job;
import com.rahul.jobMicroservice.job.JobRepository;
import com.rahul.jobMicroservice.job.JobService;
import com.rahul.jobMicroservice.job.clients.CompanyClient;
import com.rahul.jobMicroservice.job.clients.ReviewClient;
import com.rahul.jobMicroservice.job.dto.JobDTO;
import com.rahul.jobMicroservice.job.external.Company;
import com.rahul.jobMicroservice.job.external.Review;
import com.rahul.jobMicroservice.job.mapper.JobMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
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

    @Autowired
    private CompanyClient companyClient;

    @Autowired
    private ReviewClient reviewClient;


    private long nextId  = 1L ;


    public JobServiceImpl(JobRepository jobRepository) {
        this.jobRepository = jobRepository;
    }

    @Override
    public List<JobDTO> findAll() {

        List<Job> jobs = jobRepository.findAll();
        List<JobDTO> jobDTOS = new ArrayList<>();


        return jobs.stream().map(this::convertToDTO)
                .collect(Collectors.toList());

//      [ here above we have used streams for each job we are finding the corresponding companies and collecting as list . ] ;
//      [ for each job convertToDTO function is invoking
    }


    private JobDTO convertToDTO(Job job){

// http://localhost:8761/eureka/
// review-service
        // [ .getForObject | this we mainly use when ae get a single object | a single object
        // [ .exchange | thus us is mainly used for the generic type | here the reviews are list
//

        //  [ this below lines are before implementing the openFeing so here we earlier using the restTempplate ]
//        Company company = restTemplate.getForObject("http://COMPANY-SERVICE:8081/companies/"+job.getCompanyId(), Company.class);


//      [ after implementing the openFeing just calling the meathods .getCompany ]
        Company company = companyClient.getCompany(job.getCompanyId());
//       [ after implementing the openFeign ]
        List<Review> reviews = reviewClient.getReviews(job.getCompanyId());

//  [ before implementing the openFeign ]
//            ResponseEntity<List<Review>> reviewResponseEntity =  restTemplate.exchange(
//                    "http://REVIEW-SERVICE:8083/reviews?companyId=" + job.getCompanyId(),
//                    HttpMethod.GET,
//                    null,
//                    new ParameterizedTypeReference<List<Review>>() {
//                    });
//            List<Review> reviews = reviewResponseEntity.getBody();

            JobDTO jobDTO = JobMapper.mapWithJobWithCompanyDTO(job , company , reviews);




            return jobDTO;


    }

    @Override
    public void createJob(Job job ) {
        job.setId(nextId++);
        jobRepository.save(job);
    }

    @Override
    public JobDTO getJobByJobId(Long id) {

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


 // hey
}













