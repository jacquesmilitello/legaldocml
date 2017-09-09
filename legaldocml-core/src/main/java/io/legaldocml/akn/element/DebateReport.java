package io.legaldocml.akn.element;

import io.legaldocml.akn.DocumentType;
import io.legaldocml.io.impl.Buffers;
import io.legaldocml.io.XmlWriter;

import java.io.IOException;

import static io.legaldocml.akn.AknElements.DEBATE_REPORT;

/**
 * Element debateReport is used for descriving the structure and content of a report.
 *
 * <pre>
 *   <xsd:element name="debateReport" type="openStructure">
 * 	   <xsd:unique name="eId-debateReport">
 *       <xsd:selector xpath=".//*"/>
 *       <xsd:field xpath="@eId"/>
 *     <xsd:unique>
 *     <xsd:unique name="GUID-debateReport">
 *       <xsd:selector xpath=".//*"/>
 *       <xsd:field xpath="@GUID"/>
 *     <xsd:unique>
 * 	 <xsd:element>
 * </pre>
 *
 * @author <a href="mailto:jacques.militello@gmail.com">Jacques Militello</a>
 */
public final class DebateReport extends OpenStructure implements DocumentType {

    /**
     * Memory address.
     */
    private static final long ADDRESS_DEBATE_REPORT = Buffers.address(DEBATE_REPORT);

    /**
     * {@inheritDoc}
     */
    @Override
    public void write(XmlWriter writer) throws IOException {
        writer.writeStart(ADDRESS_DEBATE_REPORT, 12);
        super.write(writer);
        writer.writeEnd(ADDRESS_DEBATE_REPORT, 12);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String name() {
        return DEBATE_REPORT;
    }

}