package io.legaldocml.module;

import io.legaldocml.LegalDocMlException;
import io.legaldocml.io.XmlReader;

public final class ModuleException extends LegalDocMlException {

    public enum Type {

        AKN_MODULE_NOT_FOUND ("AkomaNtoso namespace not found"),
        TWO_AKN_MODULES("The current XML has two akn modules [%s] - [%s]");

        private final String msg;

        Type(String msg) {
            this.msg = msg;
        }
    }

    public ModuleException(AknModule aknModule, Module module) {
        super(String.format(Type.TWO_AKN_MODULES.msg, aknModule, module));
    }

    public ModuleException(XmlReader reader) {
        super(Type.AKN_MODULE_NOT_FOUND.msg + " reader : " + reader);
    }
}
