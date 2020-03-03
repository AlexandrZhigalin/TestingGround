package org.mysandbox.springdemo.xmlctxt.services.impl;

import java.util.Random;

import org.mysandbox.springdemo.xmlctxt.services.FortuneService;

public class HappyFortuneService implements FortuneService {

	private static final String[] fortunes = {
			"Today is your lucky day.",
			"Just do it!",
			"Never give up!"
			};

	@Override
	public String getFortune() {
		int randIndex = new Random().nextInt(fortunes.length);
		return fortunes[randIndex];
	}

}
