package org.mysandbox.springdemo.xmlctxt;

import org.springframework.context.support.ClassPathXmlApplicationContext;

public class BeanScopeDemoApp {

	public static void main(String[] args) {
		
		//Load spring configuration file
		ClassPathXmlApplicationContext context = 
				new ClassPathXmlApplicationContext("beanScope-applicationContext.xml");
		
		//Retrieve bean from spring container
		Coach coach = context.getBean("myCoach", Coach.class);
		
		Coach alphaCoach = context.getBean("myCoach", Coach.class);
		
		//Check if they are the same
		boolean result = (coach == alphaCoach);
		
		//Print out the results
		System.out.println("\nPointing to the same object; " + result);
		
		System.out.println("\nMemory location for coach: " + coach);

		System.out.println("\nMemory location for alphaCoach: " + alphaCoach + "\n");
		
		//Close the context
		context.close();
		
	}

}
