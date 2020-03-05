package org.mysandbox.springdemo.annotationctxt;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

//@Component("myTennisCoach")
@Component /* Using the default bean id */
@Scope("singleton")
public class TennisCoach implements Coach {
	
	@Value("${coach.email}")
	private String email;
	
	//Define a private filed for the dependency
	//@Autowired
	@Qualifier("fileFortuneService")
	private FortuneService fortuneService;
    
	//Define a default constructor
	public TennisCoach() {}
	
	//Define a constructor for dependency injection
	@Autowired
    public TennisCoach(@Qualifier("fileFortuneService") FortuneService fortuneService) {
    	this.fortuneService = fortuneService;
    }
	
    //Define a setter method
    //@Autowired
	public void setFortuneService(@Qualifier("fileFortuneService") FortuneService fortuneService) {
		this.fortuneService = fortuneService;
	}
	
    //Define own custom method name (Method Injection)
    //@Autowired
	public void doSomeStuff(@Qualifier("fileFortuneService") FortuneService fortuneService) {
		this.fortuneService = fortuneService;
	}
	
	// Define init method
	@PostConstruct 
	public void doStartupStuff() {
		System.out.println("\n[Init Hook] Bean initialization\n" + this.getClass().getName());
	}
	
	// Define destroy method
	@PreDestroy
	public void doCleanupStuff() {
		System.out.println("\n[Destroy Hook] Bean destruction\n" + this.getClass().getName());
	}
	
	@Override
	public String getDailyWorkout() {
		return "Practice your backhand volley";
	}

	@Override
	public String getDailyFortune() {
		return fortuneService.getFortune();
	}

	@Override
	public String getEmail() {
		return email;
	}

}
