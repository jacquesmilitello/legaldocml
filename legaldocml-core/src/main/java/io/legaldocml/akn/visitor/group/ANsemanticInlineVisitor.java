package io.legaldocml.akn.visitor.group;


import io.legaldocml.akn.element.Concept;
import io.legaldocml.akn.element.Date;
import io.legaldocml.akn.element.Def;
import io.legaldocml.akn.element.Entity;
import io.legaldocml.akn.element.Event;
import io.legaldocml.akn.element.Location;
import io.legaldocml.akn.element.Object;
import io.legaldocml.akn.element.Organization;
import io.legaldocml.akn.element.Person;
import io.legaldocml.akn.element.Process;
import io.legaldocml.akn.element.Quantity;
import io.legaldocml.akn.element.Role;
import io.legaldocml.akn.element.Term;
import io.legaldocml.akn.element.Time;

/**
 * Visitor for {@link io.legaldocml.akn.group.ANsemanticInline}
 *
 * @author <a href="mailto:jacques.militello@gmail.com">Jacques Militello</a>
 */
public interface ANsemanticInlineVisitor {

    default boolean visitEnter(Date date) {
        return true;
    }

    default void visitLeave(Date date) {
    }

    default boolean visitEnter(Time time) {
        return true;
    }

    default void visitLeave(Time time) {
    }

    default boolean visitEnter(Person person) {
        return true;
    }

    default void visitLeave(Person person) {
    }

    default boolean visitEnter(Organization organization) {
        return true;
    }

    default void visitLeave(Organization organization) {
    }

    default boolean visitEnter(Concept concept) {
        return true;
    }

    default void visitLeave(Concept concept) {
    }

    default boolean visitEnter(Object object) {
        return true;
    }

    default void visitLeave(Object object) {
    }

    default boolean visitEnter(Event event) {
        return true;
    }

    default void visitLeave(Event event) {
    }

    default boolean visitEnter(Location location) {
        return true;
    }

    default void visitLeave(Location location) {
    }

    default boolean visitEnter(Process process) {
        return true;
    }

    default void visitLeave(Process process) {
    }

    default boolean visitEnter(Role role) {
        return true;
    }

    default void visitLeave(Role role) {
    }

    default boolean visitEnter(Term term) {
        return true;
    }

    default void visitLeave(Term term) {
    }

    default boolean visitEnter(Quantity quantity) {
        return true;
    }

    default void visitLeave(Quantity quantity) {
    }

    default boolean visitEnter(Def def) {
        return true;
    }

    default void visitLeave(Def def) {
    }

    default boolean visitEnter(Entity entity) {
        return true;
    }

    default void visitLeave(Entity entity) {
    }

}
