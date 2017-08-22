package io.legaldocml.akn.element;

import io.legaldocml.akn.util.AknList;
import io.legaldocml.io.XmlReader;
import io.legaldocml.io.XmlWriter;

import java.io.IOException;

/**
 * The group exprProperties lists the properties that are characteristics of the FRBR Expression level.
 *
 * <pre>
 *   &lt;xsd:group name="exprProperties"&gt;
 * 	   &lt;xsd:sequence&gt;
 * 	     &lt;xsd:element ref="FRBRversionNumber" minOccurs="0" maxOccurs="1"/&gt;
 *       &lt;xsd:element ref="FRBRauthoritative" minOccurs="0" maxOccurs="1"/&gt;
 *       &lt;xsd:element ref="FRBRmasterExpression" minOccurs="0" maxOccurs="1"/&gt;
 *       &lt;xsd:element ref="FRBRlanguage" minOccurs="1" maxOccurs="unbounded"/&gt;
 *       &lt;xsd:element ref="FRBRtranslation" minOccurs="0" maxOccurs="unbounded"/&gt;
 * 	   &lt;xsd:sequence&gt;
 *   &lt;xsd:group&gt;
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


    public final void add(FRBRlanguage language) {
        this.languages.add(language);
    }

    public final void add(FRBRtranslation translation) {
        if (this.translations == null) {
            this.translations = new AknList<>(new FRBRtranslation[4]);
        }
        this.translations.add(translation);
    }

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

        if (reader.getQName().equalsLocalName(FRBRlanguage.ELEMENT_FRBR_LANGUAGE)) {
            FRBRlanguage language;
            do {
                language = new FRBRlanguage();
                language.read(reader);
                this.languages.add(language);
                reader.nextStartOrEndElement();
            } while (reader.getQName().equalsLocalName(FRBRlanguage.ELEMENT_FRBR_LANGUAGE));
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