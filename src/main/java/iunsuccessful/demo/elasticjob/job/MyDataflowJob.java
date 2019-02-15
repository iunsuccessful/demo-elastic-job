package iunsuccessful.demo.elasticjob.job;

import com.dangdang.ddframe.job.api.ShardingContext;
import com.dangdang.ddframe.job.api.dataflow.DataflowJob;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.List;

/**
 * <p/>
 *
 * @author Created by 依韵 on 2019/2/14 .
 */
public class MyDataflowJob implements DataflowJob<Integer> {

    private static final Logger logger = LoggerFactory.getLogger(MyDataflowJob.class);

    private static final ThreadLocal<Integer> LOOP_COUNTER = new ThreadLocal<>();

    @Override
    public List<Integer> fetchData(ShardingContext context) {

//        logger.info("fetchData {}", Thread.currentThread().getId());

//        logger.info("sharding parameter {}", context.getShardingParameter());

//        return null;
        Integer counter = LOOP_COUNTER.get();
        if (counter == null) {
            counter = 10;
            LOOP_COUNTER.set(10);
        } else if (counter > 1) {
            counter--;
            LOOP_COUNTER.set(counter);
        } else {
            LOOP_COUNTER.remove();
            return null;
        }
        List<Integer> list = new ArrayList<>();
        for (int i = 0; i < counter; i++) {
            list.add(i);
        }
        return list;
    }

    @Override
    public void processData(ShardingContext shardingContext, List<Integer> data) {

//        logger.info("processData {}", Thread.currentThread().getId());

//        System.out.println(Thread.currentThread().getId());

        for (int i = 0; i < data.size(); i++) {
            System.out.printf("%d ", data.get(i));
        }
        System.out.println();
    }
}
