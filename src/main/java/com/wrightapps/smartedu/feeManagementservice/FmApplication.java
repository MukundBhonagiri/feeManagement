package com.wrightapps.smartedu.feeManagementservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@EnableConfigurationProperties
public class FmApplication { 

	public static void main(String[] args) {
		SpringApplication.run(FmApplication.class, args);
	}

}
