package io.legaldocml.business.builder;

import io.legaldocml.akn.element.Td;
import io.legaldocml.akn.element.Th;
import io.legaldocml.akn.element.Tr;

/**
 * @author <a href="mailto:jacques.militello@gmail.com">Jacques Militello</a>
 */
public final class TableRowBuilder extends BusinessPartBuilder{

    private final Tr tr;

    public TableRowBuilder(BusinessBuilder businessBuilder, Tr tr) {
        super(businessBuilder);
        this.tr = tr;
    }

    @SuppressWarnings("unchecked")
    public BlocksBuilder<Td> column() {
        Td td = new Td();
        this.tr.add(td);
        return new BlocksBuilder(getBusinessBuilder(), this.tr, td);
    }

    @SuppressWarnings("unchecked")
    public BlocksBuilder<Td> header() {
        Th th = new Th();
        this.tr.add(th);
        return new BlocksBuilder(getBusinessBuilder(), this.tr, th);
    }
}
