package io.legaldocml.schematron.model.attribute;

import io.legaldocml.util.Uri;

/**
 * ```
 * rich =
 *      attribute icon { uriValue }?,
 *      attribute see { uriValue }?,
 *      attribute fpi { fpiValue }?,
 *      attribute xml:lang { langValue }?,
 *      attribute xml:space { "preserve" | "default" }?
 * ```
 *
 * @author <a href="mailto:jacques.militello@gmail.com">Jacques Militello</a>
 */
public interface Rich {

    Uri getIcon();

    void setIcon(Uri icon);

    Uri getSee();

    void setSee(Uri see);

    String getFpi();

    void setFpi(String fpi);

//    XmlLang getXmlLang();
//
//    void setXmlLang(XmlLang lang);
//
//    XmlSpace getXmlSpace();
//
//    void setXmlSpace(XmlSpace space);

}