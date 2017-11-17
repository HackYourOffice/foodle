package com.github.hackyouroffice.foodle;

import com.github.hackyouroffice.foodle.storage.ProposedLocation;
import org.junit.Test;

import java.util.Arrays;
import java.util.List;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

public class RecentlyProposedPredicateTest {

    @Test
    public void testPositive() {
        List<ProposedLocation> blacklist = Arrays.asList(new ProposedLocation(1L, new Location("Grieche")));
        RecentlyProposedPredicate recentlyProposedPredicate = new RecentlyProposedPredicate(blacklist);
        Location location = new Location("Ritter");
        assertThat(recentlyProposedPredicate.test(location), is(false));
    }

    @Test
    public void testNegative() {
        List<ProposedLocation> blacklist = Arrays.asList(new ProposedLocation(1L, new Location("Ritter")));
        RecentlyProposedPredicate recentlyProposedPredicate = new RecentlyProposedPredicate(blacklist);
        Location location = new Location("Ritter");
        assertThat(recentlyProposedPredicate.test(location), is(true));
    }
}
