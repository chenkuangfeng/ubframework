package com.ubsoft.framework.core.cache;

import com.ubsoft.framework.core.context.AppContext;
import com.ubsoft.framework.job.api.IJobAPI;
import com.ubsoft.framework.job.entity.Job;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest

class ICacheTest {


    @Test
    void get() {
    }

    @Test
    void testGet() {
    }
    private Job createJob(){
        Job job= new Job();
        job.setJobKey("jobKey1");
        job.setJobName("jobName1");
        job.setCron("0/2 * * * * ? *");

        return job;

    }
    @Test
    void put() {
        IJobAPI jobApi= (IJobAPI)AppContext.getBean("jobAPI");
        Job job=  createJob();
        jobApi.addJob(job);
        AppContext.getCache().put("fff","fffff");
    }

    @Test
    void testPut() {
    }

    @Test
    void gets() {
    }

    @Test
    void testGets() {
    }

    @Test
    void remove() {
    }

    @Test
    void testRemove() {
    }

    @Test
    void clean() {
    }

    @Test
    void expire() {
    }
}