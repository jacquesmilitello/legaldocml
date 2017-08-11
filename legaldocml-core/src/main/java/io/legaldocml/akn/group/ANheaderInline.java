package io.legaldocml.akn.group;

/**
 * The group ANheaderInline lists the elements that are inline, are specific to the Akoma Ntoso vocabulary, and should
 * only be used within the header element.
 * <pre>
 *   <xsd:group name="ANheaderInline">
 * 	   <xsd:choice>
 * 	     <xsd:element ref="courtType"/>
 * 		 <xsd:element ref="neutralCitation"/>
 * 		 <xsd:element ref="party"/>
 * 		 <xsd:element ref="judge"/>
 * 		 <xsd:element ref="lawyer"/>
 * 		 <xsd:element ref="signature"/>
 * 		 <xsd:element ref="argument"/>
 * 	   </xsd:choice>
 *   </xsd:group>
 * </pre>
 *
 * @author <a href="mailto:jacques.militello@gmail.com">Jacques Militello</a>
 */
public interface ANheaderInline extends InlineElements {

}