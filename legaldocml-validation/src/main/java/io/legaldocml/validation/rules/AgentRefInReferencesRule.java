package io.legaldocml.validation.rules;

import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import io.legaldocml.akn.element.Meta;
import io.legaldocml.akn.element.RefItem;
import io.legaldocml.akn.element.References;
import io.legaldocml.akn.type.AgentRef;
import io.legaldocml.akn.type.NoWhiteSpace;
import io.legaldocml.util.ListIterable;
import io.legaldocml.validation.ValidationContext;
import io.legaldocml.validation.Rule;

/**
 * @author <a href="mailto:jacques.militello@gmail.com">Jacques Militello</a>
 */
final class AgentRefInReferencesRule implements Rule {

	/**
	 * SLF4J Logger.
	 */
	private static final Logger LOGGER = LoggerFactory.getLogger(AgentRefInReferencesRule.class);

	/**
	 * Singleton Instance.
	 */
	static final public AgentRefInReferencesRule INSTANCE = new AgentRefInReferencesRule();
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public void apply(ValidationContext context) {

		Meta meta = context.getAkomaNtoso().getDocumentType().getMeta();

		checkIdentification(context, meta);

	}

	private void checkIdentification(ValidationContext context, Meta meta) {

		AgentRef source = meta.getIdentification().getSource();

		if (LOGGER.isDebugEnabled()) {
			LOGGER.debug("check identification -> source [{}]", source);
		}
		
		if (source == null) {
			context.addError(this, meta.getIdentification(), "Missing @source");
			return;
		}
		
		if (!source.isRef()) {
			if (LOGGER.isDebugEnabled()) {
				LOGGER.debug("check identification -> source [{}] -> not a ref -> skip.", source);
			}
			return;
		}
		
		NoWhiteSpace eId = source.toEID();
		
		// @formatter:off
		Optional<RefItem> optional = meta.getReferences()
				.stream()
			    .map(References::getRefItems)
			    .flatMap(ListIterable::stream)
			    .filter(item -> eId.equals(item.getEid()))
			    .findFirst();
		// @formatter:on

		if (optional.isPresent()) {
			if (LOGGER.isDebugEnabled()) {
				LOGGER.debug("@source [{}] found in [{}]", source, optional.get());
			}
		} else {
			context.addError(this, meta.getIdentification(), "@source [" + source + "] not found");	
		}
		
		
	}

}
