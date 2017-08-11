package io.legaldocml.akn.group;

import io.legaldocml.akn.element.RefItem;

/**
 * The group docrefs is a list of types of legal references to documents.
 * <p/>
 * <pre>
 *   <xsd:group name="docRefs">
 * 	   <xsd:choice>
 * 	     <xsd:element ref="original"/>
 * 		 <xsd:element ref="passiveRef"/>
 * 		 <xsd:element ref="activeRef"/>
 * 		 <xsd:element ref="jurisprudence"/>
 * 		 <xsd:element ref="hasAttachment"/>
 * 		 <xsd:element ref="attachmentOf"/>
 * 	   </xsd:choice>
 *   </xsd:group>
 * </pre>
 *
 * @author <a href="mailto:jacques.militello@gmail.com">Jacques Militello</a>
 */
public interface DocRef extends RefItem {

}