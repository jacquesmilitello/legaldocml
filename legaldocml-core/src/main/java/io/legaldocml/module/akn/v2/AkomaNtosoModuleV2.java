package io.legaldocml.module.akn.v2;


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
import io.legaldocml.akn.element.OfficialGazette;
import io.legaldocml.io.Attribute;
import io.legaldocml.io.CharArray;
import io.legaldocml.io.CharArrays;
import io.legaldocml.io.XmlWriter;
import io.legaldocml.io.impl.Buffers;
import io.legaldocml.module.AknModule;

import java.io.IOException;
import java.util.function.Supplier;

/**
 * @author <a href="mailto:jacques.militello@gmail.com">Jacques Militello</a>
 */
public final class AkomaNtosoModuleV2 implements AknModule {

    public static final String NS_VALUE = "http://www.akomantoso.org/2.0";
    public static final String NS_PREFIX = "xmlns";

    private static final long NS_VALUE_ADDRESS = Buffers.address(NS_VALUE);
    private static final long NS_PREFIX_ADDRESS = Buffers.address(NS_PREFIX);

    static final CharArray NAMESPACE = CharArrays.constant(NS_VALUE);

    public static final ImmutableMap<String, Supplier<DocumentType>> DOCUMENT_TYPE;

    static {

        /**
         * <pre>
         *  <xsd:groupOLD name="documentType">
         * 		<xsd:choice>
         * 			<xsd:groupOLD ref="collectionDocs"/>
         * 			<xsd:groupOLD ref="legislativeDocs"/>
         * 			<xsd:groupOLD ref="debateDocs"/>
         * 			<xsd:groupOLD ref="amendmentDocs"/>
         * 			<xsd:groupOLD ref="judgementDocs"/>
         * 			<xsd:element ref="doc"/>
         * 		</xsd:choice>
         * 	</xsd:groupOLD>
         * </pre>
         */
        DOCUMENT_TYPE = ImmutableMap.<String, Supplier<DocumentType>>builder()
                .put(Act.ELEMENT, Act::new)
                .put(Amendment.ELEMENT, Amendment::new)
                .put(AmendmentList.ELEMENT, AmendmentList::new)
                .put(Bill.ELEMENT, Bill::new)
//                    .put(Judgement.ELEMENT, Judgement.INSTANTIATOR)
                .put(DebateReport.ELEMENT, DebateReport::new)
                .put(Doc.ELEMENT, Doc::new)
                .put(Debate.ELEMENT, Debate::new)
                .put(DocumentCollection.ELEMENT, DocumentCollection::new)
                .put(OfficialGazette.ELEMENT, OfficialGazette::new)
                .build();
//        }
    }

    @Override
    public CharArray namespace() {
        return NAMESPACE;
    }

    @Override
    public void writeNamespace(XmlWriter writer) throws IOException {
        writer.writeNamespace(NS_PREFIX_ADDRESS,5, NS_VALUE_ADDRESS,29);
    }

    @Override
    public Supplier<Attribute> attributes(String name) {
        throw new IllegalStateException();
    }

    @Override
    public String toString() {
        return getClass().getName() + " for [" + NAMESPACE + "]";
    }

    @Override
    public int getVersion() {
        return 2;
    }

    @Override
    public AkomaNtosoContext newAkomaNtosoContext() {
        AkomaNtosoContext context = new AkomaNtosoContextV2();
        context.add(this);
        return context;
    }
}
