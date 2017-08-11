package io.legaldocml.akn.group;

/**
 * The group ANsemanticInline lists additional elements that are inline, and are specific to the Akoma Ntoso vocabulary.
 * <p/>
 * <pre>
 *   <xsd:group name="ANsemanticInline">
 * 	   <xsd:choice>
 * 	     <xsd:element ref="date" />
 * 		 <xsd:element ref="time" />
 * 		 <xsd:element ref="person" />
 * 		 <xsd:element ref="organization" />
 * 		 <xsd:element ref="concept" />
 * 		 <xsd:element ref="object" />
 * 		 <xsd:element ref="event" />
 * 		 <xsd:element ref="location" />
 * 		 <xsd:element ref="process" />
 * 		 <xsd:element ref="role" />
 * 		 <xsd:element ref="term" />
 * 		 <xsd:element ref="quantity" />
 * 		 <xsd:element ref="def" />
 * 		 <xsd:element ref="entity" />
 * 	   </xsd:choice>
 *   </xsd:group>
 * </pre>
 *
 * @author <a href="mailto:jacques.militello@gmail.com">Jacques Militello</a>
 */
public interface ANsemanticInline extends InlineElements {

}