package io.legaldocml.akn.group;

/**
 * The group ANblock lists the elements that are blocks and are specific to the Akoma Ntoso vocabulary.
 * <pre>
 *   <xsd:group name="ANblock">
 * 	   <xsd:choice>
 * 	     <xsd:element ref="blockList"/>
 * 	     <xsd:element ref="blockContainer"/>
 * 	     <xsd:element ref="tblock"/>
 * 		 <xsd:element ref="toc"/>
 * 	   </xsd:choice>
 *   </xsd:group>
 * </pre>
 *
 * @author <a href="mailto:jacques.militello@gmail.com">Jacques Militello</a>
 */
public interface ANblock extends BlockElements {

}