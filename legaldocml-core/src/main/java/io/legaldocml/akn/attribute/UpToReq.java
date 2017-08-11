package io.legaldocml.akn.attribute;

import io.legaldocml.akn.AknObject;
import io.legaldocml.akn.type.EidRef;

/**
 * The attribute upTo is used in range-based elements to specify the eId of the ending element of the range.
 * <p/>
 * <pre>
 * 	 <xsd:attributeGroup name="upTo">
 * 	   <xsd:attribute name="upTo" type="eIdRef" use="required"/>
 * 	 </xsd:attributeGroup>
 * </pre>
 */
public interface UpToReq extends UpTo {


}