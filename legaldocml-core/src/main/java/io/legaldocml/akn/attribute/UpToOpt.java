package io.legaldocml.akn.attribute;

/**
 * The attribute upTo is used in range-based elements to specify the eId of the ending element of the range.
 *
 * <pre>
 *   &lt;xsd:attributeGroup name="upTo"&gt;
 * 	   &lt;xsd:attribute name="upTo" type="eIdRef"/&gt;
 *   &lt;xsd:attributeGroup&gt;
 * </pre>
 * @author <a href="mailto:jacques.militello@gmail.com">Jacques Militello</a>
 */
public interface UpToOpt extends UpTo {

}