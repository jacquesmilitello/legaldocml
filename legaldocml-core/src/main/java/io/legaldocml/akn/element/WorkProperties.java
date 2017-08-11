package io.legaldocml.akn.element;

import io.legaldocml.akn.util.AknList;
import io.legaldocml.io.XmlReader;
import io.legaldocml.io.XmlWriter;

import java.io.IOException;

/**
 * The group workProperties lists the properties that are characteristics of the FRBR Work level.
 * <p>
 * <pre>
 * 	<xsd:group name="workProperties">
 * 		<xsd:sequence>
 *          <xsd:element ref="FRBRcountry"/>
 *          <xsd:element ref="FRBRsubtype" minOccurs="0" maxOccurs="1"/>
 *          <xsd:element ref="FRBRnumber" minOccurs="0" maxOccurs="unbounded"/>
 *          <xsd:element ref="FRBRname" minOccurs="0" maxOccurs="unbounded"/>
 *          <xsd:element ref="FRBRprescriptive" minOccurs="0" maxOccurs="1"/>
 *          <xsd:element ref="FRBRauthoritative" minOccurs="0" maxOccurs="1"/>
 * 		</xsd:sequence>
 * 	</xsd:group>
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

        if (reader.getQName().equalsLocalName(FRBRcountry.ELEMENT)) {
            this.country.read(reader);
            reader.nextStartOrEndElement();
        } else {

        }

        if (reader.getQName().equalsLocalName(FRBRsubtype.ELEMENT)) {
            this.subtype = new FRBRsubtype();
            this.subtype.read(reader);
            reader.nextStartOrEndElement();
        }

        if (reader.getQName().equalsLocalName(FRBRnumber.ELEMENT)) {
            FRBRnumber number;
            this.numbers = new AknList<>(new FRBRnumber[4]);
            do {
                number = new FRBRnumber();
                number.read(reader);
                this.numbers.add(number);
                reader.nextStartOrEndElement();
            } while (reader.getQName().equalsLocalName(FRBRnumber.ELEMENT));
        }

        if (reader.getQName().equalsLocalName(FRBRname.ELEMENT)) {
            FRBRname name;
            this.names = new AknList<>(new FRBRname[4]);
            do {
                name = new FRBRname();
                name.read(reader);
                this.names.add(name);
                reader.nextStartOrEndElement();
            } while (reader.getQName().equalsLocalName(FRBRname.ELEMENT));
        }

        if (reader.getQName().equalsLocalName(FRBRprescriptive.ELEMENT)) {
            this.prescriptive = new FRBRprescriptive();
            this.prescriptive.read(reader);
            reader.nextStartOrEndElement();
        }

        if (reader.getQName().equalsLocalName(FRBRauthoritative.ELEMENT)) {
            this.authoritative = new FRBRauthoritative();
            this.authoritative.read(reader);
            reader.nextStartOrEndElement();
        }

    }

}