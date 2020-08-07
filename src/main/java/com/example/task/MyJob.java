package com.example.task;

import com.dangdang.ddframe.job.api.ShardingContext;
import com.dangdang.ddframe.job.api.simple.SimpleJob;
import com.example.annotation.ElasticSchedulerParam;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Slf4j
@Component
@ElasticSchedulerParam(jobName = "MyJob",
        cron = "0 0/5 * * * ? *",
        description = "my job",
        jobClassFullPath = "com.example.task.MyJob",
        shardingTotalCount = 5,
        shardingItemParameters="0=a,1=b,2=c,3=d,4=e")
public class MyJob implements SimpleJob {
    @Override
    public void execute(ShardingContext shardingContext) {
        log.info("shardingContext={}",shardingContext);
        log.info("每隔5分钟执行...");
    }
}