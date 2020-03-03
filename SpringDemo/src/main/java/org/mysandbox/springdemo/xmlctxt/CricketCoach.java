package org.mysandbox.springdemo.xmlctxt;

import org.mysandbox.springdemo.xmlctxt.services.FortuneService;

public class CricketCoach implements Coach {
	
	private String emailAddress;
	private String teamName;
	
	//Define a private filed for the dependency
	private FortuneService fortuneService;
	
	
	//Setters
	
	//Setter method called by spring when injects the dependency
	public void setFortuneService(FortuneService fortuneService) {
		this.fortuneService = fortuneService;
	}

	public void setEmailAddress(String emailAddress) {
		this.emailAddress = emailAddress;
	}

	public void setTeamName(String teamName) {
		this.teamName = teamName;
	}
	
	
	//Getters
	
	public String getEmailAddress() {
		return emailAddress;
	}

	public String getTeamName() {
		return teamName;
	}

	@Override
	public String getDailyWorkout() {
		return "Practice fast bowling for 15 minutes";
	}

	@Override
	public String getDailyFortune() {
		return fortuneService.getFortune();
	}

}
