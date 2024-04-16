package com.rahul.jobMicroservice.job;

import com.rahul.jobMicroservice.job.dto.JobDTO;

import java.util.List;

public interface JobService {
    List<JobDTO> findAll();
    void createJob(Job job);

    JobDTO getJobByJobId(Long id);

    boolean deleteById(Long id);

    boolean updateJob(Long id, Job updatedJob);
}
