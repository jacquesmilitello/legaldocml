package io.legaldocml.akn.element;

import io.legaldocml.akn.AknObject;
import io.legaldocml.akn.exception.WriterMandatoryElementException;
import io.legaldocml.akn.util.AknList;
import io.legaldocml.akn.visitor.AknVisitor;
import io.legaldocml.io.XmlReader;
import io.legaldocml.io.XmlWriter;

import java.io.IOException;

import static io.legaldocml.akn.AknElements.COMPONENT_INFO;
import static io.legaldocml.akn.AknElements.FRBR_ALIAS;
import static io.legaldocml.akn.AknElements.FRBR_AUTHOR;
import static io.legaldocml.akn.AknElements.FRBR_DATE;
import static io.legaldocml.akn.AknElements.FRBR_THIS;
import static io.legaldocml.akn.AknElements.FRBR_URI;
import static io.legaldocml.akn.AknElements.PRESERVATION;

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

    private AknObject parent;

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

    /**
     * {@inheritDoc}
     */
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

        if (reader.getQName().equalsLocalName(FRBR_THIS)) {
            this.frbrThis.read(reader);
            reader.nextStartOrEndElement();
        } else {
            throw new WriterMandatoryElementException(this, FRBR_THIS, reader);
        }

        if (reader.getQName().equalsLocalName(FRBR_URI)) {
            FRBRuri uri;
            do {
                uri = new FRBRuri();
                uri.read(reader);
                this.uris.add(uri);
                reader.nextStartOrEndElement();
            } while (reader.getQName().equalsLocalName(FRBR_URI));
        } else {
            throw new WriterMandatoryElementException(this, FRBR_URI, reader);
        }

        if (reader.getQName().equalsLocalName(FRBR_ALIAS)) {
            FRBRalias alias;
            this.aliases = new AknList<>(new FRBRalias[4]);
            do {
                alias = new FRBRalias();
                alias.read(reader);
                this.aliases.add(alias);
                reader.nextStartOrEndElement();
            } while (reader.getQName().equalsLocalName(FRBR_ALIAS));
        }


        if (reader.getQName().equalsLocalName(FRBR_DATE)) {
            this.date.read(reader);
            reader.nextStartOrEndElement();
        } else {
            throw new WriterMandatoryElementException(this, FRBR_DATE, reader);
        }

        if (reader.getQName().equalsLocalName(FRBR_AUTHOR)) {
            FRBRauthor author;
            do {
                author = new FRBRauthor();
                author.read(reader);
                this.authors.add(author);
                reader.nextStartOrEndElement();
            } while (reader.getQName().equalsLocalName(FRBR_AUTHOR));
        } else {
            throw new WriterMandatoryElementException(this, FRBR_AUTHOR, reader);
        }

        if (reader.getQName().equalsLocalName(COMPONENT_INFO)) {
            this.componentInfo = new ComponentInfo();
            this.componentInfo.read(reader);
            reader.nextStartOrEndElement();
        }

        if (reader.getQName().equalsLocalName(PRESERVATION)) {
            this.preservation = new Preservation();
            this.preservation.read(reader);
            reader.nextStartOrEndElement();
        }

    }


    /**
     * {@inheritDoc}
     */
    @Override
    public void accept(AknVisitor visitor) {
        this.frbrThis.accept(visitor);
        this.uris.accept(visitor);
        if (this.aliases != null) {
            this.aliases.accept(visitor);
        }
        this.date.accept(visitor);
        this.authors.accept(visitor);
        if (this.componentInfo != null) {
            this.componentInfo.accept(visitor);
        }
        if (this.preservation != null) {
            this.preservation.accept(visitor);
        }
    }

    @SuppressWarnings("unchecked")
    public <T extends AknObject> T getParent() {
        return (T)parent;
    }

    public <T extends AknObject> void setParent(T parent) {
        this.parent = parent;
    }

}