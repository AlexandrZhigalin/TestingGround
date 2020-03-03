package org.mysandbox.springdemo.xmlctxt;

import org.mysandbox.springdemo.xmlctxt.services.impl.HappyFortuneService;

public class MyApp {
	
	public static void main(String[] args) {
		
		// create the object
		Coach coach = new TrackCoach(new HappyFortuneService());
		
		// get daily workout
        System.out.println(coach.getDailyWorkout());
        
        // get daily fortune message
        System.out.println(coach.getDailyFortune());
        
	}

}
