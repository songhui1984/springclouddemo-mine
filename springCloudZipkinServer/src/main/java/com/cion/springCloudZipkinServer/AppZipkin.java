package com.cion.springCloudZipkinServer;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import zipkin.server.EnableZipkinServer;


@SpringBootApplication
@EnableZipkinServer
public class AppZipkin {
	public static void main(String[] args) {
		SpringApplication.run(AppZipkin.class, args);
//		Caused by: java.lang.ClassNotFoundException: org.springframework.boot.context.embedded.undertow.UndertowEmbeddedServletContainerFactory
	}
}
