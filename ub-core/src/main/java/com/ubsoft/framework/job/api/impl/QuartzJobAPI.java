package com.ubsoft.framework.job.api.impl;

import com.ubsoft.framework.core.context.AppContext;
import com.ubsoft.framework.core.util.StringUtil;
import com.ubsoft.framework.job.GeneralJob;
import com.ubsoft.framework.job.api.IJobAPI;
import com.ubsoft.framework.job.entity.Job;
import org.quartz.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Date;
import java.util.List;

public class QuartzJobAPI  implements IJobAPI {

    protected final Logger logger = LoggerFactory.getLogger(getClass());

    private Scheduler getScheduler(){
        Scheduler  scheduler=AppContext.getBean(Scheduler.class);

        return scheduler;
    }
    //rescheduleJob
    @Override
    public void addJob(Job job) {
        try {
            String jobName = job.getJobKey();
            String jobGroup = job.getJobKey();
            String cronExpression = job.getCron();
            String description = job.getJobName();
            // String className = GeneralJob.class.getCanonicalName();
            Date startDate = job.getStartTime();
            Date endDate = job.getEndTime();
            if (StringUtil.isEmpty(jobName)) {
                throw new RuntimeException("job's name is empty!");
            }
            if (getScheduler().checkExists(TriggerKey.triggerKey(jobName, jobGroup))) {

                deleteJob(job);
                //throw new RuntimeException("Job：" + jobName + " 已存在。");
            }
            if (StringUtil.isEmpty(jobGroup)) {
                jobGroup = Scheduler.DEFAULT_GROUP;
            }
            // JOB状态
            // final Class<? extends Job> clazz = (Class<? extends Job>) Class.forName(className);
            // 构建job信息
            JobDetail jobDetail = JobBuilder.newJob(GeneralJob.class).withIdentity(jobName, jobGroup).withDescription(description)
                    .build();
            //放入参数，运行时的方法可以获取
            jobDetail.getJobDataMap().put("args", job.getArgs());

            // 表达式调度构建器
            CronScheduleBuilder scheduleBuilder = CronScheduleBuilder.cronSchedule(cronExpression).withMisfireHandlingInstructionDoNothing();
            CronTrigger trigger = null;
            // 按新的cronExpression表达式构建一个新的trigger
            if (startDate != null && endDate != null) {
                trigger = TriggerBuilder.newTrigger().startAt(startDate).endAt(startDate).withIdentity(jobName,
                        jobGroup).withSchedule(scheduleBuilder).withDescription(description).build();

            } else if (startDate != null && endDate == null) {
                trigger = TriggerBuilder.newTrigger().startAt(startDate).withIdentity(jobName, jobGroup).withSchedule(
                        scheduleBuilder).withDescription(description).build();
            } else if (startDate == null && endDate == null) {
                trigger = TriggerBuilder.newTrigger().withIdentity(jobName, jobGroup).withSchedule(scheduleBuilder)
                        .withDescription(description).build();
            }

            // 绑定调度器
            getScheduler().scheduleJob(jobDetail, trigger);
        }  catch (Exception e) {

            throw new RuntimeException(e);
        }
    }

    @Override
    public void deleteJob(Job job) {
        try {
            String jobGroup = job.getJobKey();
            String jobName = job.getJobKey();
            JobKey jobKey = JobKey.jobKey(jobName, jobGroup);
            TriggerKey triggerKey = TriggerKey.triggerKey(jobName, jobName);
            if (getScheduler().checkExists(jobKey)) {
                // 考虑立即运行会产生simple型随机的触发器，以Jobkey作为参数删除
                getScheduler().pauseTrigger(triggerKey);// 停止触发器
                getScheduler().unscheduleJob(triggerKey);// 移除触发器


                getScheduler().deleteJob(jobKey);
            } else {
                throw new RuntimeException("该Job不存在");
            }

        } catch (Exception e) {
            throw new RuntimeException("Job:"+job.getJobKey()+"不存在");
        }
    }

    public void runJob(Job job) {
        String jobGroup = job.getJobKey();
        String jobName = job.getJobKey();
        JobKey key = new JobKey(jobName, jobGroup);
        try {
            getScheduler().triggerJob(key);
        } catch (SchedulerException e) {

            throw new RuntimeException(e);
        }
    }
    @Override
    public void pauseJob(Job job) {
        try {
            String jobGroup = job.getJobKey();
            String jobName = job.getJobKey();
            JobKey key=new JobKey(jobName,jobGroup);
            TriggerKey triggerKey = TriggerKey.triggerKey(jobName, jobGroup);
            if (getScheduler().checkExists(key)) {
               //getScheduler().pauseTrigger(triggerKey);// 停止触发器
                //getScheduler().unscheduleJob(triggerKey);// 移除触发器
                getScheduler().pauseJob(key);
            } else {
                throw new RuntimeException("job:"+jobName+"不存在");
            }
        } catch (SchedulerException e) {

            throw new RuntimeException(e);
        }

    }
    @Override
    public void resumeJob(Job job) {
        try {
            String jobGroup = job.getJobKey();
            String jobName = job.getJobKey();
            JobKey jobKey = JobKey.jobKey(jobName, jobGroup);
            TriggerKey triggerKey = TriggerKey.triggerKey(jobName, jobGroup);
            if (getScheduler().checkExists(jobKey)) {
                //getScheduler().resumeTrigger(triggerKey);
                getScheduler().resumeJob(jobKey);
            } else {
                throw new RuntimeException("Job:"+job.getJobKey()+"不存在");
            }
        } catch (SchedulerException e) {
            throw new RuntimeException(e);
        }

    }

    @Override
    public void pauseAll() {
        try {
            getScheduler().pauseAll();
        } catch (Exception e) {
            logger.error(e.getMessage());
        }
    }
    public void resumeAll() {
        try {
            getScheduler().resumeAll();
        } catch (Exception e) {
            logger.error(e.getMessage());
        }
    }

    @Override
    public void start() {
        try {
            if(!getScheduler().isStarted()){
                getScheduler().start();
            }

        } catch (Exception e) {
            logger.error(e.getMessage());
        }

    }
    @Override
    public void shutdown() {
        try {
            if(!getScheduler().isShutdown()){
                getScheduler().shutdown();
            }

        } catch (Exception e) {
            logger.error(e.getMessage());
        }
    }
    @Override
    public List<Job> getAll() {
       // getScheduler().getJobGroupNames();
        return null;
    }
}
