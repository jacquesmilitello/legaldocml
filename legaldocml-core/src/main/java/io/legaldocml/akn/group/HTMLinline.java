package io.legaldocml.akn.group;

/**
 * The group HTMLinline lists the elements that are inline and were inherited from the HTML vocabulary.
 *
 * <pre>
 *   <xsd:group name="HTMLinline">
 * 	   <xsd:choice>
 * 	     <xsd:element ref="b"/>
 * 		 <xsd:element ref="i"/>
 * 		 <xsd:element ref="a"/>
 * 		 <xsd:element ref="u"/>
 * 		 <xsd:element ref="sub"/>
 * 		 <xsd:element ref="sup"/>
 * 		 <xsd:element ref="abbr"/>
 * 		 <xsd:element ref="span"/>
 * 	   <xsd:choice>
 *   <xsd:group>
 * </pre>
 *
 * @author <a href="mailto:jacques.militello@gmail.com">Jacques Militello</a>
 */
public interface HTMLinline extends InlineElements {

}