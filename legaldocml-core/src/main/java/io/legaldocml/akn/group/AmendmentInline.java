package io.legaldocml.akn.group;

/**
 * The group amendmentInline lists the inline elements that should be found in the preface of an amendment.
 *
 * <pre>
 *   <xsd:group name="amendmentInline">
 * 	   <xsd:choice>
 * 	     <xsd:element ref="affectedDocument" />
 * 		 <xsd:element ref="relatedDocument" />
 * 		 <xsd:element ref="change" />
 *     <xsd:choice>
 *   <xsd:group>
 * </pre>
 *
 * @author <a href="mailto:jacques.militello@gmail.com">Jacques Militello</a>
 */
public interface AmendmentInline extends InlineElements {

}