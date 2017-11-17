package com.github.hackyouroffice.foodle;

import com.amazon.speech.speechlet.lambda.SpeechletRequestStreamHandler;

import java.util.HashSet;
import java.util.Set;

public final class FoodleStreamHandler extends SpeechletRequestStreamHandler {

    private static final Set<String> supportedApplicationIds;

    static {
        /*
         * This Id can be found on https://developer.amazon.com/edw/home.html#/ "Edit" the relevant
         * Alexa Skill and put the relevant Application Ids in this Set.
         */
        supportedApplicationIds = new HashSet<>();
        supportedApplicationIds.add("amzn1.ask.skill.b45229b9-9bf2-47b6-9817-333769c167e4");
    }

    public FoodleStreamHandler() {
        super(new FoodleSpeechlet(new RandomLunchProposer()), supportedApplicationIds);
    }
}
