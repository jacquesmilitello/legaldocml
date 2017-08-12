package io.legaldocml.akn.element;


import io.legaldocml.io.impl.Buffers;
import io.legaldocml.io.XmlWriter;

import java.io.IOException;

/**
 * The element FRBRmasterExpression is the metadata property identifying the master expression, i.e., the expression
 * whose ids are used as permanent ids in the wId attributes. An expression without the FRBRmasterExpression element is
 * considered a master expression itself, i.e., the first version, or the most important version, of a document
 * expressed in the only language, or in the most important language. Any other situation (subsequent versions, or
 * language variants, or content variants) must have the FRBRmasterExpression element pointing to the URI of the master
 * expression. If the FRBRmasterEpression is specified, but without a href pointing to the masterExpression, it is
 * assumed that NO master expression exist in reality, but an UR-Expression exist, whose ids are used in this expression
 * as wIds.
 *
 * <pre>
 *   &lt;xsd:element name="FRBRmasterExpression" type="linkType"/&gt;
 * </pre>
 *
 * @author <a href="mailto:jacques.militello@gmail.com">Jacques Militello</a>
 */
public final class FRBRmasterExpression extends LinkType {

    /**
     * XML Tag element name.
     */
    public static final String ELEMENT = "FRBRmasterExpression";

    /**
     * Memory address.
     */
    private static final long ADDRESS = Buffers.address(ELEMENT);

    /**
     * {@inheritDoc}
     */
    @Override
    public void write(XmlWriter writer) throws IOException {
        writer.writeStart(ADDRESS, 20);
        super.write(writer);
        writer.writeEnd(ADDRESS, 20);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String name() {
        return ELEMENT;
    }
}