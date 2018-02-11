package io.legaldocml.validation.rules;

import java.io.IOException;

import io.legaldocml.akn.element.Doc;
import io.legaldocml.akn.type.AgentRef;
import io.legaldocml.module.akn.v3.AkomaNtosoContextV3;
import io.legaldocml.test.LoggerInstancePostProcessor;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import io.legaldocml.akn.AkomaNtoso;
import io.legaldocml.io.XmlProvider;
import io.legaldocml.test.PathForTest;
import io.legaldocml.validation.ValidationContext;
import io.legaldocml.validation.impl.Validations;
import org.junit.jupiter.api.extension.ExtendWith;

@ExtendWith(LoggerInstancePostProcessor.class)
class AgentRefInReferencesRuleTest {

	@Test
	void testNotFound() throws IOException {
		AkomaNtoso<?> akn = XmlProvider.readerFactory().read(PathForTest.path("/xml/v3/cl_Sesion56_2.xml"));
		ValidationContext context =  Validations.context(akn);
		Rules.agentRefInReferences().apply(context);
		Assertions.assertEquals(1,context.getErrorCount());
	}
	
	@Test
	void testOK() throws IOException {
		AkomaNtoso<?> akn = XmlProvider.readerFactory().read(PathForTest.path("/xml/v3/eu_COM(2013)0619_EN-8.xml"));
		ValidationContext context =  Validations.context(akn);
		Rules.agentRefInReferences().apply(context);
		Assertions.assertEquals(0,context.getErrorCount());
	}

	@Test
    void testEmptySource()  {
	    AkomaNtoso<Doc> akn = new AkomaNtoso<>(new AkomaNtosoContextV3());
        akn.setDocumentType(new Doc());
        ValidationContext context =  Validations.context(akn);
        Rules.agentRefInReferences().apply(context);
        Assertions.assertEquals(1,context.getErrorCount());
    }

    @Test
    void testNoRefSource()  {
        AkomaNtoso<Doc> akn = new AkomaNtoso<>(new AkomaNtosoContextV3());
        akn.setDocumentType(new Doc());
        akn.getDocumentType().getMeta().getIdentification().setSource(AgentRef.raw("toto".toCharArray()));
        ValidationContext context =  Validations.context(akn);
        Rules.agentRefInReferences().apply(context);
        Assertions.assertEquals(0,context.getErrorCount());
    }
}
