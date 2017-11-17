package com.github.hackyouroffice.foodle;

import org.junit.Test;

import static org.hamcrest.CoreMatchers.anything;
import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

public class ProposalPrefixTextRandomizerTest {

    @Test
    public void randomPrefix() {

        final ProposalPrefixTextRandomizer proposalPrefixTextRandomizer = new ProposalPrefixTextRandomizer();
        assertThat(proposalPrefixTextRandomizer.randomTextPrefix(), is(anything()));
    }
}
