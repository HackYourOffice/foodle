package com.github.hackyouroffice.foodle;

import com.amazon.speech.json.SpeechletRequestEnvelope;
import com.amazon.speech.slu.Intent;
import com.amazon.speech.speechlet.IntentRequest;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class FoodleSpeechletTest {

  @Mock
  private LunchProposer lunchProposer;

  @Test
  public void onIntent() throws Exception {

    when(lunchProposer.propose()).thenReturn(new Proposal("foo", "bar"));

    final FoodleSpeechlet foodleSpeechlet = new FoodleSpeechlet(lunchProposer);

    final Intent intent = Intent.builder().withName("LunchProposal").build();

    foodleSpeechlet.onIntent(wrapIntend(intent));

    verify(lunchProposer).propose();
  }

  private SpeechletRequestEnvelope<IntentRequest> wrapIntend(Intent intent) {
    final IntentRequest intentRequest = IntentRequest.builder().withRequestId("1").withIntent(intent).build();
    return SpeechletRequestEnvelope.<IntentRequest>builder()
          .withRequest(intentRequest).build();
  }

}
