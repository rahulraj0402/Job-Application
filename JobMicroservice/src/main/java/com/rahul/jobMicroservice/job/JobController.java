package com.rahul.jobMicroservice.job;

import com.rahul.jobMicroservice.job.dto.JobWithCompanyDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/jobs")
public class JobController {

    @Autowired
    private JobService jobService;

    public JobController(JobService jobService) {
        this.jobService = jobService;
    }

    // end point for returning all the jobs available
//    JobWithCompanyDTO
    @GetMapping
    public ResponseEntity<List<JobWithCompanyDTO>> findAall(){

        return new ResponseEntity<>(jobService.findAll() , HttpStatus.OK);
    }
    //  for posting the jobs
    @PostMapping
    public ResponseEntity<String> createJob(@RequestBody Job job){

       jobService.createJob(job);

        return new ResponseEntity<>("added successfully !! " ,HttpStatus.CREATED);
    }


    @GetMapping("/{id}")
    public ResponseEntity<JobWithCompanyDTO> getJobById(@PathVariable long id){

        JobWithCompanyDTO jobWithCompanyDTO = jobService.getJobByJobId(id);
        if (jobWithCompanyDTO != null){
            return new ResponseEntity<>(jobWithCompanyDTO , HttpStatus.OK);
        }

        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }



    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteJobById(@PathVariable Long id){
        boolean deleted =  jobService.deleteById(id);
      if (deleted){
          return new ResponseEntity<>("deleted " , HttpStatus.OK);
      }
      return new ResponseEntity<>("Id entered is not found" , HttpStatus.NOT_FOUND);
    }



    @PutMapping("/{id}")
    public ResponseEntity<String> updateJob(@PathVariable Long id , @RequestBody Job updatedJob){

        boolean updated = jobService.updateJob(id , updatedJob);
        if (updated){
            return new ResponseEntity<>("job updated" , HttpStatus.OK);
        }

        return new ResponseEntity<>("Job id is not found , so cant update  : - ( " , HttpStatus.NOT_FOUND);
    }
}
