package com.rahul.jobMicroservice.job;

import com.rahul.jobMicroservice.job.dto.JobWithCompanyDTO;

import java.util.List;

public interface JobService {
    List<JobWithCompanyDTO> findAll();
    void createJob(Job job);

    JobWithCompanyDTO getJobByJobId(Long id);

    boolean deleteById(Long id);

    boolean updateJob(Long id, Job updatedJob);
}
