package com.howtodoinjava.demo;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.concurrent.Executor;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;

import com.asynchandler.AsyncConfiguration;

@SpringBootApplication
@EnableScheduling
public class DemoApplication {
	
	
	 private static final Logger LOGGER = LoggerFactory.getLogger(AsyncConfiguration.class);

	    @Bean (name = "taskExecutor")
	    public Executor taskExecutor() {
	        LOGGER.debug("Creating Async Task Executor");
	        final ThreadPoolTaskExecutor executor = new ThreadPoolTaskExecutor();
	        executor.setCorePoolSize(2);
	        executor.setMaxPoolSize(2);
	        executor.setQueueCapacity(25);
	        executor.setThreadNamePrefix("CarThread-");
	        executor.initialize();
	        return executor;
	    }
	    
//	    @Scheduled(cron = "0 24 20 1/1 * ?")
//		public void cronJobSch() {
//			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSS");
//			Date now = new Date();
//			String strDate = sdf.format(now);
//			System.out.println("Java cron job expression:: " + strDate);
//		}
	

	public static void main(String[] args) {
		SpringApplication.run(DemoApplication.class, args);
		
		
		 
		
	}

}
