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
    // supportedApplicationIds.add("[unique-value-here]");
  }

  public FoodleStreamHandler() {
    super(new FoodleSpeechlet(), supportedApplicationIds);
  }
}
