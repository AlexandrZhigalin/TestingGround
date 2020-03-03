package org.mysandbox.springdemo.xmlctxt;

import org.mysandbox.springdemo.xmlctxt.services.FortuneService;

public class TrackCoach implements Coach {

	//Define a private filed for the dependency
	private FortuneService fortuneService;
	
	//Define a constructor for dependency injection
	public TrackCoach(FortuneService fortuneService) {
		this.fortuneService = fortuneService;
	}
	
	@Override
	public String getDailyWorkout() {
		return "Run a hard 5k";
	}

	@Override
	public String getDailyFortune() {
		//Use fortuneService to get a fortune
		return fortuneService.getFortune();
	}

	
	//Add an init method
	public void doStartupStuff() {
		System.out.println("TrackCoach: inside method doStartupStuff");
	}
	
	//Add a destroy method
	public void doCleanupStuff() {
		System.out.println("TrackCoach: inside method doCleanupStuff");
	}
	
}
