package io.legaldocml.akn.group;

import io.legaldocml.akn.AknObject;

/**
 * The group amendmentBlock lists the structures that should be found in an amendment.
 *
 * <pre>
 *   &lt;xsd:group name="amendmentBlock"&gt;
 * 	   &lt;xsd:choice&gt;
 * 	     &lt;xsd:element ref="amendmentHeading" /&gt;
 * 		 &lt;xsd:element ref="amendmentContent" /&gt;
 * 		 &lt;xsd:element ref="amendmentReference" /&gt;
 * 		 &lt;xsd:element ref="amendmentJustification" /&gt;
 * 	   &lt;xsd:choice&gt;
 *   &lt;xsd:group&gt;
 * </pre>
 *
 * @author <a href="mailto:jacques.militello@gmail.com">Jacques Militello</a>
 */
public interface AmendmentBlock extends AknObject {

}