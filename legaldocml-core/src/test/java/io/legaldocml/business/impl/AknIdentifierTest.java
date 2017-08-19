package io.legaldocml.business.impl;

import io.legaldocml.akn.AkomaNtoso;
import io.legaldocml.akn.element.Amendment;
import io.legaldocml.business.AknIdentifier;
import io.legaldocml.business.AknIdentifierException;
import io.legaldocml.module.akn.v3.AkomaNtosoContextV3;
import org.junit.Assert;
import org.junit.Test;

public class AknIdentifierTest {

    @Test
    public void applyTest() {

        AknIdentifier identifier = new DefaultAknIdentifier("work001", "expression002", "manifestation003", "/");
        AkomaNtoso<Amendment> akn = new AkomaNtoso<>(new AkomaNtosoContextV3());
        akn.setDocumentType(new Amendment());

        identifier.apply(akn);

        Assert.assertEquals("work001", akn.getDocumentType().getMeta().getIdentification().getFRBRWork().getFRBRthis().getValue());
        Assert.assertEquals("work001/expression002", akn.getDocumentType().getMeta().getIdentification().getFRBRExpression().getFRBRthis().getValue());
        Assert.assertEquals("work001/expression002/manifestation003", akn.getDocumentType().getMeta().getIdentification().getFRBRManifestation().getFRBRthis().getValue());
    }

    @Test
    public void consistentTest() {

        AkomaNtoso<Amendment> akn =  new AkomaNtoso<>(new AkomaNtosoContextV3());
        akn.setDocumentType(new Amendment());

        AknIdentifier identifier = new DefaultAknIdentifier("work001", "expression002", "manifestation003", "/");
        identifier.apply(akn);

        AknIdentifier.consistent(akn);

        akn.getDocumentType().getMeta().getIdentification().getFRBRExpression().getFRBRthis().setValue("toto");

        try {
            AknIdentifier.consistent(akn);
            Assert.fail();
        } catch (AknIdentifierException cause) {
            Assert.assertTrue(true);
        }

    }

    @Test
    public void extractTest() {

        AkomaNtoso<Amendment> akn = new AkomaNtoso<>(new AkomaNtosoContextV3());
        akn.setDocumentType(new Amendment());

        AknIdentifier identifier = new DefaultAknIdentifier("work001", "expression002", "manifestation003", "/");
        identifier.apply(akn);

        AknIdentifier id = AknIdentifier.extract(akn);

        Assert.assertEquals("work001", id.work());
        Assert.assertEquals("work001/expression002", id.expression());
        Assert.assertEquals("expression002", id.expressionPart());
        Assert.assertEquals("work001/expression002/manifestation003", id.manifestation());
        Assert.assertEquals("manifestation003", id.manifestationPart());

    }

    @Test
    public void isEmptyTest() {

        AkomaNtoso<Amendment> akn = new AkomaNtoso<>(new AkomaNtosoContextV3());
        akn.setDocumentType(new Amendment());

        Assert.assertTrue(AknIdentifier.isEmpty(akn));

        new DefaultAknIdentifier("work001", "expression002", "manifestation003", "/").apply(akn);

        Assert.assertFalse(AknIdentifier.isEmpty(akn));
    }

    @Test
    public void testEqualsAndHashCode() {

        AknIdentifier identifier = new DefaultAknIdentifier("work001", "expression002", "manifestation003", "/");
        AknIdentifier identifier1 = new DefaultAknIdentifier("work001", "expression002", "manifestation003", "/");

        Assert.assertEquals(identifier, identifier1);
        Assert.assertEquals(identifier.hashCode(), identifier1.hashCode());
        Assert.assertNotSame(identifier, identifier1);
    }

}