package com.example.job;

import com.dangdang.ddframe.job.executor.ShardingContexts;
import com.dangdang.ddframe.job.lite.api.listener.ElasticJobListener;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.time.DateFormatUtils;
import org.springframework.stereotype.Component;

import java.util.Date;

@Slf4j
@Component
public class MyElasticJobListener implements ElasticJobListener {

    @Override
    public void beforeJobExecuted(ShardingContexts shardingContexts) {
//        log.info("beforeJobExecuted={}", DateFormatUtils.format(new Date(),"yyyy-MM-dd HH:mm:ss"));
//        log.info("shardingContexts={}",shardingContexts);
    }

    @Override
    public void afterJobExecuted(ShardingContexts shardingContexts) {
//        log.info("afterJobExecuted={}", DateFormatUtils.format(new Date(),"yyyy-MM-dd HH:mm:ss"));
//        log.info("shardingContexts={}",shardingContexts);
    }
}
