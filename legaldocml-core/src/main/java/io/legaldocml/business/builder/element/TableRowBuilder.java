package io.legaldocml.business.builder.element;

import io.legaldocml.akn.DocumentType;
import io.legaldocml.akn.element.Td;
import io.legaldocml.akn.element.Th;
import io.legaldocml.akn.element.Tr;
import io.legaldocml.business.builder.AbstractBusinessPartBuilder;
import io.legaldocml.business.builder.BusinessBuilder;

/**
 * @author <a href="mailto:jacques.militello@gmail.com">Jacques Militello</a>
 */
public final class TableRowBuilder extends AbstractBusinessPartBuilder<Tr> {

    public TableRowBuilder(BusinessBuilder<? extends DocumentType> businessBuilder, Tr tr) {
        super(businessBuilder, tr);
    }

    public BlocksBuilder<Td> column() {
        Td td = new Td();
        this.parent().add(td);
        businessBuilder().getContext().push(parent(),td);
        return new BlocksBuilder<>(businessBuilder(), td);
    }

    public BlocksBuilder<Th> header() {
        Th th = new Th();
        this.parent().add(th);
        businessBuilder().getContext().push(parent(),th);
        return new BlocksBuilder<>(businessBuilder(), th);
    }
}
