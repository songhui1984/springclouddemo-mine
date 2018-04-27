package cion.springCloudClient;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.cloud.client.circuitbreaker.EnableCircuitBreaker;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.cloud.openfeign.EnableFeignClients;
//import org.springframework.cloud.netflix.feign.EnableFeignClients;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import zipkin2.Span;
import zipkin2.reporter.Reporter;

@SpringBootApplication
@EnableDiscoveryClient
@EnableFeignClients
@EnableCircuitBreaker
@ComponentScan
// @EnableHystrix
public class AppClient {

	public static void main(String[] args) {
		ConfigurableApplicationContext ctx = SpringApplication.run(AppClient.class, args);
		// String temp = ctx.getEnvironment().getProperty("service.url");
		// http://localhost:9991/actuator/hystrix.stream
	}

	@Bean
	@LoadBalanced
	RestTemplate restTemplate() {
		return new RestTemplate();
	}

	/**
	 * 用于调试，spring.zipkin.enabled=false时,{@link org.springframework.cloud.sleuth.zipkin2.ZipkinAutoConfiguration#reporter}不会被创建，将链路信息输出到控制台(或者没有zipkin服务器的情况下)，
		spring.zipkin.enabled属性在配置服务器上 application-zipkin.yml
	 * @return
	 */
	@Bean
	@ConditionalOnProperty(value = "spring.zipkin.enabled", havingValue="false")
//	@ConditionalOnProperty(value = "spring.zipkin.enabled", havingValue="true")
	public Reporter<Span> spanReporter() {
		return Reporter.CONSOLE;
	}
}
