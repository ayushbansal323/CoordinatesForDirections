package com.locus.spring;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.ViewResolver;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.view.InternalResourceViewResolver;

import com.locus.restapi.impl.DirectionAPIImpl;
import com.locus.restapi.intf.DirectionAPI;
import com.locus.service.impl.DirectionServiceImpl;
import com.locus.service.intf.DirectionsService;

/**
 * Configuration for spring application
 * @author Ayush
 *
 */
@Configuration
@EnableWebMvc
@ComponentScan(basePackages = "com.locus")
public class WebConfig implements WebMvcConfigurer {

	@Bean
	public ViewResolver viewResolver() {
		InternalResourceViewResolver vr=new InternalResourceViewResolver();
		vr.setPrefix("/WEB-INF/pages/");
		vr.setSuffix(".jsp");
		return vr;
	}
	
	@Bean
	public DirectionAPI directionAPI() {
		return new DirectionAPIImpl();
	}
	
	@Bean
	public DirectionsService directionsService() {
		return new DirectionServiceImpl();
	}
	
	
}
