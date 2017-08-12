package io.legaldocml.akn.attribute;

/**
 * This attribute creates a connection between the element and an element of the Akoma Ntoso ontology to which it
 * refers. In elements using this attribute definition the refersTo attribute is optional.
 *
 * <pre>
 *   &lt;xsd:attributeGroup name="refers"&gt;
 * 	   &lt;xsd:attribute name="refersTo"&gt;
 *       &lt;xsd:simpleType>
 *         &lt;xsd:list>
 *           &lt;xsd:simpleType>
 *             &lt;xsd:restriction base="referenceRef"/&gt;
 *           &lt;xsd:simpleType>
 *         &lt;xsd:list>
 *       &lt;xsd:simpleType>
 *     &lt;xsd:attribute>
 *   &lt;xsd:attributeGroup&gt;
 * </pre>
 *
 * @author <a href="mailto:jacques.militello@gmail.com">Jacques Militello</a>
 */
public interface RefersOpt extends Refers {

}