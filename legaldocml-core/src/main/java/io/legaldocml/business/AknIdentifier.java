package io.legaldocml.business;

import io.legaldocml.akn.AkomaNtoso;
import io.legaldocml.akn.DocumentType;
import io.legaldocml.akn.element.FRBRExpression;
import io.legaldocml.akn.element.FRBRManifestation;
import io.legaldocml.akn.element.FRBRWork;
import io.legaldocml.akn.element.Identification;
import io.legaldocml.util.Hashing;
import io.legaldocml.util.Strings;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author <a href="mailto:jacques.militello@gmail.com">Jacques Militello</a>
 */
public abstract class AknIdentifier {

    /**
     * SLF4J Logger.
     */
    private static final Logger LOGGER = LoggerFactory.getLogger(AknIdentifier.class);

    private int hash;

    public abstract String work();

    public abstract String expression();

    public abstract String expressionPart();

    public abstract String manifestation();

    public abstract String manifestationPart();

    public final <T extends DocumentType> void apply(AkomaNtoso<T> akn) {

        if (LOGGER.isDebugEnabled()) {
            LOGGER.debug("apply with work part [{}] - expression part [{}] - manifestation part [{}]", work(), expressionPart(), manifestationPart());
        }

        Identification identification = akn.getDocumentType().getMeta().getIdentification();

        FRBRWork frbrWork = identification.getFRBRWork();
        if (LOGGER.isDebugEnabled() && !Strings.isEmpty(frbrWork.getFRBRthis().getValue())) {
            LOGGER.debug("find FRBRWork with [{}]", frbrWork.getFRBRthis());
        }
        frbrWork.getFRBRthis().setValue(work());

        FRBRExpression frbrExpression = identification.getFRBRExpression();
        if (LOGGER.isDebugEnabled() && !Strings.isEmpty(frbrExpression.getFRBRthis().getValue())) {
            LOGGER.debug("find FRBRExpression with [{}]", frbrExpression.getFRBRthis());
        }
        frbrExpression.getFRBRthis().setValue(expression());

        FRBRManifestation frbrManifestation = identification.getFRBRManifestation();
        if (LOGGER.isDebugEnabled() && !Strings.isEmpty(frbrManifestation.getFRBRthis().getValue())) {
            LOGGER.debug("find FRBRManifestation with [{}]", frbrManifestation.getFRBRthis());
        }
        frbrManifestation.getFRBRthis().setValue(manifestation());
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public final boolean equals(Object obj) {
        return obj == this || obj != null && obj instanceof AknIdentifier && doEquals((AknIdentifier) obj);

    }

    /**
     * {@inheritDoc}
     */
    @Override
    public final int hashCode() {
        if (hash == 0) {
            String manifestation = manifestation();
            hash = (int) Hashing.xx(manifestation.length(), manifestation);
        }
        return hash;
    }

    protected abstract boolean doEquals(AknIdentifier aknIdentifier);

    public static <T extends DocumentType> AknIdentifier extract(BusinessProvider provider, AkomaNtoso<T> akn) {

        Identification identification = akn.getDocumentType().getMeta().getIdentification();

        String work = identification.getFRBRWork().getFRBRthis().getValue();
        if (Strings.isEmpty(work)) {
            throw AknIdentifierException.extract("Missing FRBRWork.FRBRthis.value", identification.getFRBRWork());
        }

        String expression = identification.getFRBRExpression().getFRBRthis().getValue();
        if (Strings.isEmpty(expression)) {
            throw AknIdentifierException.extract("Missing FRBRExpression.FRBRthis.value", identification.getFRBRExpression());
        }

        String manifestation = identification.getFRBRManifestation().getFRBRthis().getValue();
        if (Strings.isEmpty(manifestation)) {
            throw AknIdentifierException.extract("Missing FRBRManifestation.FRBRthis.value", identification.getFRBRManifestation());
        }

        return provider.extractAknIdentifier(work, expression, manifestation);

    }

    public static <T extends DocumentType> AknIdentifier extract(String provider, AkomaNtoso<T> akn) {
        return extract(BusinessProvider.businessProvider(provider), akn);
    }


    public static <T extends DocumentType> void consistent(AkomaNtoso<T> akn) {

        if (LOGGER.isDebugEnabled()) {
            LOGGER.debug("consistent for [{}]", akn);
        }

        Identification identification = akn.getDocumentType().getMeta().getIdentification();

        if (!identification.getFRBRExpression().getFRBRthis().getValue().startsWith(identification.getFRBRWork().getFRBRthis().getValue())) {
            throw AknIdentifierException.consistent(identification.getFRBRExpression(), identification.getFRBRWork());
        }

        if (!identification.getFRBRManifestation().getFRBRthis().getValue().startsWith(identification.getFRBRExpression().getFRBRthis().getValue())) {
            throw AknIdentifierException.consistent(identification.getFRBRManifestation(), identification.getFRBRExpression());
        }

    }

    public static <T extends DocumentType> boolean isEmpty(AkomaNtoso<T> akn) {

        Identification identification = akn.getDocumentType().getMeta().getIdentification();

        return Strings.isEmpty(identification.getFRBRWork().getFRBRthis().getValue())
                && Strings.isEmpty(identification.getFRBRExpression().getFRBRthis().getValue())
                && Strings.isEmpty(identification.getFRBRManifestation().getFRBRthis().getValue());
    }

}
