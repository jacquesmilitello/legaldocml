package io.legaldocml.akn.util;

import io.legaldocml.akn.element.TLCConcept;
import io.legaldocml.akn.element.TLCLocation;
import io.legaldocml.akn.element.TLCOrganization;
import io.legaldocml.akn.element.TLCPerson;
import io.legaldocml.akn.element.TLCRole;
import io.legaldocml.akn.element.TLCTerm;
import io.legaldocml.akn.type.NoWhiteSpace;
import io.legaldocml.akn.type.Uri;

/**
 * @author <a href="mailto:jacques.militello@gmail.com">Jacques Militello</a>
 */
public final class TLCFactory {

    private TLCFactory() {
    }

    public static TLCPerson newTLCPerson(NoWhiteSpace eid, Uri href, String showAs) {
        TLCPerson person = new TLCPerson();
        person.setEid(eid);
        person.setHref(href);
        person.setShowAs(showAs);
        return person;
    }

    public static TLCRole newTLCRole(NoWhiteSpace eid, Uri href, String showAs) {
        TLCRole role = new TLCRole();
        role.setEid(eid);
        role.setHref(href);
        role.setShowAs(showAs);
        return role;
    }

    public static TLCOrganization newTLCOrganization(NoWhiteSpace eid, Uri href, String showAs) {
        TLCOrganization organization = new TLCOrganization();
        organization.setEid(eid);
        organization.setHref(href);
        organization.setShowAs(showAs);
        return organization;
    }

    public static TLCLocation newTLCLocation(NoWhiteSpace eid, Uri href, String showAs) {
        TLCLocation location = new TLCLocation();
        location.setEid(eid);
        location.setHref(href);
        location.setShowAs(showAs);
        return location;
    }

    public static TLCConcept newTLCConcept(NoWhiteSpace eid, Uri href, String showAs) {
        TLCConcept concept = new TLCConcept();
        concept.setEid(eid);
        concept.setHref(href);
        concept.setShowAs(showAs);
        return concept;
    }

    public static TLCTerm newTLCTerm(NoWhiteSpace eid, Uri href, String showAs) {
        TLCTerm term = new TLCTerm();
        term.setEid(eid);
        term.setHref(href);
        term.setShowAs(showAs);
        return term;
    }
}
