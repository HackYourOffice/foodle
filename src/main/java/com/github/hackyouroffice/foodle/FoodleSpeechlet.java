package com.github.hackyouroffice.foodle;

import com.amazon.speech.json.SpeechletRequestEnvelope;
import com.amazon.speech.slu.Intent;
import com.amazon.speech.speechlet.*;
import com.amazon.speech.ui.OutputSpeech;
import com.amazon.speech.ui.PlainTextOutputSpeech;
import com.amazon.speech.ui.Reprompt;
import com.amazon.speech.ui.SimpleCard;

public class FoodleSpeechlet implements SpeechletV2 {

  private static final String GENERAL_FOOD_INTEND_NAME = "LunchProposal";
  private final LunchProposer lunchProposer;

  public FoodleSpeechlet(LunchProposer lunchProposer) {
    this.lunchProposer = lunchProposer;
  }

  @Override
  public void onSessionStarted(SpeechletRequestEnvelope<SessionStartedRequest> speechletRequestEnvelope) {

  }

  @Override
  public SpeechletResponse onLaunch(SpeechletRequestEnvelope<LaunchRequest> speechletRequestEnvelope) {
    return getAskResponse("Foodle", "Fuudel gestartet!");
  }

  @Override
  public SpeechletResponse onIntent(SpeechletRequestEnvelope<IntentRequest> speechletRequestEnvelope) {

    IntentRequest request = speechletRequestEnvelope.getRequest();

    Intent intent = request.getIntent();
    String intentName = (intent != null) ? intent.getName() : null;

    if (GENERAL_FOOD_INTEND_NAME.equals(intentName)) {

      Proposal proposal = lunchProposer.getProposal();
      return getAskResponse(proposal.getTitle(), proposal.getText());

    } else if ("AMAZON.StopIntent".equals(intentName)) {
        PlainTextOutputSpeech outputSpeech = new PlainTextOutputSpeech();
        outputSpeech.setText("Bis bald");

        return SpeechletResponse.newTellResponse(outputSpeech);
    } else if ("AMAZON.CancelIntent".equals(intentName)) {
        PlainTextOutputSpeech outputSpeech = new PlainTextOutputSpeech();
        outputSpeech.setText("Bis bald");

        return SpeechletResponse.newTellResponse(outputSpeech);

    } else {
      return getAskResponse("NichtVerstanden", "Ich habe dich leider nicht verstanden, bitte frag mich nochmal.");
    }
  }

  @Override
  public void onSessionEnded(SpeechletRequestEnvelope<SessionEndedRequest> speechletRequestEnvelope) {

  }

  /**
   * Helper method for retrieving an Ask response with a simple card and reprompt included.
   *
   * @param cardTitle  Title of the card that you want displayed.
   * @param speechText speech text that will be spoken to the user.
   * @return the resulting card and speech text.
   */
  private SpeechletResponse getAskResponse(String cardTitle, String speechText) {
    SimpleCard card = getSimpleCard(cardTitle, speechText);
    PlainTextOutputSpeech speech = getPlainTextOutputSpeech(speechText);
    Reprompt reprompt = getReprompt(speech);

    return SpeechletResponse.newAskResponse(speech, reprompt, card);
  }

  /**
   * Helper method that creates a card object.
   *
   * @param title   title of the card
   * @param content body of the card
   * @return SimpleCard the display card to be sent along with the voice response.
   */
  private SimpleCard getSimpleCard(String title, String content) {
    SimpleCard card = new SimpleCard();
    card.setTitle(title);
    card.setContent(content);

    return card;
  }

  /**
   * Helper method for retrieving an OutputSpeech object when given a string of TTS.
   *
   * @param speechText the text that should be spoken out to the user.
   * @return an instance of SpeechOutput.
   */
  private PlainTextOutputSpeech getPlainTextOutputSpeech(String speechText) {
    PlainTextOutputSpeech speech = new PlainTextOutputSpeech();
    speech.setText(speechText);

    return speech;
  }

  /**
   * Helper method that returns a reprompt object. This is used in Ask responses where you want
   * the user to be able to respond to your speech.
   *
   * @param outputSpeech The OutputSpeech object that will be said once and repeated if necessary.
   * @return Reprompt instance.
   */
  private Reprompt getReprompt(OutputSpeech outputSpeech) {
    Reprompt reprompt = new Reprompt();
    reprompt.setOutputSpeech(outputSpeech);

    return reprompt;
  }
}
