package io.legaldocml.akn;

import io.legaldocml.akn.element.Meta;

/**
 * The type documentType lists all the document types that are managed by Akoma Ntoso.
 * <pre>
 *   &lt;xsd:group name="documentType"&gt;
 *     &lt;xsd:choice&gt;
 *       &lt;xsd:element ref="amendmentList"/&gt;
 *       &lt;xsd:element ref="officialGazette"/&gt;
 *       &lt;xsd:element ref="documentCollection"/&gt;
 *       &lt;xsd:element ref="act"/&gt;
 *       &lt;xsd:element ref="bill"/&gt;
 *       &lt;xsd:element ref="debateReport"/&gt;
 *       &lt;xsd:element ref="debate"/&gt;
 *       &lt;xsd:element ref="statement"/&gt;
 *       &lt;xsd:element ref="amendment"/&gt;
 *       &lt;xsd:element ref="judgment"/&gt;
 *       &lt;xsd:element ref="portion"/&gt;
 *       &lt;xsd:element ref="doc"/&gt;
 *     &lt;xsd:choice&gt;
 *   &lt;xsd:group&gt;
 * </pre>
 *
 * @author <a href="mailto:jacques.militello@gmail.com">Jacques Militello</a>
 */
public interface DocumentType extends CollectionBodyElement, DocContainerTypeElement {

    Meta getMeta();

}