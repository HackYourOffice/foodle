package com.github.hackyouroffice.foodle;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.Arrays;

import static org.hamcrest.CoreMatchers.anything;
import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;


@RunWith(MockitoJUnitRunner.class)
public class LunchProposerTest {

    private LunchProposer lunchProposer;

    @Mock
    private GooglePlacesLunchLocationFinder googlePlacesLunchLocationFinder;

    @Mock
    private KnownLocationsLunchLocationFinder knownLocationsLunchLocationFinder;

    @Mock
    private ProposalPrefixTextRandomizer proposalPrefixTextRandomizer;

    @Before
    public void setup() {
        lunchProposer = new LunchProposer(googlePlacesLunchLocationFinder, knownLocationsLunchLocationFinder, proposalPrefixTextRandomizer);
    }

    @Test
    public void getProposal() throws Exception {

        when(knownLocationsLunchLocationFinder.findLocations()).thenReturn(Arrays.asList(new Location("a", "",0,true,false)));
        when(googlePlacesLunchLocationFinder.findLocations()).thenReturn(Arrays.asList(new Location("b","",0,true,false)));
        when(proposalPrefixTextRandomizer.randomTextPrefix()).thenReturn("blubb");

        final Proposal proposal = lunchProposer.getProposal();

        assertThat(proposal.getSpeechText(), is(anything()));

        verify(googlePlacesLunchLocationFinder).findLocations();
        verify(knownLocationsLunchLocationFinder).findLocations();
    }

}
