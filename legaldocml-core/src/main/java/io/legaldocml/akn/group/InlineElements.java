package io.legaldocml.akn.group;

/**
 * The group inlineElements lists all the elements that are inline.
 *
 * <pre>
 *   <xsd:group name="inlineElements">
 * 	   <xsd:choice>
 * 	     <xsd:group ref="ANinline"/>
 * 		 <xsd:group ref="HTMLinline"/>
 * 		 <xsd:group ref="ANtitleInline"/>
 * 		 <xsd:group ref="ANsemanticInline"/>
 * 		 <xsd:group ref="ANheaderInline"/>
 * 		 <xsd:group ref="amendmentInline"/>
 * 		 <xsd:element ref="inline"/>
 * 	   <xsd:choice>
 *   <xsd:group>
 * </pre>
 *
 * @author <a href="mailto:jacques.militello@gmail.com">Jacques Militello</a>
 */
public interface InlineElements extends InlineCM {

}