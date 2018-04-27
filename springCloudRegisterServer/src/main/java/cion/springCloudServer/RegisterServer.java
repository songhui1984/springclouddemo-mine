package cion.springCloudServer;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;

@SpringBootApplication
@EnableEurekaServer
public class RegisterServer {
	
	
	public static void main(String[] args) {
//		ConfigurableApplicationContext ctx = 
				SpringApplication.run(RegisterServer.class, args);
//		System.out.println(ctx.getBean(Config.class).getParam());
//		System.out.println(ctx.getEnvironment().getProperty("server.param"));
	}
}
