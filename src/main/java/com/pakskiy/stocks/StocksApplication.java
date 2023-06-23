package com.pakskiy.stocks;

import com.pakskiy.stocks.config.DefaultProfileUtil;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.web.client.RestTemplate;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

@SpringBootApplication
@EntityScan(basePackages = {"com.pakskiy.stocks"})
@ComponentScan(basePackages = {"com.pakskiy.stocks"})
@EnableJpaRepositories(basePackages = {"com.pakskiy.stocks"})
@EnableScheduling
public class StocksApplication {
	public static void main(String[] args) {
		SpringApplication app = new SpringApplication(StocksApplication.class);
		DefaultProfileUtil.addDefaultProfile(app);
		app.run(args);
	}

	@Bean
	public RestTemplate restTemplate()
	{
		return new RestTemplate();
	}

	@Bean
	public ExecutorService executorService() {
		return Executors.newFixedThreadPool(Runtime.getRuntime().availableProcessors() * 2);
	}
}
