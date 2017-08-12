package io.legaldocml.akn.group;

/**
 * The group HTMLblock lists the elements that are blocks and were inherited from the HTML vocabulary.
 * 
 * <pre>
 *   &lt;xsd:group name="HTMLblock"&gt;
 * 	   &lt;xsd:choice&gt;
 * 	     &lt;xsd:element ref="ul"/&gt;
 * 		 &lt;xsd:element ref="ol"/&gt;
 * 		 &lt;xsd:element ref="table"/&gt;
 * 		 &lt;xsd:element ref="p"/&gt;
 * 	   &lt;xsd:choice&gt;
 *   &lt;xsd:group&gt;
 * </pre>
 *
 * @author <a href="mailto:jacques.militello@gmail.com">Jacques Militello</a>
 */
public interface HTMLblock extends BlockElements{

}