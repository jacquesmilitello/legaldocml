package io.legaldocml.business.builder;

import io.legaldocml.akn.attribute.Id;
import io.legaldocml.akn.element.Blocksreq;
import io.legaldocml.akn.element.P;
import io.legaldocml.business.util.EidFactory;

public class BlocksreqBuilder<T extends Blocksreq> {

    private final Id parent;
    private final Blocksreq blocksreq;

    public BlocksreqBuilder(Id parent, T blocksreq) {
        this.parent = parent;
        this.blocksreq = blocksreq;
    }

    public BlocksreqBuilder<T> eid(String number) {
        EidFactory.makeAndFill(this.parent, this.blocksreq, number);
        return this;
    }

    public PBuilder p() {
        P p = new P();
        this.blocksreq.add(p);
        return new PBuilder(p, null);
    }

}
