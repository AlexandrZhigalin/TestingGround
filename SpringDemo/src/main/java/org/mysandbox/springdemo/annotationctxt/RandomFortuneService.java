package org.mysandbox.springdemo.annotationctxt;

import org.springframework.stereotype.Component;

import java.util.Random;

@Component
public class RandomFortuneService implements FortuneService {

    // Create an array of strings
    private static final String[] fortunes = {
            "How we spend our days is, of course, how we spend our lives.",
            "Don't just wait for inspiration, Become it.",
            "If you keep on doing what you've always done, you will keep getting what you've always gotten."
    };

    @Override
    public String getFortune() {
        // Pick a random String from the array
        int randIndex = new Random().nextInt(fortunes.length);
        return fortunes[randIndex];
    }

}
