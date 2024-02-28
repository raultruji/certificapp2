package com.raultruji.certificapp2.application.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

//@Configuration
public class WebConfig implements WebMvcConfigurer{

	  @Override
	  public void addViewControllers(ViewControllerRegistry registry) {
	    registry.addViewController("/certificapp/index").setViewName("index");
	    registry.addViewController("/certificapp/login");
	  }
}
