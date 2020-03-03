package org.mysandbox.springdemo.xmlctxt;

import org.mysandbox.springdemo.xmlctxt.services.FortuneService;

public class HockeyCoach implements Coach {

	//Define a private filed for the dependency
	private FortuneService fortuneService;
	
	//Define a constructor for dependency injection
	public HockeyCoach(FortuneService fortuneService) {
		this.fortuneService = fortuneService;
	}
	
	@Override
	public String getDailyWorkout() {
		return "Horizontal Pull-Ups x3";
	}

	@Override
	public String getDailyFortune() {
		//Use fortuneService to get a fortune
		return fortuneService.getFortune();
	}

}
