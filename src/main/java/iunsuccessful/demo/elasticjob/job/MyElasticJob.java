package iunsuccessful.demo.elasticjob.job;

import com.dangdang.ddframe.job.api.ShardingContext;
import com.dangdang.ddframe.job.api.simple.SimpleJob;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * 〈一句话功能简述〉<br>
 * 〈功能详细描述〉
 * <p/>
 *
 * @author Created by 依韵 on 2019/2/14 .
 */
public class MyElasticJob implements SimpleJob {

    private static final Logger logger = LoggerFactory.getLogger(MyElasticJob.class);

    @Override
    public void execute(ShardingContext context) {

        logger.info("MyElasticJob#execute begin...");

        logger.info("sharding parameter {}", context.getShardingParameter());
        logger.info("job parameter {}", context.getJobParameter());

        switch (context.getShardingItem()) {
            case 0:
                logger.info("run sharding item equals 0");
                break;
            case 1:
                logger.info("run sharding item equals 1");
                break;
            case 2:
                logger.info("run sharding item equals 2");
                break;
            default:
                logger.info("run sharding item equals 2");
        }

        logger.info("MyElasticJob#execute end");

    }
}
