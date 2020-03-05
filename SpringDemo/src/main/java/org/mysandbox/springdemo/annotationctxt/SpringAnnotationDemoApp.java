package org.mysandbox.springdemo.annotationctxt;

import org.mysandbox.springdemo.annotationctxt.config.ApplicationConfig;
import org.mysandbox.springdemo.annotationctxt.config.LoggerConfig;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class SpringAnnotationDemoApp {

    public static void main(String[] args) {

        // Load the spring java configuration class
        AnnotationConfigApplicationContext context =
                new AnnotationConfigApplicationContext(LoggerConfig.class, ApplicationConfig.class);

        // Retrieve bean from spring container
        Coach coach = context.getBean("tennisCoach", Coach.class);

        // Call methods on the bean
        System.out.println("Coach email: " + coach.getEmail());
        System.out.println(coach.getDailyWorkout());
        System.out.println(coach.getDailyFortune());

        // Close the context
        context.close();
    }
}
