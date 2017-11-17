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

    @Before
    public void setup() {
        lunchProposer = new LunchProposer(googlePlacesLunchLocationFinder, knownLocationsLunchLocationFinder);
    }

    @Test
    public void getProposal() throws Exception {

        when(knownLocationsLunchLocationFinder.findLocations()).thenReturn(Arrays.asList(new Location("a", "",0,true,false)));
        when(googlePlacesLunchLocationFinder.findLocations()).thenReturn(Arrays.asList(new Location("b","",0,true,false)));

        final Proposal proposal = lunchProposer.getProposal();

        assertThat(proposal.getLocation().getName(), is(anything()));

        verify(googlePlacesLunchLocationFinder).findLocations();
        verify(knownLocationsLunchLocationFinder).findLocations();
    }

}
