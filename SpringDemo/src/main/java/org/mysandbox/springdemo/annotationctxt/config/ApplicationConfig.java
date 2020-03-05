package org.mysandbox.springdemo.annotationctxt.config;

import org.mysandbox.springdemo.annotationctxt.Coach;
import org.mysandbox.springdemo.annotationctxt.FortuneService;
import org.mysandbox.springdemo.annotationctxt.SadFortuneService;
import org.mysandbox.springdemo.annotationctxt.SwimCoach;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.*;

@Configuration
// Load properties file
@PropertySource("classpath:coach.properties")
//Scan for components
@ComponentScan("org.mysandbox.springdemo.annotationctxt")
public class ApplicationConfig {
	
	// Define bean for sad fortune service
	@Bean
	public FortuneService sadFortuneService() {
		return new SadFortuneService();
	}
	
	// Define bean for swim coach and inject dependency
	@Bean
	@DependsOn({ "sadFortuneService" })
	public Coach swimCoach(SadFortuneService fortuneService) {
		return new SwimCoach(/*sadFortuneService()*/ fortuneService);
	}

}
