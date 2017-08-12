package io.legaldocml.akn.group;

/**
 * The group ANheaderInline lists the elements that are inline, are specific to the Akoma Ntoso vocabulary, and should
 * only be used within the header element.
 * <pre>
 *   &lt;xsd:group name="ANheaderInline"&gt;
 * 	   &lt;xsd:choice&gt;
 * 	     &lt;xsd:element ref="courtType"/&gt;
 * 		 &lt;xsd:element ref="neutralCitation"/&gt;
 * 		 &lt;xsd:element ref="party"/&gt;
 * 		 &lt;xsd:element ref="judge"/&gt;
 * 		 &lt;xsd:element ref="lawyer"/&gt;
 * 		 &lt;xsd:element ref="signature"/&gt;
 * 		 &lt;xsd:element ref="argument"/&gt;
 * 	   &lt;xsd:choice&gt;
 *   &lt;xsd:group&gt;
 * </pre>
 *
 * @author <a href="mailto:jacques.militello@gmail.com">Jacques Militello</a>
 */
public interface ANheaderInline extends InlineElements {

}