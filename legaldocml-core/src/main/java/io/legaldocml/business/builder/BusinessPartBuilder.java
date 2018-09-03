package io.legaldocml.business.builder;

import io.legaldocml.akn.AknObject;
import io.legaldocml.akn.DocumentType;

/**
 * @author <a href="mailto:jacques.militello@gmail.com">Jacques Militello</a>
 */
public interface BusinessPartBuilder<T extends AknObject> {

   BusinessBuilder<? extends DocumentType, ? extends BusinessBuilderAkomaNtosoContext> businessBuilder();

   T parent();
   
}
