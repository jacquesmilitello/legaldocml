package io.legaldocml.akn.element;

import io.legaldocml.akn.util.AknList;
import io.legaldocml.io.XmlReader;
import io.legaldocml.io.XmlWriter;

import java.io.IOException;

/**
 * The group exprProperties lists the properties that are characteristics of the FRBR Expression level.
 * <p>
 * <pre>
 *   <xsd:group name="exprProperties">
 * 	   <xsd:sequence>
 * 	     <xsd:element ref="FRBRversionNumber" minOccurs="0" maxOccurs="1"/>
 *       <xsd:element ref="FRBRauthoritative" minOccurs="0" maxOccurs="1"/>
 *       <xsd:element ref="FRBRmasterExpression" minOccurs="0" maxOccurs="1"/>
 *       <xsd:element ref="FRBRlanguage" minOccurs="1" maxOccurs="unbounded"/>
 *       <xsd:element ref="FRBRtranslation" minOccurs="0" maxOccurs="unbounded"/>
 * 	   </xsd:sequence>
 *   </xsd:group>
 * </pre>
 *
 * @author <a href="mailto:jacques.militello@gmail.com">Jacques Militello</a>
 */
public abstract class ExprProperties extends CoreProperties {

    // Optional
    private FRBRversionNumber versionNumber;

    // Optional
    private FRBRauthoritative authoritative;

    // Optional
    private FRBRmasterExpression masterExpression;

    // Mandatory
    private final AknList<FRBRlanguage> languages = new AknList<>(new FRBRlanguage[2]);

    // Optional
    private AknList<FRBRtranslation> translations;

    /**
     * {@inheritDoc}
     */
    @Override
    public void write(XmlWriter writer) throws IOException {
        super.write(writer);
        if (this.versionNumber != null) {
            this.versionNumber.write(writer);
        }
        if (this.authoritative != null) {
            this.authoritative.write(writer);
        }
        if (this.masterExpression != null) {
            this.masterExpression.write(writer);
        }
        this.languages.write(writer);
        if (this.translations != null) {
            this.translations.write(writer);
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void read(XmlReader reader) {
        super.read(reader);

        if (reader.getQName().equalsLocalName(FRBRversionNumber.ELEMENT)) {
            this.versionNumber = new FRBRversionNumber();
            this.versionNumber.read(reader);
            reader.nextStartOrEndElement();
        }

        if (reader.getQName().equalsLocalName(FRBRauthoritative.ELEMENT)) {
            this.authoritative = new FRBRauthoritative();
            this.authoritative.read(reader);
            reader.nextStartOrEndElement();
        }

        if (reader.getQName().equalsLocalName(FRBRmasterExpression.ELEMENT)) {
            this.masterExpression = new FRBRmasterExpression();
            this.masterExpression.read(reader);
            reader.nextStartOrEndElement();
        }

        if (reader.getQName().equalsLocalName(FRBRlanguage.ELEMENT)) {
            FRBRlanguage language;
            do {
                language = new FRBRlanguage();
                language.read(reader);
                this.languages.add(language);
                reader.nextStartOrEndElement();
            } while (reader.getQName().equalsLocalName(FRBRlanguage.ELEMENT));
        }

        if (reader.getQName().equalsLocalName(FRBRtranslation.ELEMENT)) {
            FRBRtranslation translation;
            this.translations = new AknList<>(new FRBRtranslation[4]);
            do {
                translation = new FRBRtranslation();
                translation.read(reader);
                this.translations.add(translation);
                reader.nextStartOrEndElement();
            } while (reader.getQName().equalsLocalName(FRBRtranslation.ELEMENT));
        }

    }

}