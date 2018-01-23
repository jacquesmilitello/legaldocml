package io.legaldocml.akn.util;

import io.legaldocml.akn.element.Meta;
import io.legaldocml.akn.element.Proprietary;
import io.legaldocml.akn.element.References;
import io.legaldocml.akn.type.AgentRef;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author <a href="mailto:jacques.militello@gmail.com">Jacques Militello</a>
 */
public final class Metas {

    /**
     * SLF4J Logger.
     */
    private static final Logger LOGGER = LoggerFactory.getLogger(Metas.class);

    private Metas() {
    }

    public static References references(Meta meta, AgentRef source) {
        References ref = meta.getReferences(source);

        if (ref == null) {

            if (LOGGER.isDebugEnabled()) {
                LOGGER.debug("meta.references not found for agentRef [{]] -> create", source);
            }

            ref = new References();
            ref.setSource(source);
            meta.add(ref);
        }

        return ref;
    }
    
    public static Proprietary proprietary(Meta meta, AgentRef source) {
    	
    	Proprietary proprietary = meta.getProprietary(source);
    	
    	if (proprietary == null) {
    		
    		if (LOGGER.isDebugEnabled()) {
                LOGGER.debug("meta.proprietary not found for agentRef [{]] -> create", source);
            }
    		
    		proprietary = new Proprietary();
    		proprietary.setSource(source);
    		meta.add(proprietary);
    	}
    	
    	return proprietary;
    }
    
}
