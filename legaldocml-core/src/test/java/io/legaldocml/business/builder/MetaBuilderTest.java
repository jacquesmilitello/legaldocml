package io.legaldocml.business.builder;

import io.legaldocml.akn.element.Debate;
import io.legaldocml.business.BusinessProvider;
import io.legaldocml.iso.Language;
import org.junit.Test;

public class MetaBuilderTest {

    @Test
    public void testSetAknIdentifier() {
        BusinessProvider provider = BusinessProvider.businessProvider("default");
        BusinessBuilder<Debate> builder = provider.newBuilder(Debate.ELEMENT);
        builder.getMetaBuilder().setAknIdentifier(provider.newAknIdentifier("work","expression","manifestation"));
        builder.getMetaBuilder().setLanguage(Language.EN);
    }
}
