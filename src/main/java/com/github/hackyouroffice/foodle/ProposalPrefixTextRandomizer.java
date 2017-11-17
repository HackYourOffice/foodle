package com.github.hackyouroffice.foodle;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class ProposalPrefixTextRandomizer {

    private List<String> texts = Arrays.asList(
        "Geh heute mal zu ",
        "Wie wär's mit ",
        "Lass doch mal wieder zu ",
        "Ey, heute zu ",
        "Heute auf jeden Fall zu ");

    public String randomTextPrefix() {
        Collections.shuffle(texts);
        return texts.get(0);
    }
}
