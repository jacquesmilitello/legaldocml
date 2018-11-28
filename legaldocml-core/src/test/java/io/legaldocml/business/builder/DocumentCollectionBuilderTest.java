package io.legaldocml.business.builder;

import io.legaldocml.ReaderHelper;
import io.legaldocml.akn.element.Component;
import io.legaldocml.akn.element.Container;
import io.legaldocml.akn.element.DocumentCollection;
import io.legaldocml.akn.element.P;
import io.legaldocml.akn.element.TLCConcept;
import io.legaldocml.akn.element.TLCOrganization;
import io.legaldocml.akn.type.AgentRef;
import io.legaldocml.akn.type.NoWhiteSpace;
import io.legaldocml.akn.type.Uri;
import io.legaldocml.business.BusinessProvider;
import io.legaldocml.business.builder.element.ContainerTypeBuilder;
import io.legaldocml.business.builder.element.DocContainerTypeBuilder;
import io.legaldocml.business.builder.element.InlineTypeBuilder;
import io.legaldocml.io.XmlProvider;
import io.legaldocml.module.akn.v3.DefaultXmlWriterFactoryV3;
import io.legaldocml.test.LoggerInstancePostProcessor;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.w3c.dom.Document;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.nio.channels.Channels;
import java.time.LocalDate;

import static io.legaldocml.akn.util.TLCFactory.newTLCConcept;
import static io.legaldocml.akn.util.TLCFactory.newTLCOrganization;
import static io.legaldocml.business.util.AknReference.refersTo;
import static io.legaldocml.test.XmlUnitHelper.compare;

@ExtendWith(LoggerInstancePostProcessor.class)
class DocumentCollectionBuilderTest {

    private BusinessProvider provider = BusinessProvider.businessProvider("default");

    private static final AgentRef SOURCE = AgentRef.valueOf("palmirani");

    private static final TLCOrganization ORGANIZATION = newTLCOrganization(NoWhiteSpace.valueOf("cameraCommittee"),
            Uri.valueOf("/ontology/organizations/akn/uy/committee"),
            "Comisión de Constitución, Códigos, Legislación General y Administración");

    private static final TLCConcept CONCEPT_CARPETA = newTLCConcept(NoWhiteSpace.valueOf("carpeta"),
            Uri.valueOf("/ontology/concepts/akn/uy/carpeta"),
            "Carpeta");

    private static final TLCConcept CONCEPT_REPARTIDO = newTLCConcept(NoWhiteSpace.valueOf("repartido"),
            Uri.valueOf("/ontology/concepts/akn/uy/repartido"),
            "Repartido");


    @Test
    void testPreface() throws IOException {
        DocumentCollectionBusinessBuilder builder = new DocumentCollectionBusinessBuilder(provider, new DocumentCollection()) {
            @Override
            protected MetaBuilder newMetaBuilder() {
                return new MetaBuilder(this, SOURCE);
            }
        };

        addPreface(builder);

        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        XmlProvider.writerFactory(3).writePermissive(Channels.newChannel(baos), builder.getAkomaNtoso());

        Document expected = ReaderHelper.load("/xml/v3/uy_bill_2010-09-27.xml");
        Document actual = ReaderHelper.load(new ByteArrayInputStream(baos.toByteArray()));

        compare(
                expected.getElementsByTagNameNS("http://docs.oasis-open.org/legaldocml/ns/akn/3.0", "preface").item(0),
                actual.getElementsByTagNameNS("http://docs.oasis-open.org/legaldocml/ns/akn/3.0", "preface").item(0)
        );

    }

    @Test
    void testPreamble() throws IOException {
        DocumentCollectionBusinessBuilder builder = new DocumentCollectionBusinessBuilder(provider, new DocumentCollection()) {
            @Override
            protected MetaBuilder newMetaBuilder() {
                return new MetaBuilder(this, SOURCE);
            }
        };

        addPreamble(builder);

        new DefaultXmlWriterFactoryV3().writePermissive(Channels.newChannel(System.out), builder.getAkomaNtoso());

        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        XmlProvider.writerFactory(3).writePermissive(Channels.newChannel(baos), builder.getAkomaNtoso());

        Document expected = ReaderHelper.load("/xml/v3/uy_bill_2010-09-27.xml");
        Document actual = ReaderHelper.load(new ByteArrayInputStream(baos.toByteArray()));

        compare(
                expected.getElementsByTagNameNS("http://docs.oasis-open.org/legaldocml/ns/akn/3.0", "preamble").item(0),
                actual.getElementsByTagNameNS("http://docs.oasis-open.org/legaldocml/ns/akn/3.0", "preamble").item(0)
        );

    }

