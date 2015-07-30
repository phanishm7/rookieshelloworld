package demo.config;


import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;

import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;


@Configuration 
@EnableAspectJAutoProxy
@ComponentScan(basePackages = "demo")
@EnableWebMvc   
public class Application extends WebMvcConfigurerAdapter{  
	

} 