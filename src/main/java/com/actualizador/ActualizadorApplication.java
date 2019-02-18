package com.actualizador;

import org.springframework.batch.core.Job;
import org.springframework.batch.core.JobExecutionException;
import org.springframework.batch.core.JobParameters;
import org.springframework.batch.core.launch.JobLauncher;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

@SpringBootApplication
public class ActualizadorApplication {

    public static void main(String[] args) {
        SpringApplication.run(ActualizadorApplication.class, args);

        @SuppressWarnings("resource")
        ApplicationContext context = new ClassPathXmlApplicationContext("spring-batch-context.xml");

        JobLauncher jobLauncher = (JobLauncher) context.getBean("jobLauncher");
        Job job = (Job) context.getBean("job");

        try {
            jobLauncher.run(job, new JobParameters());
        } catch (JobExecutionException e) {
            throw new RuntimeException(e);
        }
    }
}