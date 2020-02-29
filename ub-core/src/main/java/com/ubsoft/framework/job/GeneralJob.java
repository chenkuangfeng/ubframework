package com.ubsoft.framework.job;

import com.dangdang.ddframe.job.api.ShardingContext;
import com.dangdang.ddframe.job.api.simple.SimpleJob;
import com.ubsoft.framework.core.cache.ICache;
import com.ubsoft.framework.core.context.AppContext;
import com.ubsoft.framework.core.exception.ComException;
import com.ubsoft.framework.core.util.UUIDUtil;
import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;

public class GeneralJob implements SimpleJob, Job {

    @Override
    public void execute(ShardingContext shardingContext) {
        String jobName= shardingContext.getJobName();
        //Job job=this.getExecuteJob(jobName);

        System.out.println(UUIDUtil.generate());
    }

    @Override
    public void execute(JobExecutionContext jobExecutionContext) throws JobExecutionException {
        System.out.println(UUIDUtil.generate());
    }
    private Job getExecuteJob(String jobName){
        ICache cache= AppContext.getCache();
        Job job=cache.get("JOB",jobName,Job.class);
        if(job==null){
            throw new ComException(ComException.MIN_ERROR_CODE_GLOBAL,"no exsits JOB："+jobName);
        }
        return job;
    }

}
