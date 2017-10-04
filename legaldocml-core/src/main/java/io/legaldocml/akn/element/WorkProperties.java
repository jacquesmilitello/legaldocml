package io.legaldocml.akn.element;

import io.legaldocml.akn.util.AknList;
import io.legaldocml.akn.visitor.AknVisitor;
import io.legaldocml.io.XmlReader;
import io.legaldocml.io.XmlWriter;

import java.io.IOException;

import static io.legaldocml.akn.AknElements.FRBR_AUTHORITATIVE;
import static io.legaldocml.akn.AknElements.FRBR_COUNTRY;
import static io.legaldocml.akn.AknElements.FRBR_NAME;
import static io.legaldocml.akn.AknElements.FRBR_NUMBER;
import static io.legaldocml.akn.AknElements.FRBR_PRESCRIPTIVE;
import static io.legaldocml.akn.AknElements.FRBR_SUBTYPE;

/**
 * The group workProperties lists the properties that are characteristics of the FRBR Work level.
 *
 * <pre>
 * 	<xsd:group name="workProperties">
 * 		<xsd:sequence>
 *          <xsd:element ref="FRBRcountry"/>
 *          <xsd:element ref="FRBRsubtype" minOccurs="0" maxOccurs="1"/>
 *          <xsd:element ref="FRBRnumber" minOccurs="0" maxOccurs="unbounded"/>
 *          <xsd:element ref="FRBRname" minOccurs="0" maxOccurs="unbounded"/>
 *          <xsd:element ref="FRBRprescriptive" minOccurs="0" maxOccurs="1"/>
 *          <xsd:element ref="FRBRauthoritative" minOccurs="0" maxOccurs="1"/>
 * 		<xsd:sequence>
 * 	<xsd:group>
 * </pre>
 *
 * @author <a href="mailto:jacques.militello@gmail.com">Jacques Militello</a>
 */
public abstract class WorkProperties extends CoreProperties {

    // Mandatory
    private final FRBRcountry country = new FRBRcountry();

    // Optional
    private FRBRsubtype subtype;

    // Optional
    private AknList<FRBRnumber> numbers;

    // Optional
    private AknList<FRBRname> names;

    // Optional
    private FRBRprescriptive prescriptive;

    // Optional
    private FRBRauthoritative authoritative;

    public final FRBRcountry getFRBRcountry() {
        return this.country;
    }

    public FRBRsubtype getSubtype() {
        return subtype;
    }

    public void setSubtype(FRBRsubtype subtype) {
        this.subtype = subtype;
    }

    public FRBRprescriptive getPrescriptive() {
        return prescriptive;
    }

    public void setPrescriptive(FRBRprescriptive prescriptive) {
        this.prescriptive = prescriptive;
    }

    public FRBRauthoritative getAuthoritative() {
        return authoritative;
    }

    public void setAuthoritative(FRBRauthoritative authoritative) {
        this.authoritative = authoritative;
    }

    public void add(FRBRnumber number) {
        if (this.numbers == null) {
            this.numbers = new AknList<>(new FRBRnumber[2]);
        }
        this.numbers.add(number);
    }

    public void add(FRBRname name) {
        if (this.names == null) {
            this.names = new AknList<>(new FRBRname[2]);
        }
        this.names.add(name);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void write(XmlWriter writer) throws IOException {
        super.write(writer);
        this.country.write(writer);
        if (this.subtype != null) {
            this.subtype.write(writer);
        }
        if (this.numbers != null) {
            this.numbers.write(writer);
        }
        if (this.names != null) {
            this.names.write(writer);
        }
        if (this.prescriptive != null) {
            this.prescriptive.write(writer);
        }
        if (this.authoritative != null) {
            this.authoritative.write(writer);
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void read(XmlReader reader) {
        super.read(reader);

        if (reader.getQName().equalsLocalName(FRBR_COUNTRY)) {
            this.country.read(reader);
            reader.nextStartOrEndElement();
        } else {

        }

        if (reader.getQName().equalsLocalName(FRBR_SUBTYPE)) {
            this.subtype = new FRBRsubtype();
            this.subtype.read(reader);
            reader.nextStartOrEndElement();
        }

        if (reader.getQName().equalsLocalName(FRBR_NUMBER)) {
            FRBRnumber number;
            this.numbers = new AknList<>(new FRBRnumber[4]);
            do {
                number = new FRBRnumber();
                number.read(reader);
                this.numbers.add(number);
                reader.nextStartOrEndElement();
            } while (reader.getQName().equalsLocalName(FRBR_NUMBER));
        }

        if (reader.getQName().equalsLocalName(FRBR_NAME)) {
            FRBRname name;
            this.names = new AknList<>(new FRBRname[4]);
            do {
                name = new FRBRname();
                name.read(reader);
                this.names.add(name);
                reader.nextStartOrEndElement();
            } while (reader.getQName().equalsLocalName(FRBR_NAME));
        }

        if (reader.getQName().equalsLocalName(FRBR_PRESCRIPTIVE)) {
            this.prescriptive = new FRBRprescriptive();
            this.prescriptive.read(reader);
            reader.nextStartOrEndElement();
        }

        if (reader.getQName().equalsLocalName(FRBR_AUTHORITATIVE)) {
            this.authoritative = new FRBRauthoritative();
            this.authoritative.read(reader);
            reader.nextStartOrEndElement();
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void accept(AknVisitor visitor) {
        super.accept(visitor);
        country.accept(visitor);
        if (this.subtype != null) {
            this.subtype.accept(visitor);
        }
        if (this.numbers != null) {
            this.numbers.accept(visitor);
        }
        if (this.names != null) {
            this.names.accept(visitor);
        }
        if (this.prescriptive != null) {
            this.prescriptive.accept(visitor);
        }
        if (this.authoritative != null) {
            this.authoritative.accept(visitor);
        }
    }
}