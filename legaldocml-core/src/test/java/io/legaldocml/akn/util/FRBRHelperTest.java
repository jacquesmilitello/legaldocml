package io.legaldocml.akn.util;

import io.legaldocml.akn.element.FRBRauthor;
import io.legaldocml.akn.element.FRBRlanguage;
import io.legaldocml.akn.element.FRBRtranslation;
import io.legaldocml.akn.element.FRBRuri;
import io.legaldocml.akn.type.AgentRef;
import io.legaldocml.model.Language;
import io.legaldocml.test.LoggerInstancePostProcessor;
import io.legaldocml.test.Tests;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;

import static io.legaldocml.WriterHelper.write;
import static io.legaldocml.akn.type.Uri.raw;
import static io.legaldocml.akn.util.FRBRHelper.newFRBRauthor;
import static io.legaldocml.akn.util.FRBRHelper.newFRBRlanguage;
import static io.legaldocml.akn.util.FRBRHelper.newFRBRuri;
import static io.legaldocml.akn.util.FRBRHelper.newFRBRtranslation;
import static io.legaldocml.iso.Iso639.ENGLISH;
import static org.junit.jupiter.api.Assertions.assertEquals;

@ExtendWith(LoggerInstancePostProcessor.class)
class FRBRHelperTest {

    @Test
    void testConstructor() throws Exception {
        Tests.assertUtilClassIsWellDefined(FRBRHelper.class);
    }

    @Test
    void testNewFRBRlanguage() {
        FRBRlanguage frbr = newFRBRlanguage(ENGLISH);
        assertEquals("<FRBRlanguage language=\"eng\"/>", write(frbr));
    }

    @Test
    void testNewFRBRlanguageWithMapper() {
        FRBRlanguage frbr = newFRBRlanguage(ENGLISH, Language::getCode);
        assertEquals("<FRBRlanguage language=\"en\"/>", write(frbr));
    }

    @Test
    void testNewFRBRauthor() {
        FRBRauthor frbr = newFRBRauthor(raw("Manon"));
        assertEquals("<FRBRauthor href=\"Manon\"/>", write(frbr));
    }

    @Test
    void testNewFRBRuri() {
        FRBRuri frbr = newFRBRuri("Manon");
        assertEquals("<FRBRuri value=\"Manon\"/>", write(frbr));
    }

    @Test
    void testNewFRBRtranslation() {
        FRBRtranslation frbRtranslation = newFRBRtranslation(raw("/akn/doc/2018/eng@0001.0001"), AgentRef.valueOf("org"), "eng");
        assertEquals("<FRBRtranslation href=\"/akn/doc/2018/eng@0001.0001\" fromLanguage=\"eng\" by=\"#org\"/>", write(frbRtranslation));
    }
}
