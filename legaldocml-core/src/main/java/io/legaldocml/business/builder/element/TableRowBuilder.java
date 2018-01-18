package io.legaldocml.business.builder.element;

import io.legaldocml.akn.element.Td;
import io.legaldocml.akn.element.Th;
import io.legaldocml.akn.element.Tr;
import io.legaldocml.business.builder.AbstractBusinessPartBuilder;
import io.legaldocml.business.builder.BusinessBuilder;

/**
 * @author <a href="mailto:jacques.militello@gmail.com">Jacques Militello</a>
 */
public final class TableRowBuilder extends AbstractBusinessPartBuilder<Tr> {

    private final Tr tr;

    public TableRowBuilder(BusinessBuilder businessBuilder, Tr tr) {
        super(businessBuilder, tr);
        this.tr = tr;
    }

    public BlocksBuilder<Td> column() {
        Td td = new Td();
        this.tr.add(td);
        return new BlocksBuilder<>(businessBuilder(), this.tr, td);
    }

    @SuppressWarnings("unchecked")
    public BlocksBuilder<Td> header() {
        Th th = new Th();
        this.tr.add(th);
        return new BlocksBuilder(businessBuilder(), this.tr, th);
    }
}
