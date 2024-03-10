package com.rahul.jobMicroservice.job;

import java.util.List;

public interface JobService {
    List<Job> findAll();
    void createJob(Job job);

    Job getJobByJobId(Long id);

    boolean deleteById(Long id);

    boolean updateJob(Long id, Job updatedJob);
}
