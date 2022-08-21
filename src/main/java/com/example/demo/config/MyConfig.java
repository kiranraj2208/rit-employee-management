package com.example.demo.config;

import com.example.demo.repository.EmployeeRepository;
import com.example.demo.service.EmployeeService;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

@Configuration
public class MyConfig {

    @Bean
    @Qualifier("restTemplate1")
    public RestTemplate getRestTemplate() {
        return new RestTemplate();
    }

    @Bean
    @Qualifier("restTemplate2")
    public RestTemplate getRestTemplateNew() {
        return new RestTemplate();
    }

    @Bean
    public EmployeeService getEmployeeService(EmployeeRepository employeeRepository) {
        return new EmployeeService(employeeRepository);
    }
}
