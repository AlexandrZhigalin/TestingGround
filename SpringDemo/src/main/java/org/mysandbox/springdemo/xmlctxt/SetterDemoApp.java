package org.mysandbox.springdemo.xmlctxt;

import org.springframework.context.support.ClassPathXmlApplicationContext;

public class SetterDemoApp {

	public static void main(String[] args) {

		// Load the spring configuration file
		ClassPathXmlApplicationContext context = 
				new ClassPathXmlApplicationContext("applicationContext.xml");
		
		// Retrieve bean from spring container
		CricketCoach coach = context.getBean("myCricketCoach", CricketCoach.class);
		
		// Call methods on the bean
		System.out.println(coach.getDailyWorkout());
		
		// Call new method for fortunes
		System.out.println(coach.getDailyFortune());
		
		// Call new methods to get literal values
		System.out.println(coach.getEmailAddress());
		System.out.println(coach.getTeamName());
		
		// Close the context
		context.close();
		
	}

}
