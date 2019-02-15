package iunsuccessful.demo.elasticjob.config;

import com.dangdang.ddframe.job.lite.lifecycle.api.JobOperateAPI;
import com.dangdang.ddframe.job.lite.lifecycle.api.JobSettingsAPI;
import com.dangdang.ddframe.job.lite.lifecycle.api.JobStatisticsAPI;
import com.dangdang.ddframe.job.lite.lifecycle.domain.JobBriefInfo;
import com.google.common.base.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;

import java.util.List;

@Service
public class JobOnlineConfigService {

    /**
     * 注入 JobOperateAPI
     */
    @Autowired
    private JobOperateAPI jobOperateAPI;

    /**
     * 注入 JobStatisticsAPI
     */
    @Autowired
    private JobStatisticsAPI jobStatisticsAPI;

    /**
     * 注入 JobSettingsAPI
     */
    @Autowired
    private JobSettingsAPI jobSettingsAPI;

    /**
     * 暂停任务
     *
     * @param jobName 任务名
     * @return 是否成功
     */
    public Boolean disable(String jobName) {
        jobOperateAPI.disable(Optional.of(jobName), Optional.absent());

        return Boolean.TRUE;
    }

    /**
     * 立刻执行任务
     *
     * @param jobName 任务名
     * @return 是否成功
     */
    public Boolean trigger(String jobName) {
        jobOperateAPI.trigger(Optional.of(jobName), Optional.absent());

        return Boolean.TRUE;
    }

    /**
     * 开始任务
     *
     * @param jobName 任务名
     * @return 是否成功
     */
    public Boolean enable(String jobName) {
        jobOperateAPI.enable(Optional.of(jobName), Optional.absent());

        return Boolean.TRUE;
    }

    /**
     * 下线任务
     *
     * @param jobName 任务名
     * @return 是否成功
     */
    public Boolean shutdown(String jobName) {
        jobOperateAPI.shutdown(Optional.of(jobName), Optional.absent());

        return Boolean.TRUE;
    }

    /**
     * 移除任务 只有下线后的任务才可以移除
     *
     * @param jobName 任务名
     * @return 是否成功
     */
    public Boolean remove(String jobName) {
        jobSettingsAPI.removeJobSettings(jobName);

        return Boolean.TRUE;
    }

    /**
     * 根据任务名获取任务详情
     *
     * @param jobName 任务名
     * @return JobOnlineDTO
     */
//    public JobOnlineDTO getDetail(String jobName) {
//
//        JobOnlineDTO jobOnlineDTO = new JobOnlineDTO();
//
//        JobSettings jobSettings = jobSettingsAPI.getJobSettings(jobName);
//        BeanUtils.copyProperties(jobSettings, jobOnlineDTO);
//        JobBriefInfo jobBriefInfo = jobStatisticsAPI.getJobBriefInfo(jobName);
//        if (jobBriefInfo != null) {
//            jobOnlineDTO.setStatus(jobBriefInfo.getStatus().name());
//        }
//        return jobOnlineDTO;
//    }

//    /**
//     * 分页查询
//     *
//     * @param param 查询参数
//     * @return List<JobBriefInfo> 分页数据
//     */
//    public Page<List<JobBriefInfo>> listByPage(ListJobParam param) {
//        Page<List<JobBriefInfo>> jobPage = new Page<>();
//
//        List<JobBriefInfo> jobBriefInfoList = new ArrayList<>();
//
//        if (!TextUtils.isEmpty(param.getJobName())) {
//            JobBriefInfo jobBriefInfo = jobStatisticsAPI.getJobBriefInfo(param.getJobName());
//            if (TextUtils.isEmpty(param.getStatus()) || jobBriefInfo.getStatus().name().equals(param.getStatus())) {
//                jobBriefInfoList.add(jobBriefInfo);
//                jobPage.setTotal(1);
//            }
//        } else {
//
//            List<JobBriefInfo> allJobBriefInfo = (List<JobBriefInfo>)jobStatisticsAPI.getAllJobsBriefInfo();
//            List<JobBriefInfo> jobBriefInfoCollection = new ArrayList<>();
//            for (JobBriefInfo jobBriefInfo : allJobBriefInfo) {
//                if (TextUtils.isEmpty(param.getStatus()) || jobBriefInfo.getStatus().name().equals(param.getStatus())) {
//                    jobBriefInfoCollection.add(jobBriefInfo);
//                }
//            }
//
//            int startIndex = (param.getPageIndex() - 1) * param.getPageSize();
//
//            if (jobBriefInfoCollection.size() > startIndex) {
//
//                int endIndex = param.getPageIndex() * param.getPageSize() - 1;
//
//                if (jobBriefInfoCollection.size() < param.getPageIndex() * param.getPageSize()) {
//                    endIndex = jobBriefInfoCollection.size() - 1;
//                }
//
//                for (int i = startIndex; i <= endIndex; i++) {
//                    jobBriefInfoList.add(jobBriefInfoCollection.get(i));
//                }
//
//                jobPage.setTotal(jobBriefInfoCollection.size());
//            }
//        }
//
//        if (!CollectionUtils.isEmpty(jobBriefInfoList)) {
//            jobPage.setData(jobBriefInfoList);
//            jobPage.setPageIndex(param.getPageIndex());
//            jobPage.setPageSize(param.getPageSize());
//
//        }
//
//        return jobPage;
//    }

    /**
     * 使所有任务都失效
     * 
     * @return 是否成功
     */
    public Boolean disableAllJob() {
        List<JobBriefInfo> allJobBriefInfo = (List<JobBriefInfo>)jobStatisticsAPI.getAllJobsBriefInfo();
        if (!CollectionUtils.isEmpty(allJobBriefInfo)) {
            for (JobBriefInfo jobBriefInfo : allJobBriefInfo) {
                if (!StringUtils.isEmpty(jobBriefInfo.getJobName())) {
                    jobOperateAPI.disable(Optional.of(jobBriefInfo.getJobName()), Optional.absent());
                }
            }
        }
        return Boolean.TRUE;
    }

    /**
     * 使所有任务都生效
     *
     * @return 是否成功
     */
    public Boolean enableAllJob() {
        List<JobBriefInfo> allJobBriefInfo = (List<JobBriefInfo>)jobStatisticsAPI.getAllJobsBriefInfo();
        if (!CollectionUtils.isEmpty(allJobBriefInfo)) {
            for (JobBriefInfo jobBriefInfo : allJobBriefInfo) {
                if (!StringUtils.isEmpty(jobBriefInfo.getJobName())) {
                    jobOperateAPI.enable(Optional.of(jobBriefInfo.getJobName()), Optional.absent());
                }
            }
        }
        return Boolean.TRUE;
    }

}
