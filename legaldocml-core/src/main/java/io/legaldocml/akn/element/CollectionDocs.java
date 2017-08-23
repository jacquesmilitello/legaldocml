package io.legaldocml.akn.element;


import io.legaldocml.akn.DocumentType;

/**
 * The group collectionDocs specifies all the document types that are collections of other documents, that may or may
 * not be addressed individually. Many documents are of this type, such as individual issues of the official gazette,
 * amendment lists, and many others.
 *
 * <pre>
 *   <xsd:group name="collectionDocs">
 * 	   <xsd:choice>
 * 	     <xsd:element ref="amendmentList"/>
 * 		 <xsd:element ref="officialGazette"/>
 * 		 <xsd:element ref="documentCollection"/>
 *     <xsd:choice>
 *   <xsd:group>
 * </pre>
 *
 * @author <a href="mailto:jacques.militello@gmail.com">Jacques Militello</a>
 */
public interface CollectionDocs extends DocumentType {

}