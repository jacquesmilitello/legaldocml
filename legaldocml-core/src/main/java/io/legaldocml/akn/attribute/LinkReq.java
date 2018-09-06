package io.legaldocml.akn.attribute;


import io.legaldocml.io.XmlWriter;

import java.io.IOException;

import static io.legaldocml.akn.util.XmlWriterHelper.writeLinkReq;

/**
 * The attribute href is used to specify an internal or external destination for a reference, a citation, an access to
 * the ontology or a hypertext link. In elements using this attribute definition the href attribute is
 * required.
 * <p>
 * ```xml
 * <xsd:attributeGroup name="link">
 * <xsd:attribute name="href" type="xsd:anyURI" use="required"/>
 * </xsd:attributeGroup>
 * ```
 *
 * @author <a href="mailto:jacques.militello@gmail.com">Jacques Militello</a>
 */
public interface LinkReq extends Link {

    /**
     * {@inheritDoc}
     */
    @Override
    default void write(XmlWriter writer) throws IOException {
        writeLinkReq(writer, this);
    }

}