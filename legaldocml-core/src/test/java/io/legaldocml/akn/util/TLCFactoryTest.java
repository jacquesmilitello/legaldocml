package io.legaldocml.akn.util;

import io.legaldocml.akn.element.TLCLocation;
import io.legaldocml.akn.element.TLCTerm;
import io.legaldocml.akn.type.NoWhiteSpace;
import io.legaldocml.akn.type.Uri;
import io.legaldocml.test.LoggerInstancePostProcessor;
import io.legaldocml.test.Tests;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;

import static io.legaldocml.WriterHelper.write;

@ExtendWith(LoggerInstancePostProcessor.class)
class TLCFactoryTest {

    @Test
    void testFactoryClass() throws Exception {
        Tests.assertUtilClassIsWellDefined(TLCFactory.class);
    }

    @Test
    void testTLCLocation() {
        TLCLocation location = TLCFactory.newTLCLocation(NoWhiteSpace.valueOf("Sotaqui"),
                Uri.raw("/cl/division-politico-administrativa/2010/comuna/ovalle"), "Sotaqui");

        Assertions.assertEquals("<TLCLocation eId=\"Sotaqui\" href=\"/cl/division-politico-administrativa/2010/comuna/ovalle\" showAs=\"Sotaqui\"/>", write(location));
    }


    @Test
    void testTLCTerm() {
        TLCTerm term = TLCFactory.newTLCTerm(NoWhiteSpace.valueOf("hello"),
                Uri.raw("/ontology/term/hello"), "bonjour");

        Assertions.assertEquals("<TLCTerm eId=\"hello\" href=\"/ontology/term/hello\" showAs=\"bonjour\"/>", write(term));
    }

}
