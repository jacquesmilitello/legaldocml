package io.legaldocml.business.builder.support;

import io.legaldocml.akn.AknObject;
import io.legaldocml.akn.container.ANsemanticInlineContainer;
import io.legaldocml.akn.element.Term;
import io.legaldocml.akn.type.ListReferenceRef;
import io.legaldocml.business.builder.element.InlineReqReqTypeBuilder;

import java.util.function.Consumer;

/**
 * @author <a href="mailto:jacques.militello@gmail.com">Jacques Militello</a>
 */
public interface TermSupport<T extends ANsemanticInlineContainer<E>, E extends AknObject> extends SupportBuilder<T> {

    default InlineReqReqTypeBuilder<Term> term(ListReferenceRef refersTo) {
        return term(refersTo,null);
    }

    default InlineReqReqTypeBuilder<Term> term(ListReferenceRef refersTo,Consumer<Term> consumer) {
        Term term = new Term();
        term.setRefersTo(refersTo);
        parent().add(term);
        if (consumer != null) {
            consumer.accept(term);
        }
        return new InlineReqReqTypeBuilder<>(businessBuilder(), term);
    }

}
