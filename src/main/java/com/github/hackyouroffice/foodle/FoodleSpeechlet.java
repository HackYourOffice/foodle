package com.github.hackyouroffice.foodle;

import com.amazon.speech.json.SpeechletRequestEnvelope;
import com.amazon.speech.slu.Intent;
import com.amazon.speech.speechlet.*;

import com.amazon.speech.ui.OutputSpeech;
import com.amazon.speech.ui.PlainTextOutputSpeech;
import com.amazon.speech.ui.Reprompt;
import com.amazon.speech.ui.SimpleCard;
import com.google.gson.Gson;
import com.amazon.speech.ui.*;


public class FoodleSpeechlet implements SpeechletV2 {

    private static final String GENERAL_FOOD_INTEND_NAME = "LunchProposal";
    private static final String DISTANCE_INTEND_NAME = "LunchDistance";
    private static final String DURATION_INTEND_NAME = "LunchDuration";

    private final LunchProposer lunchProposer;
    private final Gson gson = new Gson();


    public FoodleSpeechlet(LunchProposer lunchProposer) {
        this.lunchProposer = lunchProposer;
    }

    @Override
    public void onSessionStarted(SpeechletRequestEnvelope<SessionStartedRequest> speechletRequestEnvelope) {

    }

    @Override
    public SpeechletResponse onLaunch(SpeechletRequestEnvelope<LaunchRequest> speechletRequestEnvelope) {
        return getAskWithSsmlResponse("Foodle",
                "<speak> Fuudel gestartet! <say-as interpret-as=\"interjection\">ding dong</say-as> </speak>");
    }

    @Override
    public SpeechletResponse onIntent(SpeechletRequestEnvelope<IntentRequest> speechletRequestEnvelope) {

        IntentRequest request = speechletRequestEnvelope.getRequest();
        final Session session = speechletRequestEnvelope.getSession();

        Intent intent = request.getIntent();
        String intentName = (intent != null) ? intent.getName() : null;

        if (GENERAL_FOOD_INTEND_NAME.equals(intentName)) {

            Proposal proposal = lunchProposer.getProposal();

            session.setAttribute("currentProposal", gson.toJson(proposal));

            return getAskResponse(proposal.getTitle(), proposal.getSpeechText());

        } else if (DISTANCE_INTEND_NAME.equals(intentName)) {

            Proposal currentProposal = getCurrentProposal(session);
            return getAskResponse("Distance", currentProposal.getWayTimeInfoText());

        } else if (DURATION_INTEND_NAME.equals(intentName)) {

            Proposal currentProposal = getCurrentProposal(session);
            return getAskResponse("Duration", currentProposal.getCompleteLunchDuration());

        } else if ("AMAZON.StopIntent".equals(intentName)) {
            PlainTextOutputSpeech outputSpeech = new PlainTextOutputSpeech();
            outputSpeech.setText("Ich bin raus");

            return SpeechletResponse.newTellResponse(outputSpeech);
        } else if ("AMAZON.CancelIntent".equals(intentName)) {
            PlainTextOutputSpeech outputSpeech = new PlainTextOutputSpeech();
            outputSpeech.setText("Over and out");

            return SpeechletResponse.newTellResponse(outputSpeech);
        } else if ("DeployProd".equals(intentName)) {
            PlainTextOutputSpeech outputSpeech = new PlainTextOutputSpeech();
            outputSpeech.setText("In Ordnung, ich schiebe vor dem Essen noch schnell alles auf Produktion");

            return SpeechletResponse.newTellResponse(outputSpeech);
        } else {
            return getAskResponse("Nicht Verstanden", "Aaaalter, sprichst du Hochdeutsch?");
        }
    }

    private Proposal getCurrentProposal(Session session) {
        return gson.fromJson((String) session.getAttribute("currentProposal"),Proposal.class);
    }

    @Override
    public void onSessionEnded(SpeechletRequestEnvelope<SessionEndedRequest> speechletRequestEnvelope) {

    }


    private SpeechletResponse getAskWithSsmlResponse(String cardTitle, String speechText) {
        SimpleCard card = getSimpleCard(cardTitle, speechText);
        SsmlOutputSpeech speech = getSsmlTextOutputSpeech(speechText);
        Reprompt reprompt = getReprompt(speech);

        return SpeechletResponse.newAskResponse(speech, reprompt, card);
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

    private SsmlOutputSpeech getSsmlTextOutputSpeech(String speechText) {
        SsmlOutputSpeech speech = new SsmlOutputSpeech();
        speech.setSsml(speechText);

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
