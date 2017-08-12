package io.legaldocml.akn.group;

import io.legaldocml.akn.element.RefItem;

/**
 * The group docrefs is a list of types of legal references to documents.
 *
 * <pre>
 *   &lt;xsd:group name="docRefs"&gt;
 * 	   &lt;xsd:choice&gt;
 * 	     &lt;xsd:element ref="original"/&gt;
 * 		 &lt;xsd:element ref="passiveRef"/&gt;
 * 		 &lt;xsd:element ref="activeRef"/&gt;
 * 		 &lt;xsd:element ref="jurisprudence"/&gt;
 * 		 &lt;xsd:element ref="hasAttachment"/&gt;
 * 		 &lt;xsd:element ref="attachmentOf"/&gt;
 * 	   &lt;xsd:choice&gt;
 *   &lt;xsd:group&gt;
 * </pre>
 *
 * @author <a href="mailto:jacques.militello@gmail.com">Jacques Militello</a>
 */
public interface DocRef extends RefItem {

}