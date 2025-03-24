package com.root.sms.societyMgmtService;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.ConfigurationPropertiesScan;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@SpringBootApplication(scanBasePackages = "com.root")
@EnableDiscoveryClient
@ConfigurationPropertiesScan
public class SocietyMgmtServiceApplication {


	public static void main(String[] args) {
		SpringApplication.run(SocietyMgmtServiceApplication.class, args);
	}

}
