package io.legaldocml.module.xml;

import io.legaldocml.model.Language;
import io.legaldocml.module.xml.attribute.XmlId;
import io.legaldocml.module.xml.attribute.XmlLang;
import io.legaldocml.module.xml.attribute.XmlSpace;
import io.legaldocml.module.xml.type.Space;

/**
 * @author <a href="mailto:jacques.militello@gmail.com">Jacques Militello</a>
 */
public final class Factory {

    public static XmlId xmlId(String value) {
        XmlId xmlId = new XmlIdImpl();
        xmlId.setXmlId(value);
        return xmlId;
    }

    public static XmlLang xmlLang(Language language) {
        XmlLang lang = new XmlLangImpl();
        lang.setXmlLang(language);
        return lang;
    }

    public static XmlSpace xmlSpace(Space space) {
        XmlSpace xmlSpace = new XmlSpaceImpl();
        xmlSpace.setSpace(space);
        return xmlSpace;
    }
}
