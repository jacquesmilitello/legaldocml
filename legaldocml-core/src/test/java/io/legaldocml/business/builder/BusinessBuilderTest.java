package io.legaldocml.business.builder;

import io.legaldocml.akn.AknElements;
import io.legaldocml.akn.element.FRBRlanguage;
import io.legaldocml.business.BusinessProvider;
import io.legaldocml.iso.Iso639;
import io.legaldocml.test.LoggerInstancePostProcessor;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;

/**
 * @author <a href="mailto:jacques.militello@gmail.com">Jacques Militello</a>
 */
@ExtendWith(LoggerInstancePostProcessor.class)
class BusinessBuilderTest {

    @Test
    void testMainLanguage() {
        BusinessBuilder businessBuilder = BusinessProvider.businessProvider("default").newBuilder(AknElements.DOC);
        businessBuilder.setMainLanguage(Iso639.ENGLISH);

        Iterable<FRBRlanguage> languages = businessBuilder.getAkomaNtoso().getDocumentType().getMeta().getIdentification().getFRBRExpression().getLanguages();
        Assertions.assertTrue(languages.iterator().hasNext());
        FRBRlanguage language = languages.iterator().next();
        Assertions.assertEquals(Iso639.ENGLISH.getTerminology(), language.getLanguage());

    }

    @Test
    void testMainLanguageAlreadyExists() {
        BusinessBuilder businessBuilder = BusinessProvider.businessProvider("default").newBuilder(AknElements.DOC);
        businessBuilder.setMainLanguage(Iso639.ENGLISH);

        Assertions.assertThrows(BusinessBuilderException.class, () -> businessBuilder.setMainLanguage(Iso639.FRENCH));
    }

}
