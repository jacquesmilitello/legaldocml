package io.legaldocml.business.impl;

import io.legaldocml.LegalDocMlException;
import io.legaldocml.akn.element.Debate;
import io.legaldocml.business.BusinessProvider;
import io.legaldocml.business.builder.BusinessBuilder;
import io.legaldocml.iso.Language;
import org.junit.Test;

import java.io.IOException;
import java.nio.channels.Channels;
import java.util.List;

import static io.legaldocml.io.XmlProvider.writerFactory;

public class BusinessBuilderTest {

    @Test
    public void testDebate() throws IOException {

        BusinessProvider provider = BusinessProvider.businessProvider("default");
        BusinessBuilder<Debate> builder = provider.newBuilder(Debate.ELEMENT);

        builder.getMetaBuilder().setAknIdentifier(provider.newAknIdentifierTransient());
        builder.getMetaBuilder().addLanguage(Language.EN);

        List<LegalDocMlException> exceptions = writerFactory(3).writePermissive(Channels.newChannel(System.out), builder.getAkomaNtoso());

        exceptions.forEach(System.out::println);
    }
}
