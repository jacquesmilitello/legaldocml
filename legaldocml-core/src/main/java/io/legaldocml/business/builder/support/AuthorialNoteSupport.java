package io.legaldocml.business.builder.support;

import io.legaldocml.akn.AknObject;
import io.legaldocml.akn.container.ANsubFlowContainer;
import io.legaldocml.akn.element.AuthorialNote;
import io.legaldocml.business.builder.element.SubFlowStructureBuilder;

import java.util.function.Consumer;

/**
 * @author <a href="mailto:charboubmustapha@gmail.com">Mustapha CHARBOUB</a>
 */
public interface AuthorialNoteSupport<T extends ANsubFlowContainer<E>, E extends AknObject> extends SupportBuilder<T> {

    default SubFlowStructureBuilder<AuthorialNote> authorialNote() {
        return authorialNote(null);
    }

    default SubFlowStructureBuilder<AuthorialNote> authorialNote(Consumer<AuthorialNote> consumer) {
        AuthorialNote authorialNote = new AuthorialNote();
        parent().add(authorialNote);
        if (consumer != null) {
            consumer.accept(authorialNote);
        }
        businessBuilder().getContext().push(parent(), authorialNote);
        return new SubFlowStructureBuilder<>(businessBuilder(), authorialNote);
    }

}