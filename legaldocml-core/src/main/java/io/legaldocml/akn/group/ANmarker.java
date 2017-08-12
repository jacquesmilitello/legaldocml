package io.legaldocml.akn.group;

/**
 * The group ANmarker lists the elements that are markers and are specific to the Akoma Ntoso vocabulary.
 *
 * <pre>
 *   &lt;xsd:group name="ANmarker"&gt;
 * 	   &lt;xsd:choice&gt;
 * 	     &lt;xsd:element ref="noteRef" /&gt;
 * 		 &lt;xsd:element ref="eol" /&gt;
 * 		 &lt;xsd:element ref="eop" /&gt;
 * 	   &lt;xsd:choice&gt;
 *   &lt;xsd:group&gt;
 * </pre>
 *
 * @author <a href="mailto:jacques.militello@gmail.com">Jacques Militello</a>
 */
public interface ANmarker extends MarkerElements {

}
