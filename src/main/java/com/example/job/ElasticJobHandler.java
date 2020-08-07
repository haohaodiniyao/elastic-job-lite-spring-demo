package com.example.job;

import com.dangdang.ddframe.job.config.JobCoreConfiguration;
import com.dangdang.ddframe.job.config.simple.SimpleJobConfiguration;
import com.dangdang.ddframe.job.lite.api.listener.ElasticJobListener;
import com.dangdang.ddframe.job.lite.config.LiteJobConfiguration;
import com.dangdang.ddframe.job.lite.spring.api.SpringJobScheduler;
import com.dangdang.ddframe.job.reg.zookeeper.ZookeeperRegistryCenter;
import com.example.annotation.ElasticJobParamVo;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;

@Component
public class ElasticJobHandler {

    @Resource
    private ZookeeperRegistryCenter regCenter;

    @Resource(name= "myElasticJobListener")
    private ElasticJobListener myElasticJobListener;
    /**
     * 添加job到SpringJobScheduler
     * @param elasticJobParamVo
     */
    public void addJob(ElasticJobParamVo elasticJobParamVo) {
        JobCoreConfiguration jobCoreConfiguration = JobCoreConfiguration.newBuilder(elasticJobParamVo.getJobName(),
                elasticJobParamVo.getCron(),
                elasticJobParamVo.getShardingTotalCount())
                .shardingItemParameters(elasticJobParamVo.getShardingItemParameters())
                .description(elasticJobParamVo.getDescription())
                .misfire(elasticJobParamVo.isMisfire())
                .failover(elasticJobParamVo.isFailover())
                //.jobProperties("job_exception_handler", "XXXXHandler")
                .build();
        SimpleJobConfiguration simpleJobConfiguration = new SimpleJobConfiguration(jobCoreConfiguration, elasticJobParamVo.getClassFullPath());
        LiteJobConfiguration jobConfig = LiteJobConfiguration.newBuilder(simpleJobConfiguration)
                .overwrite(true)
                .disabled(elasticJobParamVo.isDisabled())
                .build();

        new SpringJobScheduler(elasticJobParamVo.getJobClass(), regCenter, jobConfig, myElasticJobListener).init();
    }

}
