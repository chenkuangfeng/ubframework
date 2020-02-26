package com.ubsoft.framework.job;

import com.dangdang.ddframe.job.api.ShardingContext;
import com.dangdang.ddframe.job.api.simple.SimpleJob;
import com.ubsoft.framework.core.util.UUIDUtil;
import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;

public class GeneralJob implements SimpleJob, Job {

    @Override
    public void execute(ShardingContext shardingContext) {

        System.out.println(UUIDUtil.generate());
    }

    @Override
    public void execute(JobExecutionContext jobExecutionContext) throws JobExecutionException {

    }
}
