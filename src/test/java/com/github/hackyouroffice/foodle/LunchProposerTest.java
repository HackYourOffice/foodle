package com.github.hackyouroffice.foodle;

import com.github.hackyouroffice.foodle.storage.ProposedLocationDataItem;
import com.github.hackyouroffice.foodle.storage.LocationsDynamoDbClient;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import java.time.Clock;
import java.time.Instant;
import java.time.ZoneId;
import java.util.Arrays;

import static org.hamcrest.CoreMatchers.anything;
import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;
import static org.mockito.ArgumentMatchers.any;
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
    private LocationsDynamoDbClient locationsDynamoDbClient;

    private Clock clock;

    @Before
    public void setup() {
        clock = Clock.fixed(Instant.EPOCH, ZoneId.systemDefault());
        lunchProposer = new LunchProposer(googlePlacesLunchLocationFinder, knownLocationsLunchLocationFinder, locationsDynamoDbClient, clock);
    }

    @Test
    public void getProposal() throws Exception {

        when(knownLocationsLunchLocationFinder.findLocations()).thenReturn(Arrays.asList(new Location("a"), new Location("b")));
        when(googlePlacesLunchLocationFinder.findLocations()).thenReturn(Arrays.asList(new Location("z"), new Location("y")));

        final Proposal proposal = lunchProposer.getProposal();

        assertThat(proposal.getText(), is(anything()));

        verify(locationsDynamoDbClient).loadItem(any(ProposedLocationDataItem.class));
        verify(locationsDynamoDbClient).saveItem(any(ProposedLocationDataItem.class));
        verify(googlePlacesLunchLocationFinder).findLocations();
        verify(knownLocationsLunchLocationFinder).findLocations();
    }

    @Test
    public void getProposalDontUseLastTwo() throws Exception {

        when(knownLocationsLunchLocationFinder.findLocations()).thenReturn(Arrays.asList(new Location("a"), new Location("b")));
        when(googlePlacesLunchLocationFinder.findLocations()).thenReturn(Arrays.asList(new Location("z"), new Location("y")));

        final Proposal proposal = lunchProposer.getProposal();



    }

}
