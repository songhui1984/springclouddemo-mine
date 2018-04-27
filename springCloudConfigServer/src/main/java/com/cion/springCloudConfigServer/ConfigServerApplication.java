package com.cion.springCloudConfigServer;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.config.server.EnableConfigServer;

/**
 * 配置服务器
 * @author 37534
 *
 */
@EnableConfigServer
@SpringBootApplication
public class ConfigServerApplication {
	
	public static void main(String[] args) {
		  SpringApplication.run(ConfigServerApplication.class, args); 
	}
}
