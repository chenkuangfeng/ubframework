package com.ubsoft.framework.job.api.impl;

import com.dangdang.ddframe.job.lite.lifecycle.api.*;
import com.dangdang.ddframe.job.lite.lifecycle.domain.JobBriefInfo;
import com.dangdang.ddframe.job.reg.zookeeper.ZookeeperRegistryCenter;
import com.google.common.base.Optional;
import com.ubsoft.framework.core.conf.AppConfig;
import com.ubsoft.framework.core.context.AppContext;
import com.ubsoft.framework.job.api.IJobAPI;
import com.ubsoft.framework.job.entity.Job;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Collection;
import java.util.List;

public class ElasticJobAPI implements IJobAPI {
    protected final Logger logger = LoggerFactory.getLogger(getClass());
    private AppConfig appConfig = AppContext.getAppConfig();
    private JobSettingsAPI jobSettingsAPI;
    private JobOperateAPI jobOperateAPI;
    private ShardingOperateAPI shardingOperateAPI;
    private JobStatisticsAPI jobStatisticsAPI;
    private ServerStatisticsAPI serverStatisticsAPI;
    private ShardingStatisticsAPI shardingStatisticsAPI;
    ZookeeperRegistryCenter regCenter;

    public ElasticJobAPI(ZookeeperRegistryCenter regCenter) {
        String serverList=appConfig.getProperty("elasticjob.serverlists");
        String namespace =appConfig.getProperty("elasticjob.namespace");
        jobSettingsAPI = JobAPIFactory.createJobSettingsAPI(serverList, namespace, Optional.fromNullable(null));
        jobOperateAPI = JobAPIFactory.createJobOperateAPI(serverList, namespace,Optional.fromNullable(null));
        shardingOperateAPI = JobAPIFactory.createShardingOperateAPI(serverList, namespace,Optional.fromNullable(null));
        jobStatisticsAPI = JobAPIFactory.createJobStatisticsAPI(serverList, namespace,Optional.fromNullable(null));
        serverStatisticsAPI= JobAPIFactory.createServerStatisticsAPI(serverList, namespace,Optional.fromNullable(null));
        shardingStatisticsAPI= JobAPIFactory.createShardingStatisticsAPI(serverList, namespace,Optional.fromNullable(null));
        this.regCenter = regCenter;
    }
    @Override
    public void addJob(Job job) {
        JobBriefInfo jobBriefInfo= jobStatisticsAPI.getJobBriefInfo(job.getJobKey());
        if(jobBriefInfo!=null){
            pauseJob(job);
            deleteJob(job);
        }
//        // 核心配置
//        JobCoreConfiguration coreConfig =JobCoreConfiguration.newBuilder(job.getJobKey(), job.getCron(), 1).shardingItemParameters("0=1").build();
//        LiteJobConfiguration jobConfig = null;
//        JobTypeConfiguration typeConfig = new SimpleJobConfiguration(coreConfig, GeneralJob.class.getCanonicalName());;
//        jobConfig = LiteJobConfiguration.newBuilder(typeConfig).build();
//        SimpleJob simpleJob=new GeneralJob();
//        SpringJobScheduler jobScheduler=new SpringJobScheduler(simpleJob, regCenter, jobConfig);
//        jobScheduler.init();
    }

    @Override
    public void deleteJob(Job job) {

        pauseJob(job);
        jobOperateAPI.remove(Optional.of(job.getJobKey()), Optional.<String>absent());


    }

    @Override
    public void runJob(Job job) {
        jobOperateAPI.trigger(Optional.of(job.getJobKey()), Optional.<String>absent());
    }

    @Override
    public void pauseJob(Job job) {

        jobOperateAPI.disable(Optional.of(job.getJobKey()), Optional.<String>absent());
    }

    @Override
    public void resumeJob(Job job) {
        jobOperateAPI.enable(Optional.of(job.getJobKey()), Optional.<String>absent());

    }

    @Override
    public void pauseAll() {
        Collection<JobBriefInfo> listJob=jobStatisticsAPI.getAllJobsBriefInfo();
        for(JobBriefInfo jinfo:listJob){
            jobOperateAPI.disable(Optional.of(jinfo.getJobName()), Optional.<String>absent());
        }

    }

    @Override
    public void resumeAll() {
        Collection<JobBriefInfo> listJob=jobStatisticsAPI.getAllJobsBriefInfo();
        for(JobBriefInfo jinfo:listJob){
            jobOperateAPI.enable(Optional.of(jinfo.getJobName()), Optional.<String>absent());
        }
    }

    @Override
    public void start() {

    }

    @Override
    public void shutdown() {

    }

    @Override
    public List<Job> getAll() {
        return null;
    }
}
