package iunsuccessful.demo.elasticjob.mongo;

import com.dangdang.ddframe.job.lite.lifecycle.api.JobOperateAPI;
import com.dangdang.ddframe.job.lite.lifecycle.api.JobSettingsAPI;
import com.dangdang.ddframe.job.lite.lifecycle.api.JobStatisticsAPI;
import com.dangdang.ddframe.job.lite.lifecycle.domain.JobBriefInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextClosedEvent;
import org.springframework.context.event.ContextStartedEvent;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.Collection;

/**
 * <p/>
 *
 * @author Created by 依韵 on 2019/2/14 .
 */
@Component
public class RunMongoDbInitializerWhenNeeded implements ApplicationListener<ContextStartedEvent> {

    @Autowired
    private JobStatisticsAPI jobStatisticsAPI;

    @Autowired
    private JobOperateAPI jobOperateAPI;

    @Autowired
    private JobSettingsAPI jobSettingsAPI;

    @PostConstruct
    private void init() {
        Collection<JobBriefInfo> infos = jobStatisticsAPI.getAllJobsBriefInfo();
        infos.forEach(x -> {
            jobSettingsAPI.removeJobSettings(x.getJobName());
        });
    }

//    @Override
//    public void onApplicationEvent(ContextClosedEvent contextClosedEvent) {
//        Collection<JobBriefInfo> infos = jobStatisticsAPI.getAllJobsBriefInfo();
//        infos.forEach(x -> {
//            jobSettingsAPI.removeJobSettings(x.getJobName());
//        });
//    }

    @Override
    public void onApplicationEvent(ContextStartedEvent contextStartedEvent) {
        Collection<JobBriefInfo> infos = jobStatisticsAPI.getAllJobsBriefInfo();
        infos.forEach(x -> {
            jobSettingsAPI.removeJobSettings(x.getJobName());
        });
    }
}
