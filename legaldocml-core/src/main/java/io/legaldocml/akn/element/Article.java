package io.legaldocml.akn.element;

import io.legaldocml.akn.group.ANhier;
import io.legaldocml.akn.visitor.AknVisitor;
import io.legaldocml.io.impl.Buffers;
import io.legaldocml.io.XmlWriter;

import java.io.IOException;

/**
 * This element is a hierarchical container called "article" either explicitly or due to the local tradition.
 * <text/>
 * <pre>
 *   <xsd:element name="article" type="hierarchy"/>
 * </pre>
 *
 * @author <a href="mailto:jacques.militello@gmail.com">Jacques Militello</a>
 */
public final class Article extends Hierarchy implements ANhier {

    /**
     * XML tag element name.
     */
    public static final String ELEMENT_ARTICLE = "article";

    /**
     * Memory address.
     */
    private static final long ADDRESS_ARTICLE = Buffers.address(ELEMENT_ARTICLE);

    /**
     * {@inheritDoc}
     */
    @Override
    public void write(XmlWriter writer) throws IOException {
        writer.writeStart(ADDRESS_ARTICLE, 7);
        super.write(writer);
        writer.writeEnd(ADDRESS_ARTICLE, 7);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String name() {
        return ELEMENT_ARTICLE;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void accept(AknVisitor visitor) {
        if (visitor.visitEnter(this)) {
            super.accept(visitor);
            visitor.visitLeave(this);
        }
    }

}