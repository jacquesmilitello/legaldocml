package io.legaldocml.business.builder.support;

import static io.legaldocml.business.util.AknReference.refersTo;

import java.util.function.Consumer;

import io.legaldocml.akn.container.InlineCMContainer;
import io.legaldocml.akn.element.Organization;
import io.legaldocml.akn.element.TLCOrganization;
import io.legaldocml.business.builder.element.InlineReqReqTypeBuilder;

/**
 * @author <a href="mailto:jacques.militello@gmail.com">Jacques Militello</a>
 */
public interface OrganizationSupport<T extends InlineCMContainer> extends SupportBuilder<T> {

    default InlineReqReqTypeBuilder<Organization> organization(TLCOrganization tlcOrganization) {
        return organization(tlcOrganization, null);
    }

    default InlineReqReqTypeBuilder<Organization> organization(TLCOrganization tlcOrganization, Consumer<Organization> consumer) {
        Organization organization = new Organization();
        parent().add(organization);
        businessBuilder().getContext().push(parent(), organization);
        refersTo(businessBuilder().getSource(), tlcOrganization).accept(organization, businessBuilder().getAkomaNtoso());

        if (consumer != null) {
            consumer.accept(organization);
        }

        return new InlineReqReqTypeBuilder<>(businessBuilder(), organization);
    }

}