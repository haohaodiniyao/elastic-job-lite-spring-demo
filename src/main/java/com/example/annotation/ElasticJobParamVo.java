package com.example.annotation;

import com.dangdang.ddframe.job.api.simple.SimpleJob;
import lombok.Data;

/**
 * https://shardingsphere.apache.org/elasticjob/legacy/lite-2.x/02-guide/config-manual/
 */
@Data
public class ElasticJobParamVo {
    /**
     * 作业名称
     */
    private String jobName;

    private SimpleJob jobClass;

    /**
     * cron表达式，用于控制作业触发时间
     */
    private String cron;

    /**
     * 作业分片总数
     */
    private int shardingTotalCount;

    /**
     * 作业描述信息
     */
    private String description;

    private String shardingItemParameters;

    private String classFullPath;

    /**
     * 是否开启错过任务重新执行
     */
    private boolean misfire;

    /**
     * 是否开启任务执行失效转移，开启表示如果作业在一次任务执行中途宕机，允许将该次未完成的任务在另一作业节点上补偿执行
     */
    private boolean failover;
    /**
     * 作业是否禁止启动
     * 可用于部署作业时，先禁止启动，部署结束后统一启动
     */
    private boolean disabled;
}
