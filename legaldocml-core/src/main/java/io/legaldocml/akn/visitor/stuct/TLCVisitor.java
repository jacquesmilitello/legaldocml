package io.legaldocml.akn.visitor.stuct;

import io.legaldocml.akn.element.TLCConcept;
import io.legaldocml.akn.element.TLCEvent;
import io.legaldocml.akn.element.TLCLocation;
import io.legaldocml.akn.element.TLCOrganization;
import io.legaldocml.akn.element.TLCPerson;
import io.legaldocml.akn.element.TLCProcess;
import io.legaldocml.akn.element.TLCReference;
import io.legaldocml.akn.element.TLCRole;

public interface TLCVisitor {

    default void visit(TLCLocation location) {
        // default -> nothing to do.
    }

    default void visit(TLCOrganization organization) {
        // default -> nothing to do.
    }

    default void visit(TLCPerson person) {
        // default -> nothing to do.
    }

    default void visit(TLCRole role) {
        // default -> nothing to do.
    }

    default void visit(TLCConcept concept) {
        // default -> nothing to do.
    }

    default void visit(TLCReference reference) {
        // default -> nothing to do.
    }

    default void visit(TLCEvent event) {
        // default -> nothing to do.
    }

    default void visit(TLCProcess process) {
        // default -> nothing to do.
    }
}
