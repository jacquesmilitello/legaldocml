package io.legaldocml.business.impl;

import com.google.common.collect.ImmutableMap;
import io.legaldocml.akn.AkomaNtosoContext;
import io.legaldocml.akn.DocumentType;
import io.legaldocml.akn.element.Act;
import io.legaldocml.akn.element.Amendment;
import io.legaldocml.akn.element.AmendmentList;
import io.legaldocml.akn.element.Bill;
import io.legaldocml.akn.element.Debate;
import io.legaldocml.akn.element.DebateReport;
import io.legaldocml.akn.element.Doc;
import io.legaldocml.akn.element.DocumentCollection;
import io.legaldocml.akn.element.Judgment;
import io.legaldocml.akn.element.OfficialGazette;
import io.legaldocml.akn.element.Portion;
import io.legaldocml.akn.element.Statement;
import io.legaldocml.akn.type.AgentRef;
import io.legaldocml.business.builder.BusinessBuilder;
import io.legaldocml.business.builder.MetaBuilder;
import io.legaldocml.module.akn.v3.AkomaNtosoContextV3;

/**
 * @author <a href="mailto:jacques.militello@gmail.com">Jacques Militello</a>
 */
public final class DefaultBusinessBuilder<T extends DocumentType> extends BusinessBuilder<T> {

    static final ImmutableMap<String, Class<? extends DocumentType>> DOCUMENT_TYPE;

    static {
        DOCUMENT_TYPE = ImmutableMap.<String, Class<? extends DocumentType>>builder()
                .put(Act.ELEMENT_ACT, Act.class)
                .put(Amendment.ELEMENT, Amendment.class)
                .put(AmendmentList.ELEMENT, AmendmentList.class)
                .put(Bill.ELEMENT, Bill.class)
                .put(Judgment.ELEMENT, Judgment.class)
                .put(DebateReport.ELEMENT, DebateReport.class)
                .put(Doc.ELEMENT, Doc.class)
                .put(Debate.ELEMENT, Debate.class)
                .put(DocumentCollection.ELEMENT, DocumentCollection.class)
                .put(OfficialGazette.ELEMENT, OfficialGazette.class)
                .put(Portion.ELEMENT, Portion.class)
                .put(Statement.ELEMENT, Statement.class)
                .build();
    }

    @SuppressWarnings("unchecked")
    public DefaultBusinessBuilder(String name) {
        super((Class<T>) DOCUMENT_TYPE.get(name));
    }

    @Override
    protected AkomaNtosoContext newAkomaNtosoContext() {
        return new AkomaNtosoContextV3();
    }

    @Override
    protected T newDocumenyType() {
        try {
            return getDocumentTypeClass().newInstance();
        } catch (InstantiationException | IllegalAccessException e) {
            throw new RuntimeException();
        }
    }

    @Override
    protected MetaBuilder<T> newMetaBuilder(BusinessBuilder<T> businessBuilder) {
        return new MetaBuilder<T>(businessBuilder, AgentRef.valueOf("default")) {
        };
    }
}
