package io.legaldocml.business.builder;

import io.legaldocml.akn.element.Caption;
import io.legaldocml.akn.element.Table;
import io.legaldocml.akn.element.Tr;

/**
 * @author <a href="mailto:jacques.militello@gmail.com">Jacques Militello</a>
 */
public final class TableBuilder extends BusinessPartBuilder{

    private final Table table;

    public TableBuilder(BusinessBuilder businessBuilder, Table table) {
        super(businessBuilder);
        this.table = table;
    }

    public InlineTypeBuilder<Caption> caption() {
        Caption caption = new Caption();
        table.setCaption(caption);
        return new InlineTypeBuilder<>(getBusinessBuilder(), caption);
    }

    public TableRowBuilder row() {
        Tr tr = new Tr();
        this.table.add(tr);
        return new TableRowBuilder(getBusinessBuilder(), tr);
    }

}
