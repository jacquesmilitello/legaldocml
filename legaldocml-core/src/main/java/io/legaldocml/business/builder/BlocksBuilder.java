package io.legaldocml.business.builder;

import io.legaldocml.akn.attribute.Id;
import io.legaldocml.akn.container.BlockElementsContainer;
import io.legaldocml.akn.element.Foreign;
import io.legaldocml.akn.element.P;
import io.legaldocml.akn.element.Table;
import io.legaldocml.business.util.EidFactory;

/**
 * @author <a href="mailto:jacques.militello@gmail.com">Jacques Militello</a>
 */
public class BlocksBuilder<T extends BlockElementsContainer> extends BusinessPartBuilder implements HasPBuilder {

    private final Id parent;
    private final T container;

    public BlocksBuilder(BusinessBuilder businessBuilder, Id parent, T container) {
        super(businessBuilder);
        this.parent = parent;
        this.container = container;
    }

    public BlocksBuilder<T> eid(String number) {
        EidFactory.makeAndFill(this.parent, this.container, number);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public PBuilder p() {
        P p = new P();
        this.container.add(p);
        return new PBuilder(p, getBusinessBuilder());
    }

    public TableBuilder table() {
        Table table = new Table();
        this.container.add(table);
        return new TableBuilder(getBusinessBuilder(), table);
    }

    public <T extends BusinessPartBuilder> T foreign(String businessPartBuilderName) {
        Foreign foreign = new Foreign();
        this.container.add(foreign);

       // getBusinessBuilder().getProvider();


        //BusinessProvider.businessProvider("toto").
        return null;

    }

}
