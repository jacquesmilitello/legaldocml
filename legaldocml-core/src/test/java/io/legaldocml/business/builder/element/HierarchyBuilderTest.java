package io.legaldocml.business.builder.element;

import io.legaldocml.akn.AknElements;
import io.legaldocml.akn.element.Chapter;
import io.legaldocml.business.BusinessProvider;
import io.legaldocml.business.builder.BusinessBuilder;
import io.legaldocml.business.builder.BusinessBuilderException;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class HierarchyBuilderTest {

    @Test
    void testNumTwive() {
        BusinessBuilder businessBuilder = BusinessProvider.businessProvider("default").newBuilder(AknElements.AMENDMENT);
        HierarchyBuilder<Chapter> hierarchyBuilder = new HierarchyBuilder<Chapter>(businessBuilder, new Chapter());
        hierarchyBuilder.num();

        Assertions.assertThrows(BusinessBuilderException.class, () -> hierarchyBuilder.num());
    }


}
