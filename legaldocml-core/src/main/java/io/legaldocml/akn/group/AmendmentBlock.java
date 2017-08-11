package io.legaldocml.akn.group;

import io.legaldocml.akn.AknObject;

/**
 * The group amendmentBlock lists the structures that should be found in an amendment.
 * <p/>
 * <pre>
 *   <xsd:group name="amendmentBlock">
 * 	   <xsd:choice>
 * 	     <xsd:element ref="amendmentHeading" />
 * 		 <xsd:element ref="amendmentContent" />
 * 		 <xsd:element ref="amendmentReference" />
 * 		 <xsd:element ref="amendmentJustification" />
 * 	   </xsd:choice>
 *   </xsd:group>
 * </pre>
 *
 * @author <a href="mailto:jacques.militello@gmail.com">Jacques Militello</a>
 */
public interface AmendmentBlock extends AknObject {

}