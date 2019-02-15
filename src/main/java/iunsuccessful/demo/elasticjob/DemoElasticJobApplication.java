package iunsuccessful.demo.elasticjob;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ImportResource;

@ImportResource(locations={"classpath:spring-elastic-job.xml"})
@SpringBootApplication
public class DemoElasticJobApplication {

    /**
     * @param args
     */
	public static void main(String[] args) {
		SpringApplication.run(DemoElasticJobApplication.class, args);
	}

}

