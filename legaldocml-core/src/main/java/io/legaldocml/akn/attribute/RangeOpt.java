package io.legaldocml.akn.attribute;


/**
 * These attributes are used in range-based elements to specify the eId of the beginning and ending element of the
 * range.
 *
 * ```xml
 * <xsd:attributeGroup name="range">
 *   <xsd:attribute name="from" type="eIdRef" use="required"/>
 *   <xsd:attributeGroup ref="upToOpt"/>
 * </xsd:attributeGroup>
 * ```
 *
 * @author <a href="mailto:jacques.militello@gmail.com">Jacques Militello</a>
 */
public interface RangeOpt extends Range, UpToOpt {

}