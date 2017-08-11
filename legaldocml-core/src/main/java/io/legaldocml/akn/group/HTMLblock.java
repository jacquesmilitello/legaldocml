package io.legaldocml.akn.group;

/**
 * The group HTMLblock lists the elements that are blocks and were inherited from the HTML vocabulary.
 * 
 * <pre>
 *   <xsd:group name="HTMLblock">
 * 	   <xsd:choice>
 * 	     <xsd:element ref="ul"/>
 * 		 <xsd:element ref="ol"/>
 * 		 <xsd:element ref="table"/>
 * 		 <xsd:element ref="p"/>
 * 	   </xsd:choice>
 *   </xsd:group>
 * </pre>
 *
 * @author <a href="mailto:jacques.militello@gmail.com">Jacques Militello</a>
 */
public interface HTMLblock extends BlockElements{

}