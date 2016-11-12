package com.rest.test.config;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

@Configuration 
@ComponentScan("com.rest.test.restcontrollers") 
@EnableWebMvc
public class RestAppConfig extends WebMvcConfigurerAdapter {

}
