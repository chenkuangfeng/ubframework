package com.ubsoft.framework.job.api;

import com.ubsoft.framework.core.context.AppContext;
import com.ubsoft.framework.job.entity.Job;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest

class IJobTest {


    private Job getJob(){
        Job job= new Job();
        job.setJobKey("jobKey1");
        job.setJobName("jobName1");
        job.setCron("0/10 * * * * ? *");
        return job;
    }
    @Test
    void addJob() {
        IJobAPI jobApi= (IJobAPI)AppContext.getBean("jobAPI");
        Job job=  getJob();
        System.out.println("开始添加并执行");
        jobApi.deleteJob(job);
        jobApi.addJob(job);
        //jobApi.start();
        try {
            Thread.sleep(Long.parseLong("10000"));
        }catch(Exception ex){

        }

        System.out.println("停止");
        jobApi.pauseJob(job);
        try {
            Thread.sleep(Long.parseLong("10000"));
        }catch(Exception ex){


        }
        System.out.println("run");
       // jobApi.pauseJob(job);
        jobApi.runJob(job);
        try {
            Thread.sleep(Long.parseLong("10000"));
        }catch(Exception ex){

        }
        System.out.println("启动");
        jobApi.resumeJob(job);
        jobApi.runJob(job);
        try {
            Thread.sleep(Long.parseLong("1000000"));
        }catch(Exception ex){

        }
    }


}