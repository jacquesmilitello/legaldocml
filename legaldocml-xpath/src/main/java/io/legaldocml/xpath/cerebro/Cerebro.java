package io.legaldocml.xpath.cerebro;

import com.google.common.collect.ImmutableMap;
import io.legaldocml.akn.AknObject;
import io.legaldocml.akn.AkomaNtoso;
import io.legaldocml.akn.AkomaNtosoType;
import io.legaldocml.akn.element.Act;
import io.legaldocml.akn.element.ActiveModifications;
import io.legaldocml.akn.element.Amendment;
import io.legaldocml.akn.element.AmendmentList;
import io.legaldocml.akn.element.Analysis;
import io.legaldocml.akn.element.Attachments;
import io.legaldocml.akn.element.Bill;
import io.legaldocml.akn.element.Body;
import io.legaldocml.akn.element.Classification;
import io.legaldocml.akn.element.Components;
import io.legaldocml.akn.element.Conclusions;
import io.legaldocml.akn.element.CoverPage;
import io.legaldocml.akn.element.Debate;
import io.legaldocml.akn.element.DebateBody;
import io.legaldocml.akn.element.HierarchicalStructure;
import io.legaldocml.akn.element.Identification;
import io.legaldocml.akn.element.Judicial;
import io.legaldocml.akn.element.Lifecycle;
import io.legaldocml.akn.element.Mappings;
import io.legaldocml.akn.element.Meta;
import io.legaldocml.akn.element.Notes;
import io.legaldocml.akn.element.OtherAnalysis;
import io.legaldocml.akn.element.OtherReferences;
import io.legaldocml.akn.element.Parliamentary;
import io.legaldocml.akn.element.ParliamentaryAnalysisElement;
import io.legaldocml.akn.element.PassiveModifications;
import io.legaldocml.akn.element.Preamble;
import io.legaldocml.akn.element.Preface;
import io.legaldocml.akn.element.Presentation;
import io.legaldocml.akn.element.Proprietary;
import io.legaldocml.akn.element.Publication;
import io.legaldocml.akn.element.QuorumVerification;
import io.legaldocml.akn.element.Recount;
import io.legaldocml.akn.element.References;
import io.legaldocml.akn.element.Restrictions;
import io.legaldocml.akn.element.Step;
import io.legaldocml.akn.element.TemporalData;
import io.legaldocml.akn.element.Voting;
import io.legaldocml.akn.element.Workflow;

@SuppressWarnings("unchecked")
public class Cerebro {

    private static ImmutableMap<Class<? extends AknObject>, CerebroDefinition> DEFINITIONS;


