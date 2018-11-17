package io.legaldocml.xliff.util;

import io.legaldocml.io.XmlWriter;
import io.legaldocml.xliff.attribute.Id;

import java.io.IOException;

public final class XmlWriterHelper {

    private XmlWriterHelper() {
    }


    public static void writeIdOpt(XmlWriter writer, Id id) throws IOException {
/*
        if (writer.getVersion() == 2) {
            if (idOpt.getId() != null) {
                writer.writeAttribute(Attributes.ADDRESS_ID, 2, getChars(idOpt.getId()));
            }

            if (idOpt.getEvolvingId() != null) {
                writer.writeAttribute(Attributes.ADDRESS_EVOLVING_ID, 10, getChars(idOpt.getEvolvingId()));
            }
        } else {
            if (idOpt.getEid() != null) {
                writer.writeAttribute(Attributes.ADDRESS_EID, 3, idOpt.getEid().getChars());
            }
            if (idOpt.getWid() != null) {
                writer.writeAttribute(Attributes.ADDRESS_WID, 3, idOpt.getWid().getChars());
            }

        }
*/
    }

}
