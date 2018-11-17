package io.legaldocml.xliff.element;

import com.google.common.collect.ImmutableMap;
import io.legaldocml.io.XmlReader;
import io.legaldocml.io.XmlWriter;
import io.legaldocml.xliff.util.XliffList;
import io.legaldocml.xliff.util.XmlReaderHelper;

import java.io.IOException;
import java.util.function.Supplier;

/**
 * <pre>
 *   <xs:element name="source">
 *     <xs:complexType mixed="true">
 *       <xs:group ref="xlf:inline" minOccurs="0" maxOccurs="unbounded"/>
 *       <xs:attribute ref="xml:lang" use="optional"/>
 *       <xs:attribute ref="xml:space" use="optional"/>
 *     </xs:complexType>
 *   </xs:element>
 * </pre>
 */
public final class Source implements XliffObject {

    private static final ImmutableMap<String, Supplier<MixedContent>> ELEMS;

    static {
        ELEMS = ImmutableMap.<String, Supplier<MixedContent>>builder()
                .build();
    }

    private final XliffList<MixedContent> contents = new XliffList<>(new MixedContent[2]);

    @Override
    public void write(XmlWriter writer) throws IOException {

    }

    @Override
    public void read(XmlReader reader) {

        XmlReaderHelper.read(reader, this.contents, ELEMS);

    }
}
