package io.legaldocml.akn.attribute;

import io.legaldocml.akn.AknObject;
import io.legaldocml.akn.type.EidRef;

/**
 * The attribute upTo is used in range-based elements to specify the eId of the ending element of the range.
 *
 * <pre>
 * 	 &lt;xsd:attributeGroup name="upTo"&gt;
 * 	   &lt;xsd:attribute name="upTo" type="eIdRef" use="required"/&gt;
 * 	 &lt;xsd:attributeGroup&gt;
 * </pre>
 */
public interface UpToReq extends UpTo {


}