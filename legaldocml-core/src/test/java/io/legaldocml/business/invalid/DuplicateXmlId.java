package io.legaldocml.business.invalid;

import io.legaldocml.ReaderHelper;
import io.legaldocml.akn.XmlValidationException;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

class DuplicateXmlId {

    @Test
    void testDuplicate() {
        XmlValidationException exception = assertThrows(XmlValidationException.class, () -> ReaderHelper.read("/xml/invalid/sameXmlId.xml"));
        assertEquals(XmlValidationException.Type.DUPLICATE_XML_ID, exception.getType());
    }
}
