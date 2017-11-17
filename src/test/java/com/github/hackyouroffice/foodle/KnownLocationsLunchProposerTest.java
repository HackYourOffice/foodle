package com.github.hackyouroffice.foodle;

import org.hamcrest.core.IsInstanceOf;
import org.junit.Before;
import org.junit.Test;
import org.mockito.ArgumentMatchers;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

public class KnownLocationsLunchProposerTest {

  private KnownLocationsLunchProposer knownLocationsLunchProposer;

  @Before
  public void setup() {
    knownLocationsLunchProposer = new KnownLocationsLunchProposer();
  }

  @Test
  public void propose() throws Exception {

    final Proposal propose = knownLocationsLunchProposer.propose();

    assertThat(propose.getText(), is(IsInstanceOf.any(String.class)));
  }

}
