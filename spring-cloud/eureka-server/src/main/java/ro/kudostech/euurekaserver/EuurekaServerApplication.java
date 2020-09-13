package ro.kudostech.euurekaserver;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;

@EnableEurekaServer
@SpringBootApplication
public class EuurekaServerApplication {

	public static void main(String[] args) {
		SpringApplication.run(EuurekaServerApplication.class, args);
	}

}
