package io.legaldocml.akn.group;

/**
 * The group amendmentInline lists the inline elements that should be found in the preface of an amendment.
 *
 * <pre>
 *   &lt;xsd:group name="amendmentInline"&gt;
 * 	   &lt;xsd:choice&gt;
 * 	     &lt;xsd:element ref="affectedDocument" /&gt;
 * 		 &lt;xsd:element ref="relatedDocument" /&gt;
 * 		 &lt;xsd:element ref="change" /&gt;
 *     &lt;xsd:choice&gt;
 *   &lt;xsd:group&gt;
 * </pre>
 *
 * @author <a href="mailto:jacques.militello@gmail.com">Jacques Militello</a>
 */
public interface AmendmentInline extends InlineElements {

}