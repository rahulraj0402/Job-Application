package com.rahul.jobMicroservice.job;

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
    @GetMapping
    public ResponseEntity<List<Job>> findAall(){

        return new ResponseEntity<>(jobService.findAll() , HttpStatus.CREATED);
    }
    //  for posting the jobs
    @PostMapping
    public ResponseEntity<String> createJob(@RequestBody Job job){

       jobService.createJob(job);

        return new ResponseEntity<>("added successfully !! " ,HttpStatus.CREATED);
    }
    @GetMapping("/{id}")
    public ResponseEntity<Job> getJobById(@PathVariable long id){

        Job job = jobService.getJobByJobId(id);
        if (job != null){
            return new ResponseEntity<>(job , HttpStatus.OK);
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
