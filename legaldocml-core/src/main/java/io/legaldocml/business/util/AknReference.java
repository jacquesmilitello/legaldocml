package io.legaldocml.business.util;

import io.legaldocml.akn.AknObject;
import io.legaldocml.akn.AkomaNtoso;
import io.legaldocml.akn.DocumentType;
import io.legaldocml.akn.attribute.Link;
import io.legaldocml.akn.attribute.Refers;
import io.legaldocml.akn.attribute.Role;
import io.legaldocml.akn.element.RefItem;
import io.legaldocml.akn.element.References;
import io.legaldocml.akn.element.TLCPerson;
import io.legaldocml.akn.element.TLCRole;
import io.legaldocml.akn.group.TLC;
import io.legaldocml.akn.type.AgentRef;
import io.legaldocml.akn.type.ListReferenceRef;
import io.legaldocml.akn.type.RoleRef;
import io.legaldocml.unsafe.UnsafeString;
import io.legaldocml.util.Uri;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Optional;
import java.util.function.BiConsumer;

/**
 * @author <a href="mailto:jacques.militello@gmail.com">Jacques Militello</a>
 */
public abstract class AknReference implements BiConsumer<AknObject, AkomaNtoso<? extends DocumentType>> {

    /**
     * SLF4J Logger.
     */
    private static final Logger LOGGER = LoggerFactory.getLogger(AknReference.class);

    @SuppressWarnings("WeakerAccess")
    protected AknReference() {
    }

    public static AknReference refersTo(AgentRef source, TLCPerson refersTo) {
        return new AknReference() {
            @Override
            public void accept(AknObject object, AkomaNtoso<? extends DocumentType> akn) {

                if (LOGGER.isDebugEnabled()) {
                    LOGGER.debug("reference with source[{}] from [{}] refersTo [{}]",source, object, refersTo);
                }

                if (!(object instanceof Refers)) {
                    throw new AknReferenceException("Not a instance of Refers [" + object + "]");
                }

                ((Refers)object).setRefersTo(new ListReferenceRef(UnsafeString.getChars("#" + refersTo.getEid().toString())));

                References ref = akn.getDocumentType().getMeta().getReferences(source);

                if (ref == null) {
                    ref = new References();
                    ref.setSource(source);
                    akn.getDocumentType().getMeta().add(ref);
                }

                Optional<RefItem> op = ref.getRefItems().stream()
                        .filter( t -> t.equals(refersTo))
                        .findFirst();

                if (!op.isPresent()) {
                    ref.add(refersTo);
                }

            }
        };
    }

    public static AknReference as(AgentRef source, TLCRole role) {
        return new AknReference() {
            @Override
            public void accept(AknObject object, AkomaNtoso<? extends DocumentType> akn) {

                if (LOGGER.isDebugEnabled()) {
                    LOGGER.debug("reference with source[{}] from [{}] as [{}]",source, object, role);
                }

                if (!(object instanceof Role)) {
                    throw new AknReferenceException("Not a instance of Role [" + object + "]");
                }


                ((Role)object).setAs(RoleRef.valueOf(role.getEid()));

                References ref = akn.getDocumentType().getMeta().getReferences(source);

                if (ref == null) {
                    ref = new References();
                    ref.setSource(source);
                    akn.getDocumentType().getMeta().add(ref);
                }

                Optional<RefItem> op = ref.getRefItems().stream()
                        .filter( t -> t.equals(role))
                        .findFirst();

                if (!op.isPresent()) {
                    ref.add(role);
                }
            }
        };
    }

    public static AknReference href(AgentRef source, TLC tlc) {
        return new AknReference() {
            @Override
            public void accept(AknObject object, AkomaNtoso<? extends DocumentType> akn) {

                if (LOGGER.isDebugEnabled()) {
                    LOGGER.debug("reference with source [{}] from [{}] as [{}]",source, object, tlc);
                }

                if (!(object instanceof Link)) {
                    throw new AknReferenceException("Not a instance of Role [" + object + "]");
                }

                ((Link)object).setHref(Uri.valueOf(tlc.getEid().makeRef()));

                References ref = akn.getDocumentType().getMeta().getReferences(source);

                if (ref == null) {
                    ref = new References();
                    ref.setSource(source);
                    akn.getDocumentType().getMeta().add(ref);
                }

                Optional<RefItem> op = ref.getRefItems().stream()
                        .filter( t -> t.equals(tlc))
                        .findFirst();


                if (!op.isPresent()) {
                    ref.add(tlc);
                }

            }
        };
    }


}
