package io.legaldocml.business.builder;

import io.legaldocml.akn.element.Debate;
import io.legaldocml.business.BusinessProvider;
import io.legaldocml.io.XmlProvider;
import io.legaldocml.iso.Iso639;
import io.legaldocml.test.SonarJUnit4ClassRunner;
import org.junit.Test;
import org.junit.runner.RunWith;

import java.io.IOException;
import java.nio.channels.Channels;
import java.time.LocalDate;

@RunWith(SonarJUnit4ClassRunner.class)
public class MetaBuilderTest {

    @Test
    public void testSetAknIdentifier() throws IOException {
        BusinessProvider provider = BusinessProvider.businessProvider("default");
        BusinessBuilder<Debate> builder = provider.newBuilder(Debate.ELEMENT);
        builder.getMetaBuilder().setAknIdentifier(provider.newAknIdentifier("work", "expression", "manifestation"));
        builder.getMetaBuilder().addLanguage(Iso639.ENGLISH);
        builder.getMetaBuilder().setDate(LocalDate.of(2011, 3, 9));

        XmlProvider.writerFactory(3).writePermissive(Channels.newChannel(System.out), builder.getAkomaNtoso());

    }
}
