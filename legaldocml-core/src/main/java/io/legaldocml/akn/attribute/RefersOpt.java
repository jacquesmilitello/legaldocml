package io.legaldocml.akn.attribute;

/**
 * This attribute creates a connection between the element and an element of the Akoma Ntoso ontology to which it
 * refers. In elements using this attribute definition the refersTo attribute is optional.
 *
 * ```xml
 * <xsd:attributeGroup name="refers">
 *   <xsd:attribute name="refersTo">
 *     <xsd:simpleType>
 *       <xsd:list>
 *         <xsd:simpleType>
 *           <xsd:restriction base="referenceRef"/>
 *         </xsd:simpleType>
 *       </xsd:list>
 *     </xsd:simpleType>
 *   </xsd:attribute>
 * </xsd:attributeGroup>
 * ```
 *
 * @author <a href="mailto:jacques.militello@gmail.com">Jacques Militello</a>
 */
public interface RefersOpt extends Refers {

}