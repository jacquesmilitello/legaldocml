package io.legaldocml.akn.attribute;


/**
 * These attributes are used in range-based elements to specify the eId of the beginning and ending element of the
 * range.
 *
 * <pre>
 *   &lt;xsd:attributeGroup name="range"&gt;
 *     &lt;xsd:attribute name="from" type="eIdRef" use="required"/&gt;
 *     &lt;xsd:attributeGroup ref="upToOpt"/&gt;
 *   &lt;xsd:attributeGroup&gt;
 * </pre>
 *
 * @author <a href="mailto:jacques.militello@gmail.com">Jacques Militello</a>
 */
public interface RangeOpt extends Range, UpToOpt {

}