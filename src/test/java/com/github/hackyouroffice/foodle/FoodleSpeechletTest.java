package com.github.hackyouroffice.foodle;

import com.amazon.speech.json.SpeechletRequestEnvelope;
import com.amazon.speech.slu.Intent;
import com.amazon.speech.speechlet.Application;
import com.amazon.speech.speechlet.IntentRequest;
import com.amazon.speech.speechlet.Session;
import com.amazon.speech.speechlet.User;
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

    when(lunchProposer.getProposal()).thenReturn(
            new Proposal("foo",
                    new Location("bar", "address", 1, true, false)
            ));

    final FoodleSpeechlet foodleSpeechlet = new FoodleSpeechlet(lunchProposer);
    final Intent intent = Intent.builder().withName("LunchProposal").build();

    foodleSpeechlet.onIntent(wrapIntend(intent));

    verify(lunchProposer).getProposal();
  }

  private SpeechletRequestEnvelope<IntentRequest> wrapIntend(Intent intent) {
    final IntentRequest intentRequest = IntentRequest.builder().withRequestId("1").withIntent(intent).build();
    Session session =
            Session.builder()
                    .withSessionId("sId")
                    .withApplication(new Application("applicationId"))
                    .withUser(new User("UserId"))
                    .build();
    return SpeechletRequestEnvelope.<IntentRequest>builder()
          .withRequest(intentRequest).withSession(session).build();
  }

}
