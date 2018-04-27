package com.cion.springCloudZuul;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.netflix.zuul.EnableZuulProxy;
import org.springframework.context.annotation.Bean;

import zipkin2.Span;
import zipkin2.reporter.Reporter;

@SpringBootApplication
// @EnableZuulServer
@EnableZuulProxy
@EnableDiscoveryClient // zuul本身没有包含服务发现
public class AppZuul {
	public static void main(String[] args) {
		SpringApplication.run(AppZuul.class, args);
	}

	/**
	 * 用于调试，spring.zipkin.enabled=false时,{@link org.springframework.cloud.sleuth.zipkin2.ZipkinAutoConfiguration#reporter}不会被创建，将链路信息输出到控制台(或者没有zipkin服务器的情况下)，
		spring.zipkin.enabled属性在配置服务器上 application-zipkin.yml
	 * @return
	 */
	@Bean
	@ConditionalOnProperty(value = "spring.zipkin.enabled", havingValue="false")
	public Reporter<Span> spanReporter() {
		return Reporter.CONSOLE;
	}
}
