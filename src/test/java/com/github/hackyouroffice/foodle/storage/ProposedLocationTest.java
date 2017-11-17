package com.github.hackyouroffice.foodle.storage;

import com.github.hackyouroffice.foodle.Location;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.*;


public class ProposedLocationTest {

    @Test
    public void compare() throws Exception {

        final ProposedLocation p1 = new ProposedLocation(1L, new Location("a"));
        final ProposedLocation p2 = new ProposedLocation(2L, new Location("b"));

        List<ProposedLocation> list = Arrays.asList(p1, p2);

        Collections.sort(list);

        assertThat(list.get(0).getLocation().getName(), is("b"));
    }

}
