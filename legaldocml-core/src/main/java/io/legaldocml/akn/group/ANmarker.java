package io.legaldocml.akn.group;

/**
 * The group ANmarker lists the elements that are markers and are specific to the Akoma Ntoso vocabulary.
 * <p/>
 * <pre>
 *   <xsd:group name="ANmarker">
 * 	   <xsd:choice>
 * 	     <xsd:element ref="noteRef" />
 * 		 <xsd:element ref="eol" />
 * 		 <xsd:element ref="eop" />
 * 	   </xsd:choice>
 *   </xsd:group>
 * </pre>
 *
 * @author <a href="mailto:jacques.militello@gmail.com">Jacques Militello</a>
 */
public interface ANmarker extends MarkerElements {

}
