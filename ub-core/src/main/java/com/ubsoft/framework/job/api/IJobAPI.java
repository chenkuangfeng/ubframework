package com.ubsoft.framework.job.api;

import com.ubsoft.framework.job.entity.Job;

import java.util.List;

public interface IJobAPI {

    void addJob(Job job);
    void deleteJob(Job job);
    void runJob(Job job);
    void pauseJob(Job job);
    void resumeJob(Job job);
    void pauseAll();
    void resumeAll();
    void start();
    void shutdown();
    List<Job> getAll();
}
