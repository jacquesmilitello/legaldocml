package io.legaldocml.akn.element;


import io.legaldocml.akn.DocumentType;

/**
 * The group collectionDocs specifies all the document types that are collections of other documents, that may or may
 * not be addressed individually. Many documents are of this type, such as individual issues of the official gazette,
 * amendment lists, and many others.
 *
 * <pre>
 *   &lt;xsd:group name="collectionDocs"&gt;
 * 	   &lt;xsd:choice&gt;
 * 	     &lt;xsd:element ref="amendmentList"/&gt;
 * 		 &lt;xsd:element ref="officialGazette"/&gt;
 * 		 &lt;xsd:element ref="documentCollection"/&gt;
 *     &lt;xsd:choice&gt;
 *   &lt;xsd:group&gt;
 * </pre>
 *
 * @author <a href="mailto:jacques.militello@gmail.com">Jacques Militello</a>
 */
public interface CollectionDocs extends DocumentType {

}