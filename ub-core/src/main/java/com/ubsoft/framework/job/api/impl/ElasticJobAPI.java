package com.ubsoft.framework.job.api.impl;

import com.dangdang.ddframe.job.config.JobCoreConfiguration;
import com.dangdang.ddframe.job.config.JobTypeConfiguration;
import com.dangdang.ddframe.job.config.simple.SimpleJobConfiguration;
import com.dangdang.ddframe.job.lite.api.JobScheduler;
import com.dangdang.ddframe.job.lite.config.LiteJobConfiguration;
import com.dangdang.ddframe.job.lite.lifecycle.api.*;
import com.dangdang.ddframe.job.lite.spring.api.SpringJobScheduler;
import com.dangdang.ddframe.job.reg.zookeeper.ZookeeperRegistryCenter;
import com.google.common.base.Optional;
import com.ubsoft.framework.core.conf.AppConfig;
import com.ubsoft.framework.core.context.AppContext;
import com.ubsoft.framework.job.GeneralJob;
import com.ubsoft.framework.job.api.IJobAPI;
import com.ubsoft.framework.job.entity.Job;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

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
        jobSettingsAPI = JobAPIFactory.createJobSettingsAPI(serverList, namespace,Optional.fromNullable(null));
        jobOperateAPI = JobAPIFactory.createJobOperateAPI(serverList, namespace,Optional.fromNullable(null));
        shardingOperateAPI = JobAPIFactory.createShardingOperateAPI(serverList, namespace,Optional.fromNullable(null));
        jobStatisticsAPI = JobAPIFactory.createJobStatisticsAPI(serverList, namespace,Optional.fromNullable(null));
        serverStatisticsAPI= JobAPIFactory.createServerStatisticsAPI(serverList, namespace,Optional.fromNullable(null));
        shardingStatisticsAPI= JobAPIFactory.createShardingStatisticsAPI(serverList, namespace,Optional.fromNullable(null));
        this.regCenter = regCenter;
    }
    @Override
    public void addJob(Job job) {
        JobCoreConfiguration jobCoreConfiguration = JobCoreConfiguration.newBuilder(
                job.getJobKey(), job.getCron(), 1).shardingItemParameters("123323").build();
        JobTypeConfiguration jobTypeConfiguration = new SimpleJobConfiguration(jobCoreConfiguration, "");
        LiteJobConfiguration liteJobConfiguration = LiteJobConfiguration.newBuilder(jobTypeConfiguration).overwrite(true).build();
        JobScheduler js = new SpringJobScheduler(new GeneralJob(), regCenter, liteJobConfiguration /*, jobEventConfiguration*/);
        js.init();
    }

    @Override
    public void deleteJob(Job job) {
       // jobOperateAPI.remove();
    }

    @Override
    public void runJob(Job job) {

    }

    @Override
    public void pauseJob(Job job) {

    }

    @Override
    public void resumeJob(Job job) {

    }

    @Override
    public void pauseAll() {

    }

    @Override
    public void resumeAll() {

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
