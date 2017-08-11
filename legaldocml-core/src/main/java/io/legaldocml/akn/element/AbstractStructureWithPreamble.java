package io.legaldocml.akn.element;

import io.legaldocml.akn.visitor.AknVisitor;
import io.legaldocml.io.XmlReader;
import io.legaldocml.io.XmlWriter;

import java.io.IOException;

abstract class AbstractStructureWithPreamble extends AbstractStructure {

    /**
     * The preamble element. (Optional)
     */
    private Preamble preamble;

    public final Preamble getPreamble() {
        return this.preamble;
    }

    public final void setPreamble(Preamble preamble) {
        this.preamble = preamble;
    }

    protected final void writePreamble(XmlWriter writer) throws IOException {
        if (this.preamble != null) {
            this.preamble.write(writer);
        }
    }

    protected final void readPreamble(XmlReader reader) {
        if (reader.getQName().equalsLocalName(Preamble.ELEMENT)) {
            this.preamble = new Preamble();
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