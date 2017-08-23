package io.legaldocml.business.builder;

import io.legaldocml.akn.element.Debate;
import io.legaldocml.business.BusinessProvider;
import io.legaldocml.io.XmlProvider;
import io.legaldocml.iso.Language;
import org.junit.Test;

import java.io.IOException;
import java.nio.channels.Channels;
import java.time.LocalDate;

public class MetaBuilderTest {

    @Test
    public void testSetAknIdentifier() throws IOException {
        BusinessProvider provider = BusinessProvider.businessProvider("default");
        BusinessBuilder<Debate> builder = provider.newBuilder(Debate.ELEMENT);
        builder.getMetaBuilder().setAknIdentifier(provider.newAknIdentifier("work", "expression", "manifestation"));
        builder.getMetaBuilder().addLanguage(Language.EN);
        builder.getMetaBuilder().setDate(LocalDate.of(2011, 3, 9));

        XmlProvider.writerFactory(3).writePermissive(Channels.newChannel(System.out), builder.getAkomaNtoso());

    }
}