    static {

        ImmutableMap.Builder builder = ImmutableMap.<Class<? extends AknObject>, CerebroDefinition>builder();

        builder.put(AkomaNtoso.class, new CerebroDefinition(
                new CerebroDirectLink<AkomaNtoso<Bill>, Bill>(Bill.class, AkomaNtosoType::getDocumentType),
                new CerebroDirectLink<AkomaNtoso<AmendmentList>, AmendmentList>(AmendmentList.class, AkomaNtosoType::getDocumentType),
                new CerebroDirectLink<AkomaNtoso<Amendment>, Amendment>(Amendment.class, AkomaNtosoType::getDocumentType),
                new CerebroDirectLink<AkomaNtoso<Debate>, Debate>(Debate.class, AkomaNtosoType::getDocumentType),
                new CerebroDirectLink<AkomaNtoso, Components>(Components.class, AkomaNtosoType::getComponents)
                ));

        builder.put(Act.class, hierarchicalStructure(Act.class));
        builder.put(Bill.class, hierarchicalStructure(Bill.class));
        builder.put(Debate.class, new CerebroDefinition(
                new CerebroDirectLink<>(Meta.class, Debate::getMeta),
                new CerebroDirectLink<>(CoverPage.class, Debate::getCoverPage),
                new CerebroDirectLink<>(Preface.class, Debate::getPreface),
                new CerebroDirectLink<>(DebateBody.class, Debate::getDebateBody),
                new CerebroDirectLink<>(Conclusions.class, Debate::getConclusions),
                new CerebroDirectLink<>(Attachments.class, Debate::getAttachments),
                new CerebroDirectLink<>(Components.class, Debate::getComponents)
        ));

        builder.put(Meta.class, new CerebroDefinition(
                new CerebroDirectLink<>(Identification.class, Meta::getIdentification),
                new CerebroDirectLink<>(Publication.class, Meta::getPublication),
                new CerebroListLink<>(Classification.class, Meta::getClassifications),
                new CerebroListLink<>(Lifecycle.class, Meta::getLifecycles),
                new CerebroListLink<>(Workflow.class, Meta::getWorkflows),
                new CerebroListLink<>(Analysis.class, Meta::getAnalysis),
                new CerebroListLink<>(TemporalData.class, Meta::getTemporalData),
                new CerebroListLink<Meta, References>(References.class, Meta::getReferences),
                new CerebroListLink<>(Notes.class, Meta::getNotes),
                new CerebroListLink<>(Proprietary.class, Meta::getProprietaries),
                new CerebroListLink<>(Presentation.class, Meta::getPresentations)
        ));

        builder.put(Workflow.class, new CerebroDefinition(
                new CerebroListLink<>(Step.class, Workflow::getSteps)
        ));

        builder.put(Analysis.class, new CerebroDefinition(
                new CerebroDirectLink<>(ActiveModifications.class, Analysis::getActiveModifications),
                new CerebroDirectLink<>(PassiveModifications.class, Analysis::getPassiveModifications),
                new CerebroDirectLink<>(Restrictions.class, Analysis::getRestrictions),
                new CerebroDirectLink<>(Judicial.class, Analysis::getJudicial),
                new CerebroDirectLink<>(Parliamentary.class, Analysis::getParliamentary),
                new CerebroDirectLink<>(Mappings.class, Analysis::getMappings),
                new CerebroListLink<>(OtherReferences.class, Analysis::getOtherReferences),
                new CerebroListLink<>(OtherAnalysis.class, Analysis::getOtherAnalysis)
        ));


        builder.put(Parliamentary.class, new CerebroDefinition(
                new CerebroListLink<>(ParliamentaryAnalysisElement.class, Parliamentary::getElements),
                new CerebroListFilterLink<>(ParliamentaryAnalysisElement.class, Voting.class, Parliamentary::getElements),
                new CerebroListFilterLink<>(ParliamentaryAnalysisElement.class, QuorumVerification.class, Parliamentary::getElements),
                new CerebroListFilterLink<>(ParliamentaryAnalysisElement.class, Recount.class, Parliamentary::getElements)
        ));

        DEFINITIONS = builder.build();



    }


    public static CerebroLink getLink(Class<? extends AknObject> parent, Class<? extends AknObject> child) {

        CerebroDefinition definition = DEFINITIONS.get(parent);

        if (definition == null) {
            throw new CerebroException("No defintion found for [" + parent + "] (child=" + child + ")");
        }

        return definition.getLink(child);


    }


    private static <T extends HierarchicalStructure> CerebroDefinition<T> hierarchicalStructure(Class<T> clazz) {
        return new CerebroDefinition<>(
                new CerebroDirectLink<T, Meta>(Meta.class, HierarchicalStructure::getMeta),
                new CerebroDirectLink<T, CoverPage>(CoverPage.class, HierarchicalStructure::getCoverPage),
                new CerebroDirectLink<T, Preface>(Preface.class, HierarchicalStructure::getPreface),
                new CerebroDirectLink<T, Preamble>(Preamble.class, HierarchicalStructure::getPreamble),
                new CerebroDirectLink<T, Body>(Body.class, HierarchicalStructure::getBody),
                new CerebroDirectLink<T, Conclusions>(Conclusions.class, HierarchicalStructure::getConclusions),
                new CerebroDirectLink<T, Attachments>(Attachments.class, HierarchicalStructure::getAttachments),
                new CerebroDirectLink<T, Components>(Components.class, HierarchicalStructure::getComponents)
        );
    }
}
