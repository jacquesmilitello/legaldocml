package io.legaldocml.business.impl;

import io.legaldocml.akn.AkomaNtoso;
import io.legaldocml.akn.element.Amendment;
import io.legaldocml.business.AknIdentifier;
import io.legaldocml.business.AknIdentifierException;
import io.legaldocml.module.akn.DefaultAkomaNtosoContext;
import io.legaldocml.test.LoggerInstancePostProcessor;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotSame;
import static org.junit.jupiter.api.Assertions.assertTrue;

@ExtendWith(LoggerInstancePostProcessor.class)
class AknIdentifierTest {

    @Test
    void applyTest() {

        AknIdentifier identifier = new DefaultAknIdentifier("work001", "expression002", "manifestation003", "/");
        AkomaNtoso<Amendment> akn = new AkomaNtoso<>(new DefaultAkomaNtosoContext());
        akn.setDocumentType(new Amendment());

        identifier.apply(akn);

        assertEquals("work001", akn.getDocumentType().getMeta().getIdentification().getFRBRWork().getFRBRthis().getValue());
        assertEquals("work001/expression002", akn.getDocumentType().getMeta().getIdentification().getFRBRExpression().getFRBRthis().getValue());
        assertEquals("work001/expression002/manifestation003", akn.getDocumentType().getMeta().getIdentification().getFRBRManifestation().getFRBRthis().getValue());
    }

    @Test
    void consistentTest() {

        AkomaNtoso<Amendment> akn =  new AkomaNtoso<>(new DefaultAkomaNtosoContext());
        akn.setDocumentType(new Amendment());

        AknIdentifier identifier = new DefaultAknIdentifier("work001", "expression002", "manifestation003", "/");
        identifier.apply(akn);

        AknIdentifier.consistent(akn);

        akn.getDocumentType().getMeta().getIdentification().getFRBRExpression().getFRBRthis().setValue("toto");

        try {
            AknIdentifier.consistent(akn);
            Assertions.fail("");
        } catch (AknIdentifierException cause) {
            assertTrue(true);
        }

    }

    @Test
    void extractTest() {

        AkomaNtoso<Amendment> akn = new AkomaNtoso<>(new DefaultAkomaNtosoContext());
        akn.setDocumentType(new Amendment());

        AknIdentifier identifier = new DefaultAknIdentifier("work001", "expression002", "manifestation003", "/");
        identifier.apply(akn);

        AknIdentifier id = AknIdentifier.extract("default", akn);

        assertEquals("work001", id.work());
        assertEquals("work001/expression002", id.expression());
        assertEquals("expression002", id.expressionPart());
        assertEquals("work001/expression002/manifestation003", id.manifestation());
        assertEquals("manifestation003", id.manifestationPart());

    }

    @Test
    void isEmptyTest() {

        AkomaNtoso<Amendment> akn = new AkomaNtoso<>(new DefaultAkomaNtosoContext());
        akn.setDocumentType(new Amendment());

        assertTrue(AknIdentifier.isEmpty(akn));

        new DefaultAknIdentifier("work001", "expression002", "manifestation003", "/").apply(akn);

        assertFalse(AknIdentifier.isEmpty(akn));
    }

    @Test
    void testEqualsAndHashCode() {

        AknIdentifier identifier = new DefaultAknIdentifier("work001", "expression002", "manifestation003", "/");
        AknIdentifier identifier1 = new DefaultAknIdentifier("work001", "expression002", "manifestation003", "/");

        assertEquals(identifier, identifier1);
        assertEquals(identifier.hashCode(), identifier1.hashCode());
        assertNotSame(identifier, identifier1);
    }

}