    @Test
    void testBody() throws IOException {
        DocumentCollectionBusinessBuilder builder = new DocumentCollectionBusinessBuilder(provider, new DocumentCollection()) {
            @Override
            protected MetaBuilder newMetaBuilder() {
                return new MetaBuilder(this, SOURCE);
            }
        };



        addBody(builder);

        new DefaultXmlWriterFactoryV3().writePermissive(Channels.newChannel(System.out), builder.getAkomaNtoso());

//        ByteArrayOutputStream baos = new ByteArrayOutputStream();
//        XmlProvider.writerFactory(3).writePermissive(Channels.newChannel(baos), builder.getAkomaNtoso());
//
//        Document expected = ReaderHelper.load("/xml/v3/uy_bill_2010-09-27.xml");
//        Document actual = ReaderHelper.load(new ByteArrayInputStream(baos.toByteArray()));
//
//        XmlUnitHelper.compare(
//                expected.getElementsByTagNameNS("http://docs.oasis-open.org/legaldocml/ns/akn/3.0", "preamble").item(0),
//                actual.getElementsByTagNameNS("http://docs.oasis-open.org/legaldocml/ns/akn/3.0", "preamble").item(0)
//        );

    }




    private void addPreface(DocumentCollectionBusinessBuilder builder) {

        ContainerTypeBuilder<Container> container = builder.preface().container("preface1", t -> {
            t.setEid(NoWhiteSpace.valueOf("preface__container_1"));
            t.setClazz("left");
        });

        container.p().docCommittee(refersTo(SOURCE, ORGANIZATION)).text("Comisión de Constitución, Códigos, Legislación General y Administración");
        container.p().docketNumber(refersTo(SOURCE, CONCEPT_CARPETA)).text("Carpeta Nº 395 de 2010");

        container = builder.preface().container("preface1", t -> {
            t.setEid(NoWhiteSpace.valueOf("preface__container_2"));
            t.setClazz("right");
        });

        InlineTypeBuilder<P> p = container.p().text("Repartido");
        p.docNumber(refersTo(SOURCE,CONCEPT_REPARTIDO)).text("Nº 379");
        p.docDate(LocalDate.of(2010,9,27)).text("Setiembre de 2010");

        container = builder.preface().container("title", t -> {
            t.setEid(NoWhiteSpace.valueOf("preface__container_3"));
            t.setClazz("center");
        });
        container.p().docTitle().text("ARTÍCULOS 1º, 3º Y 4º DE LA LEY Nº 15.848, DE CADUCIDAD DE LA PRETENSIÓN PUNITIVA DEL ESTADO");

    }

    private void addPreamble(DocumentCollectionBusinessBuilder builder) {
        InlineTypeBuilder<P> p = builder.preamble().p();
        p.text("Se declara como interpretación obligatoria que los mismos son violatorios");
        p.text(" de la Constitución de la República y se interpreta la misma en forma");
        p.text(" auténtica en cuanto a la incorporación al ordenamiento jurídico");
        p.text(" nacional de las normas internacionales en materia");
        p.text(" de derechos humanos ratificadas");
        p.text(" por la República");
    }


    private void addBody(DocumentCollectionBusinessBuilder builder) {

        DocContainerTypeBuilder<Component> component =  builder.body().component(t -> t.setEid(NoWhiteSpace.valueOf("cmp_1")));

     //   component.documentRef();

        component = builder.body().component( t -> t.setEid(NoWhiteSpace.valueOf("cmp_2")));

        /*
         <!-- The bill is numberd with Carpeta: 395-2010 -->
            <component eId="cmp_1">
                <documentRef eId="dref_1" href="#cmpnts__cmp_1" showAs="Proyecto de Ley"/>
            </component>
            <!-- An Informative part of the document-->
            <component eId="cmp_2">
                <documentRef eId="dref_2" href="#cmpnts__cmp_2" showAs="Motivos"/>
            </component>
                */
    }
}
