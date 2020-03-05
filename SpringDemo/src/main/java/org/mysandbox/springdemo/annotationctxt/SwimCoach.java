package org.mysandbox.springdemo.annotationctxt;

import org.springframework.beans.factory.annotation.Value;

public class SwimCoach implements Coach {

    @Value("${coach.email}")
    private String email;

    private FortuneService fortuneService;

    //Define a default constructor
    public SwimCoach() {
    }

    //Define a constructor for dependency injection
    public SwimCoach(FortuneService fortuneService) {
        this.fortuneService = fortuneService;
    }


    @Override
    public String getDailyWorkout() {
        return "Swim 1000 meters.";
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
