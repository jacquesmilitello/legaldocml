package io.legaldocml.akn.attribute;


/**
 * This attribute creates a connection between the element and an element of the Akoma Ntoso ontology to which it
 * refers. In elements using this attribute definition the refersTo attribute is required
 * <p>
 * <pre>
 *   <xsd:attributeGroup name="refers">
 * 	   <xsd:attribute name="refersTo" use="required">
 *       <xsd:simpleType>
 *         <xsd:list>
 *           <xsd:simpleType>
 *             <xsd:restriction base="referenceRef"/>
 *           </xsd:simpleType>
 *         </xsd:list>
 *       </xsd:simpleType>
 *     </xsd:attribute>
 *   </xsd:attributeGroup>
 * </pre>
 *
 * @author <a href="mailto:jacques.militello@gmail.com">Jacques Militello</a>
 */
public interface RefersReq extends Refers {

}