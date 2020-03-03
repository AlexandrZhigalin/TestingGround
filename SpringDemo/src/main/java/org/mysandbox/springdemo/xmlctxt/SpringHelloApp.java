package org.mysandbox.springdemo.xmlctxt;

import org.springframework.context.support.ClassPathXmlApplicationContext;

public class SpringHelloApp {

	public static void main(String[] args) {
		
		//Load the spring configuration file
		ClassPathXmlApplicationContext context = 
				new ClassPathXmlApplicationContext("applicationContext.xml");
		
		//Retrieve bean from spring container
		Coach coach = context.getBean("myCoach", Coach.class);
		
		//Call methods on the bean
		System.out.println(coach.getDailyWorkout());
		
		//Call new method for fortunes
		System.out.println(coach.getDailyFortune());
		
		//Close the context
		context.close();
		
	}

}
