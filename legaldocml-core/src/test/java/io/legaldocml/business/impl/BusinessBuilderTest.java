package io.legaldocml.business.impl;

import io.legaldocml.LegalDocMlException;
import io.legaldocml.akn.element.Debate;
import io.legaldocml.business.builder.BusinessBuilder;
import io.legaldocml.iso.Language;
import org.junit.Test;

import java.io.IOException;
import java.nio.channels.Channels;
import java.util.List;

import static io.legaldocml.business.BusinessProvider.newAknIdentifierTransient;
import static io.legaldocml.business.BusinessProvider.newBusinessBuilder;
import static io.legaldocml.io.XmlProvider.writerFactory;

public class BusinessBuilderTest {

    @Test
    public void testDebate() throws IOException {

        BusinessBuilder<Debate> builder  = newBusinessBuilder("toto", Debate.class);
        builder.getMetaBuilder().setAknIdentifier(newAknIdentifierTransient());
        builder.getMetaBuilder().setLanguage(Language.EN);


        List<LegalDocMlException> exceptions = writerFactory(3).writePermissive(Channels.newChannel(System.out), builder.getAkomaNtoso());

        exceptions.forEach(System.out::println);
    }
}
