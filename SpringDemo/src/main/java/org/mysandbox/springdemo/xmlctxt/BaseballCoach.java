package org.mysandbox.springdemo.xmlctxt;

import org.mysandbox.springdemo.xmlctxt.services.FortuneService;

public class BaseballCoach implements Coach{

	//Define a private filed for the dependency
	private FortuneService fortuneService;
	
	//Define a constructor for dependency injection
	public BaseballCoach(FortuneService fortuneService) {
		this.fortuneService = fortuneService;
	}
	
	@Override
	public String getDailyWorkout() {
		return "Spend 30 minutes on batting practice";
	}

	@Override
	public String getDailyFortune() {
		//Use fortuneService to get a fortune
		return fortuneService.getFortune();
	}
	
}
