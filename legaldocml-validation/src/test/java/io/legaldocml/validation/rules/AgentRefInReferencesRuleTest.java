package io.legaldocml.validation.rules;

import java.io.IOException;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import io.legaldocml.akn.AkomaNtoso;
import io.legaldocml.io.XmlProvider;
import io.legaldocml.test.PathForTest;
import io.legaldocml.validation.ValidationContext;
import io.legaldocml.validation.impl.Validations;

public class AgentRefInReferencesRuleTest {

	@Test
	public void testNotFound() throws IOException {
		AkomaNtoso<?> akn = XmlProvider.readerFactory().read(PathForTest.path("/xml/v3/cl_Sesion56_2.xml"));
		ValidationContext context =  Validations.context(akn);
		Rules.agentRefInReferences().apply(context);
		Assertions.assertEquals(1,context.getErrorCount());
	}
	
	@Test
	public void testOK() throws IOException {
		AkomaNtoso<?> akn = XmlProvider.readerFactory().read(PathForTest.path("/xml/v3/eu_COM(2013)0619_EN-8.xml"));
		ValidationContext context =  Validations.context(akn);
		Rules.agentRefInReferences().apply(context);
		Assertions.assertEquals(0,context.getErrorCount());
	}
	
}
