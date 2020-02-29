package com.ubsoft.framework.job.conf;

import com.dangdang.ddframe.job.reg.zookeeper.ZookeeperConfiguration;
import com.dangdang.ddframe.job.reg.zookeeper.ZookeeperRegistryCenter;
import com.ubsoft.framework.core.conf.AppConfig;
import com.ubsoft.framework.core.context.AppContext;
import com.ubsoft.framework.core.util.StringUtil;
import com.ubsoft.framework.job.GeneralBeanJobFactory;
import com.ubsoft.framework.job.api.IJobAPI;
import com.ubsoft.framework.job.api.impl.ElasticJobAPI;
import com.ubsoft.framework.job.api.impl.QuartzJobAPI;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.io.ClassPathResource;
import org.springframework.scheduling.quartz.SchedulerFactoryBean;

import javax.sql.DataSource;
import java.io.IOException;

/**
 * 应用程序配置类
 */

@Configuration
@PropertySource("classpath:job.properties")
public class JobConfig {
    @Autowired
    AppConfig appConfig;
    @Autowired
    GeneralBeanJobFactory jobFactory;
    @Value("${job.type}")
    private String jobType;
    @Value("${job.auto}")
    private boolean jobAuto;
    @Bean
    IJobAPI jobAPI() {
        IJobAPI jobApi=null;
        if(jobType.equals("elastic-job")){
            jobApi=new ElasticJobAPI(AppContext.getBean(ZookeeperRegistryCenter.class));
        }else{
            jobApi=new QuartzJobAPI();
        }
       return jobApi;
    }
    @Bean(initMethod = "init")
    public ZookeeperRegistryCenter regCenter() {
        if(jobType.equals("elastic-job")) {
            String serverList = appConfig.getProperty("elasticjob.serverlists");
            String namespace = appConfig.getProperty("elasticjob.namespace");
            if (StringUtil.isNotEmpty(serverList)) {
                ZookeeperConfiguration zkConfig = new ZookeeperConfiguration(serverList, namespace);
                ZookeeperRegistryCenter zookeeperRegistryCenter = new ZookeeperRegistryCenter(zkConfig);
                return zookeeperRegistryCenter;
            }

        }
        return null;
    }

    @Bean(name="SchedulerFactory")
    public SchedulerFactoryBean schedulerFactoryBean() throws IOException {
        if(jobType.equals("quartz")) {
            SchedulerFactoryBean factory = new SchedulerFactoryBean();
            factory.setJobFactory(jobFactory);
            if(!jobAuto) {
                factory.setAutoStartup(false);
            }
            factory.setDataSource(AppContext.getBean(DataSource.class));
            factory.setConfigLocation(new ClassPathResource("/job.properties"));
            return factory;
        }
        return null;
    }

//    @Bean
//    public Properties quartzProperties() throws IOException {
//        PropertiesFactoryBean propertiesFactoryBean = new PropertiesFactoryBean();
//        propertiesFactoryBean.setLocation(new ClassPathResource("/quartz.properties"));
//        //在quartz.properties中的属性被读取并注入后再初始化对象
//        propertiesFactoryBean.afterPropertiesSet();
//        return propertiesFactoryBean.getObject();
//    }



//
//    public void addjobs(final String jobName , final SimpleJob simpleJob, final String cron, final int shardingTotalCount,
//                       final String shardingItemParameters){
//        simpleJobScheduler(jobName,simpleJob,cron,shardingTotalCount,shardingItemParameters).init();
//    }
//
//
//    public JobScheduler simpleJobScheduler(final String jobName , final SimpleJob simpleJob, final String cron, final int shardingTotalCount,
//                                           final String shardingItemParameters) {
//
//        LiteJobConfiguration liteJobConfiguration =  getLiteJobConfiguration(jobName ,simpleJob.getClass(), cron, shardingTotalCount, shardingItemParameters);
//        return new SpringJobScheduler(simpleJob, regCenter,liteJobConfiguration /*, jobEventConfiguration*/);
//    }
//
//    private LiteJobConfiguration getLiteJobConfiguration(final String jobName ,final Class<? extends SimpleJob> jobClass, final String cron, final int shardingTotalCount, final String shardingItemParameters) {
//
//        /**
//         * JobCoreConfiguration.newBuilder(String jobName, String cron, int shardingTotalCount)
//         * 其中jobName是任务名称
//         * cron是cron表达式
//         * shardingTotalCount是分片总数
//         * 这样就可以把jobClass.getName()变成我们自己命名的jobName
//         */
////        JobCoreConfiguration jobCoreConfiguration = JobCoreConfiguration.newBuilder(
////                jobClass.getName(), cron, shardingTotalCount).shardingItemParameters(shardingItemParameters).build();
//
//        JobCoreConfiguration jobCoreConfiguration = JobCoreConfiguration.newBuilder(
//                jobName, cron, shardingTotalCount).shardingItemParameters(shardingItemParameters).build();
//        JobTypeConfiguration jobTypeConfiguration = new SimpleJobConfiguration(jobCoreConfiguration, jobClass.getCanonicalName());
//
//        return LiteJobConfiguration.newBuilder(jobTypeConfiguration).overwrite(true).build();
//
//       /* return LiteJobConfiguration.newBuilder(new SimpleJobConfiguration(JobCoreConfiguration.newBuilder(
//                jobClass.getName(), cron, shardingTotalCount).shardingItemParameters(shardingItemParameters).build(), jobClass.getCanonicalName())).overwrite(true).build();*/
//    }






}
