package io.legaldocml.akn.util;

import io.legaldocml.akn.element.FRBRauthor;
import io.legaldocml.akn.element.FRBRlanguage;
import io.legaldocml.akn.element.FRBRuri;
import io.legaldocml.akn.type.Uri;
import io.legaldocml.iso.Iso639;
import io.legaldocml.model.Language;
import io.legaldocml.test.LoggerInstancePostProcessor;
import io.legaldocml.test.Tests;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;

import static io.legaldocml.WriterHelper.write;
import static org.junit.jupiter.api.Assertions.assertEquals;

@ExtendWith(LoggerInstancePostProcessor.class)
public class FRBRHelperTest {

    @Test
    public void testConstructor() throws Exception {
        Tests.assertUtilClassIsWellDefined(FRBRHelper.class);
    }

    @Test
    public void testNewFRBRlanguage() {
        FRBRlanguage frbr = FRBRHelper.newFRBRlanguage(Iso639.ENGLISH);
        assertEquals("<FRBRlanguage language=\"eng\"/>", write(frbr));
    }

    @Test
    public void testNewFRBRlanguageWithMapper() {
        FRBRlanguage frbr = FRBRHelper.newFRBRlanguage(Iso639.ENGLISH, Language::getCode);
        assertEquals("<FRBRlanguage language=\"en\"/>", write(frbr));
    }

    @Test
    public void testNewFRBRauthor() {
        FRBRauthor frbr = FRBRHelper.newFRBRauthor(Uri.raw("Manon"));
        assertEquals("<FRBRauthor href=\"Manon\"/>", write(frbr));
    }

    @Test
    public void testNewFRBRuri() {
        FRBRuri frbr = FRBRHelper.newFRBRuri("Manon");
        assertEquals("<FRBRuri value=\"Manon\"/>", write(frbr));
    }

}
