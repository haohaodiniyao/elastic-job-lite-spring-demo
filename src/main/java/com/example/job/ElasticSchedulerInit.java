package com.example.job;

import com.dangdang.ddframe.job.api.simple.SimpleJob;
import com.example.annotation.ElasticJobParamVo;
import com.example.annotation.ElasticSchedulerParam;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.core.annotation.AnnotationUtils;
import org.springframework.stereotype.Component;

/**
 * @ClassName ElasticSchedulerInit
 * @Description: 功能描述
 * @Author: byl
 * @Date: 20200616
 * @Version 1.0
 */
@Component
public class ElasticSchedulerInit implements ApplicationContextAware, InitializingBean {

    private ApplicationContext applicationContext;
    @Autowired
    private ElasticJobHandler elasticJobHandler;
    @Override
    public void afterPropertiesSet() {
        registrJob(applicationContext);
    }

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) {
        this.applicationContext = applicationContext;
    }


    private void registrJob(ApplicationContext applicationContext) {
        String[] beanNamesForAnnotation = applicationContext.getBeanNamesForAnnotation(ElasticSchedulerParam.class);
        for (String beanName : beanNamesForAnnotation) {
            Class < ? > handlerType = applicationContext.getType(beanName);
            Object bean = applicationContext.getBean(beanName);
            ElasticSchedulerParam annotation = AnnotationUtils.findAnnotation(handlerType, ElasticSchedulerParam.class);
            addJobToContext(annotation, bean);
        }
    }


    /**
     * 将任务添加到容器中
     *
     * @param elasticScheduler
     * @param bean
     */

    private void addJobToContext(ElasticSchedulerParam elasticScheduler, Object bean) {
        ElasticJobParamVo elasticJobParamVo = new ElasticJobParamVo();
        elasticJobParamVo.setCron(elasticScheduler.cron());
        elasticJobParamVo.setJobName(elasticScheduler.jobName());
        elasticJobParamVo.setShardingItemParameters(elasticScheduler.shardingItemParameters());
        elasticJobParamVo.setShardingTotalCount(elasticScheduler.shardingTotalCount());
        elasticJobParamVo.setClassFullPath(elasticScheduler.jobClassFullPath());
        elasticJobParamVo.setJobClass((SimpleJob) bean);
        elasticJobParamVo.setDescription(elasticScheduler.description());
        elasticJobParamVo.setMisfire(elasticScheduler.misfire());
        elasticJobParamVo.setFailover(elasticScheduler.failover());
        elasticJobParamVo.setDisabled(elasticScheduler.disabled());
        elasticJobHandler.addJob(elasticJobParamVo);
    }

}
