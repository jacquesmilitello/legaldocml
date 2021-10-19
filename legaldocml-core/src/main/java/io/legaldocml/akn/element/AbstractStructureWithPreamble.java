package io.legaldocml.akn.element;

import io.legaldocml.akn.HasPreamble;
import io.legaldocml.akn.visitor.AknVisitor;
import io.legaldocml.io.XmlReader;
import io.legaldocml.io.XmlWriter;

import java.io.IOException;

import static io.legaldocml.akn.AknElements.PREAMBLE;

abstract class AbstractStructureWithPreamble extends AbstractStructure implements HasPreamble {

    /**
     * The preamble element. (Optional)
     */
    private Preamble preamble;

    /**
     * {@inheritDoc}
     */
    @Override
    public final Preamble getPreamble() {
        return this.preamble;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public final void setPreamble(Preamble preamble) {
        this.preamble = preamble;
    }


    protected final void writePreamble(XmlWriter writer) throws IOException {
        if (this.preamble != null) {
            this.preamble.write(writer);
        }
    }

    protected final void readPreamble(XmlReader reader) {
        if (reader.getQName().equalsLocalName(PREAMBLE)) {
            this.preamble = new Preamble();
            this.preamble.setParent(this);
            this.preamble.read(reader);
            reader.nextStartOrEndElement();
        }
    }

    protected final void visitPreamble(AknVisitor visitor) {
        Preamble preamble = this.preamble;
        if (preamble != null && visitor.visitEnter(preamble)) {
            preamble.accept(visitor);
            visitor.visitLeave(preamble);
        }
    }

}