package io.legaldocml.business.builder.element;

import io.legaldocml.akn.DocumentType;
import io.legaldocml.akn.element.Caption;
import io.legaldocml.akn.element.Table;
import io.legaldocml.akn.element.Tr;
import io.legaldocml.business.builder.AbstractBusinessPartBuilder;
import io.legaldocml.business.builder.BusinessBuilder;

/**
 * @author <a href="mailto:jacques.militello@gmail.com">Jacques Militello</a>
 */
public final class TableBuilder extends AbstractBusinessPartBuilder<Table> {

    private final Table table;

    public TableBuilder(BusinessBuilder<? extends DocumentType> businessBuilder, Table table) {
        super(businessBuilder, table);
        this.table = table;
    }

    public InlineTypeBuilder<Caption> caption() {
        Caption caption = new Caption();
        table.setCaption(caption);
        businessBuilder().getContext().push(parent(), caption);
        return new InlineTypeBuilder<>(businessBuilder(), caption);
    }

    public TableRowBuilder row() {
        Tr tr = new Tr();
        this.table.add(tr);
        businessBuilder().getContext().push(parent(), tr);
        return new TableRowBuilder(businessBuilder(), tr);
    }

}
