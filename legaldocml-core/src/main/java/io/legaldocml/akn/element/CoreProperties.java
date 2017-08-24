package io.legaldocml.akn.element;

import io.legaldocml.akn.AknObject;
import io.legaldocml.akn.MandatoryElementException;
import io.legaldocml.akn.util.AknList;
import io.legaldocml.io.XmlReader;
import io.legaldocml.io.XmlWriter;

import java.io.IOException;

/**
 * The complexType coreProperties lists the identifying properties available at any of the FRBR hierarchy levels.
 * <p>
 * <pre>
 *   <xsd:complexType name="coreProperties">
 *     <xsd:sequence>
 * 		 <xsd:element ref="FRBRthis"/>
 * 		 <xsd:element ref="FRBRuri" minOccurs="1" maxOccurs="unbounded"/>
 * 		 <xsd:element ref="FRBRalias" minOccurs="0" maxOccurs="unbounded"/>
 * 		 <xsd:element ref="FRBRdate"/>
 * 		 <xsd:element ref="FRBRauthor" minOccurs="1" maxOccurs="unbounded"/>
 * 		 <xsd:element ref="componentInfo" minOccurs="0" maxOccurs="1"/>
 * 		 <xsd:element ref="preservation" minOccurs="0" maxOccurs="1"/>
 * 	   <xsd:sequence>
 *   <xsd:complexType>
 * </pre>
 *
 * @author <a href="mailto:jacques.militello@gmail.com">Jacques Militello</a>
 */
public abstract class CoreProperties implements AknObject {

    // Mandatory
    private final FRBRthis frbrThis = new FRBRthis();

    // Mandatory (min 1)
    private final AknList<FRBRuri> uris = new AknList<>(new FRBRuri[2]);

    // Optional
    private AknList<FRBRalias> aliases;

    // Mandatory
    private final FRBRdate date = new FRBRdate();

    // Mandatory (min 1)
    private final AknList<FRBRauthor> authors = new AknList<>(new FRBRauthor[4]);

    // Optional
    private ComponentInfo componentInfo;

    // Optional
    private Preservation preservation;

    public final FRBRthis getFRBRthis() {
        return this.frbrThis;
    }

    public final void addFRBRuri(FRBRuri fRBRuri) {
        this.uris.add(fRBRuri);
    }

    public final FRBRdate getFRBRdate() {
        return this.date;
    }

    public final AknList<FRBRauthor> getAuthors() {
        return this.authors;
    }

    public final void add(FRBRauthor author) {
        this.authors.add(author);
    }

    public final ComponentInfo getComponentInfo() {
        return this.componentInfo;
    }

    public final void setComponentInfo(ComponentInfo componentInfo) {
        this.componentInfo = componentInfo;
    }

    public final Preservation getPreservation() {
        return this.preservation;
    }

    public final void setPreservation(Preservation preservation) {
        this.preservation = preservation;
    }

    @Override
    public void write(XmlWriter writer) throws IOException {
        this.frbrThis.write(writer);

        for (FRBRuri uri : this.uris) {
            uri.write(writer);
        }

        if (this.aliases != null) {
            for (FRBRalias alias : this.aliases) {
                alias.write(writer);
            }
        }

        this.date.write(writer);

        for (FRBRauthor author : this.authors) {
            author.write(writer);
        }

        if (componentInfo != null) {
            this.componentInfo.write(writer);
        }

        if (this.preservation != null) {
            this.preservation.write(writer);
        }

    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void read(XmlReader reader) {

        reader.nextStartOrEndElement();

        if (reader.getQName().equalsLocalName(FRBRthis.ELEMENT_FRBRTHIS)) {
            this.frbrThis.read(reader);
            reader.nextStartOrEndElement();
        } else {
            throw new MandatoryElementException(this, FRBRthis.ELEMENT_FRBRTHIS, reader);
        }

        if (reader.getQName().equalsLocalName(FRBRuri.ELEMENT)) {
            FRBRuri uri;
            do {
                uri = new FRBRuri();
                uri.read(reader);
                this.uris.add(uri);
                reader.nextStartOrEndElement();
            } while (reader.getQName().equalsLocalName(FRBRuri.ELEMENT));
        } else {
            throw new MandatoryElementException(this, FRBRuri.ELEMENT, reader);
        }

        if (reader.getQName().equalsLocalName(FRBRalias.ELEMENT)) {
            FRBRalias alias;
            this.aliases = new AknList<>(new FRBRalias[4]);
            do {
                alias = new FRBRalias();
                alias.read(reader);
                this.aliases.add(alias);
                reader.nextStartOrEndElement();
            } while (reader.getQName().equalsLocalName(FRBRalias.ELEMENT));
        }


        if (reader.getQName().equalsLocalName(FRBRdate.ELEMENT)) {
            this.date.read(reader);
            reader.nextStartOrEndElement();
        } else {
            throw new MandatoryElementException(this, FRBRdate.ELEMENT, reader);
        }

        if (reader.getQName().equalsLocalName(FRBRauthor.ELEMENT)) {
            FRBRauthor author;
            do {
                author = new FRBRauthor();
                author.read(reader);
                this.authors.add(author);
                reader.nextStartOrEndElement();
            } while (reader.getQName().equalsLocalName(FRBRauthor.ELEMENT));
        } else {
            throw new MandatoryElementException(this, FRBRauthor.ELEMENT, reader);
        }

        if (reader.getQName().equalsLocalName(ComponentInfo.ELEMENT)) {
            this.componentInfo = new ComponentInfo();
            this.componentInfo.read(reader);
            reader.nextStartOrEndElement();
        }

        if (reader.getQName().equalsLocalName(Preservation.ELEMENT)) {
            this.preservation = new Preservation();
            this.preservation.read(reader);
            reader.nextStartOrEndElement();
        }

    }

}