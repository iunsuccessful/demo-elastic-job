package iunsuccessful.demo.elasticjob.config;

import com.dangdang.ddframe.job.lite.lifecycle.api.JobOperateAPI;
import com.dangdang.ddframe.job.lite.lifecycle.api.JobSettingsAPI;
import com.dangdang.ddframe.job.lite.lifecycle.api.JobStatisticsAPI;
import com.dangdang.ddframe.job.lite.lifecycle.internal.operate.JobOperateAPIImpl;
import com.dangdang.ddframe.job.lite.lifecycle.internal.settings.JobSettingsAPIImpl;
import com.dangdang.ddframe.job.lite.lifecycle.internal.statistics.JobStatisticsAPIImpl;
import com.dangdang.ddframe.job.reg.zookeeper.ZookeeperRegistryCenter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.annotation.Resource;


@Configuration
public class ControlConfig {

    /**
     * zookeeper注册中心
     */
    @Resource
    private ZookeeperRegistryCenter regCenter;

    /**
     * 
     * @return JobOperateAPI用户控制job开始,暂停，移除
     */
    @Bean
    public JobOperateAPI jobOperateAPI() {
        return new JobOperateAPIImpl(regCenter);
    }

    /**
     * 
     * @return JobSettingsAPI用于刷新job配置
     */
    @Bean
    public JobSettingsAPI jobSettingsAPI() {
        return new JobSettingsAPIImpl(regCenter);
    }

    /**
     *
     * @return JobSettingsAPI用于获取job状态
     */
    @Bean
    public JobStatisticsAPI jobStatisticsAPI() {
        return new JobStatisticsAPIImpl(regCenter);
    }

}
