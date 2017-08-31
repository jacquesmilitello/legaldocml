package io.legaldocml.business.impl;

import io.legaldocml.LegalDocMlException;
import io.legaldocml.akn.element.Debate;
import io.legaldocml.business.BusinessProvider;
import io.legaldocml.business.builder.BusinessBuilder;
import io.legaldocml.iso.Iso639;
import io.legaldocml.test.SonarJUnit4ClassRunner;
import org.junit.Test;
import org.junit.runner.RunWith;

import java.io.IOException;
import java.nio.channels.Channels;
import java.util.List;

import static io.legaldocml.io.XmlProvider.writerFactory;

@RunWith(SonarJUnit4ClassRunner.class)
public class BusinessBuilderTest {

    @Test
    public void testDebate() throws IOException {

        BusinessProvider provider = BusinessProvider.businessProvider("default");
        BusinessBuilder builder = provider.newBuilder(Debate.ELEMENT);

        builder.getMetaBuilder().setAknIdentifier(provider.newAknIdentifierTransient());
        builder.getMetaBuilder().addLanguage(Iso639.ENGLISH);

        List<LegalDocMlException> exceptions = writerFactory(3).writePermissive(Channels.newChannel(System.out), builder.getAkomaNtoso());

        exceptions.forEach(System.out::println);
    }
}
