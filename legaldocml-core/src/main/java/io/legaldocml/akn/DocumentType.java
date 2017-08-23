package io.legaldocml.akn;

import io.legaldocml.akn.element.Meta;

/**
 * The type documentType lists all the document types that are managed by Akoma Ntoso.
 * <pre>
 *   <xsd:group name="documentType">
 *     <xsd:choice>
 *       <xsd:element ref="amendmentList"/>
 *       <xsd:element ref="officialGazette"/>
 *       <xsd:element ref="documentCollection"/>
 *       <xsd:element ref="act"/>
 *       <xsd:element ref="bill"/>
 *       <xsd:element ref="debateReport"/>
 *       <xsd:element ref="debate"/>
 *       <xsd:element ref="statement"/>
 *       <xsd:element ref="amendment"/>
 *       <xsd:element ref="judgment"/>
 *       <xsd:element ref="portion"/>
 *       <xsd:element ref="doc"/>
 *     <xsd:choice>
 *   <xsd:group>
 * </pre>
 *
 * @author <a href="mailto:jacques.militello@gmail.com">Jacques Militello</a>
 */
public interface DocumentType extends CollectionBodyElement, DocContainerTypeElement {

    Meta getMeta();

}