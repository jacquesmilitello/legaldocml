package io.legaldocml.business.builder;

import io.legaldocml.akn.AknElements;
import io.legaldocml.akn.element.Article;
import io.legaldocml.akn.element.Bill;
import io.legaldocml.akn.element.I;
import io.legaldocml.akn.element.Intro;
import io.legaldocml.akn.element.List;
import io.legaldocml.akn.element.Paragraph;
import io.legaldocml.business.BusinessProvider;
import io.legaldocml.business.builder.element.BlocksBuilder;
import io.legaldocml.business.builder.element.HierarchyBuilder;
import io.legaldocml.business.builder.element.InlineTypeBuilder;
import io.legaldocml.io.XmlProvider;
import io.legaldocml.test.LoggerInstancePostProcessor;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;

import java.io.IOException;
import java.nio.channels.Channels;

@ExtendWith(LoggerInstancePostProcessor.class)
class HierarchyBuilderTest {

    @Test
    void testNum() throws IOException {

        BusinessProvider provider = BusinessProvider.businessProvider("default");
        BusinessBuilder<Bill, ?> businessBuilder = provider.newBuilder(AknElements.BILL);

        Article article = new Article();
        businessBuilder.getAkomaNtoso().getDocumentType().getBody().add(article);
        businessBuilder.getContext().push(businessBuilder.getAkomaNtoso().getDocumentType().getBody(), article);

        HierarchyBuilder<Article> builder = new HierarchyBuilder<>(businessBuilder, article);
        builder.eIdNumber("1");
        builder.num().text("Art. 1.");
        builder.heading()
                .i()
                .text("(Modifiche al sistema elettorale della")
                .<InlineTypeBuilder<I>>eol()
                .text("Camera dei deputati)");

        HierarchyBuilder<Paragraph> paragraphBuilder = builder.paragraph();
        paragraphBuilder.num().text("1.");
        paragraphBuilder.eIdNumber("1");

        HierarchyBuilder<List> listBuilder = paragraphBuilder.list();
        listBuilder.eIdNumber("1");
        BlocksBuilder<Intro> introBlocksBuilder = listBuilder.intro();
        introBlocksBuilder.eIdNumber();
        introBlocksBuilder.p().text("Al testo unico delle leggi recanti norme per la elezione della Camera dei deputati, di cui al decreto del Presidente della Repubblica 30 marzo 1957, n. 361, sono apportate le seguenti modificazioni:");

        XmlProvider.writerFactory(3).writePermissive(Channels.newChannel(System.out), businessBuilder.getAkomaNtoso());
    }

}
