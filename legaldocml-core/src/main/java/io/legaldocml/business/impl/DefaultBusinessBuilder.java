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
import io.legaldocml.business.BusinessProvider;
import io.legaldocml.business.builder.BusinessBuilder;
import io.legaldocml.business.builder.HierarchyStrategy;
import io.legaldocml.business.builder.MetaBuilder;
import io.legaldocml.module.akn.v3.AkomaNtosoContextV3;

import static io.legaldocml.akn.AknElements.ACT;
import static io.legaldocml.akn.AknElements.AMENDMENT;
import static io.legaldocml.akn.AknElements.AMENDMENT_LIST;
import static io.legaldocml.akn.AknElements.BILL;
import static io.legaldocml.akn.AknElements.DEBATE;
import static io.legaldocml.akn.AknElements.DEBATE_REPORT;
import static io.legaldocml.akn.AknElements.DOC;
import static io.legaldocml.akn.AknElements.DOCUMENT_COLLECTION;
import static io.legaldocml.akn.AknElements.JUDGMENT;
import static io.legaldocml.akn.AknElements.OFFICIAL_GAZETTE;
import static io.legaldocml.akn.AknElements.PORTION;
import static io.legaldocml.akn.AknElements.STATEMENT;

/**
 * @author <a href="mailto:jacques.militello@gmail.com">Jacques Militello</a>
 */
public final class DefaultBusinessBuilder extends BusinessBuilder {

    private static final ImmutableMap<String, Class<? extends DocumentType>> DOCUMENT_TYPE;

    static {
        DOCUMENT_TYPE = ImmutableMap.<String, Class<? extends DocumentType>>builder()
                .put(ACT, Act.class)
                .put(AMENDMENT, Amendment.class)
                .put(AMENDMENT_LIST, AmendmentList.class)
                .put(BILL, Bill.class)
                .put(JUDGMENT, Judgment.class)
                .put(DEBATE_REPORT, DebateReport.class)
                .put(DOC, Doc.class)
                .put(DEBATE, Debate.class)
                .put(DOCUMENT_COLLECTION, DocumentCollection.class)
                .put(OFFICIAL_GAZETTE, OfficialGazette.class)
                .put(PORTION, Portion.class)
                .put(STATEMENT, Statement.class)
                .build();
    }

    @SuppressWarnings("unchecked")
    DefaultBusinessBuilder(BusinessProvider provider, String name, HierarchyStrategy strategy) {
        super(provider,newDocumenyType(name),strategy);

    }

    @Override
    protected AkomaNtosoContext newAkomaNtosoContext() {
        return new AkomaNtosoContextV3();
    }

    private static DocumentType newDocumenyType(String name) {
        try {
            return DOCUMENT_TYPE.get(name).newInstance();
        } catch (InstantiationException | IllegalAccessException e) {
            throw new RuntimeException();
        }
    }

    @Override
    protected MetaBuilder newMetaBuilder() {
        return new MetaBuilder(this, AgentRef.valueOf("default")) {
        };
    }
}
