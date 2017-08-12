package io.legaldocml.akn.group;

/**
 * The group ANblock lists the elements that are blocks and are specific to the Akoma Ntoso vocabulary.
 * <pre>
 *   &lt;xsd:group name="ANblock"&gt;
 * 	   &lt;xsd:choice&gt;
 * 	     &lt;xsd:element ref="blockList"/&gt;
 * 	     &lt;xsd:element ref="blockContainer"/&gt;
 * 	     &lt;xsd:element ref="tblock"/&gt;
 * 		 &lt;xsd:element ref="toc"/&gt;
 * 	   &lt;xsd:choice&gt;
 *   &lt;xsd:group&gt;
 * </pre>
 *
 * @author <a href="mailto:jacques.militello@gmail.com">Jacques Militello</a>
 */
public interface ANblock extends BlockElements {

}