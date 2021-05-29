package com.microservice.user;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.context.annotation.Bean;
import org.springframework.context.support.ResourceBundleMessageSource;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.servlet.LocaleResolver;
import org.springframework.web.servlet.i18n.AcceptHeaderLocaleResolver;
import org.springframework.web.servlet.i18n.SessionLocaleResolver;

import java.util.Locale;

@SpringBootApplication
public class UserServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(UserServiceApplication.class, args);
	}

	@Bean
	@LoadBalanced
	public RestTemplate restTemplate(){
		return new RestTemplate();
	}

	@Bean
	public LocaleResolver localeResolver(){
		AcceptHeaderLocaleResolver def=new AcceptHeaderLocaleResolver();
		def.setDefaultLocale(Locale.US);
		return def;
	}

	@Bean
	public ResourceBundleMessageSource bundleMessageSource(){
		ResourceBundleMessageSource rbms=new ResourceBundleMessageSource();
		rbms.setBasename("messages");
		return rbms;
	}
}
