package io.legaldocml.schematron.model.attribute;

/**
 * ```
 * linkable =
 *      attribute role { roleValue }?,
 *      attribute subject { pathValue }?
 * ```
 *
 * @author <a href="mailto:jacques.militello@gmail.com">Jacques Militello</a>
 */
public interface Linkable {

    String getRole();

    void setRole(String role);

    String getSubject();

    void setSubject(String subject);

}
