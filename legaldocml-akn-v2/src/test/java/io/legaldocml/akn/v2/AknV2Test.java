package io.legaldocml.akn.v2;

import io.legaldocml.akn.AkomaNtoso;
import io.legaldocml.io.impl.Buffers;
import io.legaldocml.io.impl.XmlChannelWriter;
import io.legaldocml.io.XmlReaderFactory;
import io.legaldocml.io.XmlReaderFactoryProvider;
import org.junit.Assert;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.xmlunit.builder.DiffBuilder;
import org.xmlunit.diff.ComparisonControllers;
import org.xmlunit.diff.Diff;
import org.xmlunit.diff.Difference;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.MappedByteBuffer;
import java.nio.channels.FileChannel;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.EnumSet;
import java.util.Iterator;

public class AknV2Test {

    private static final Logger LOGGER = LoggerFactory.getLogger(AknV2Test.class);

    private static final XmlReaderFactory XML_READER_FACTORY = XmlReaderFactoryProvider.newXmlReaderFactory(2);

    @Test
    public void testAct() throws Exception {
        test(Paths.get(AknV2Test.class.getResource("/xml/Act_constitution_final-Kenya.xml").toURI()));
        test(Paths.get(AknV2Test.class.getResource("/xml/Act_Instrument_Scotland_Subsidiarity_2010.xml").toURI()));
        test(Paths.get(AknV2Test.class.getResource("/xml/Act_Kenya_1980-01-01@1989-12-15.xml").toURI()));
        test(Paths.get(AknV2Test.class.getResource("/xml/Act_Kenya_1989-12-06.xml").toURI()));
        test(Paths.get(AknV2Test.class.getResource("/xml/Act_Kenya_1997-08-22_3@2003-12-19.xml").toURI()));
        test(Paths.get(AknV2Test.class.getResource("/xml/Act_Kenya_1997-08-22_3_2003-12-19.xml").toURI()));
        test(Paths.get(AknV2Test.class.getResource("/xml/Act_Kenya_2003-12-10-short.xml").toURI()));
        test(Paths.get(AknV2Test.class.getResource("/xml/Act_Panama_decreto_223_2010.xml").toURI()));
        test(Paths.get(AknV2Test.class.getResource("/xml/Act_Salvador_Ley_604_1993.xml").toURI()));
        test(Paths.get(AknV2Test.class.getResource("/xml/Act_Salvador_Ley_912_2005.xml").toURI()));
        test(Paths.get(AknV2Test.class.getResource("/xml/Act_USA_2011-11-29-short.xml").toURI()));
    }

    @Test
    public void testAmendment() throws Exception {
        test(Paths.get(AknV2Test.class.getResource("/xml/Amendemet_Bungeni_2011-07-20.xml").toURI()));
        test(Paths.get(AknV2Test.class.getResource("/xml/Amendment_mexico_enmienda_2010-10-07.xml").toURI()));
        test(Paths.get(AknV2Test.class.getResource("/xml/Amendmet_Bungeni_2010-10-28adm1.xml").toURI()));
        test(Paths.get(AknV2Test.class.getResource("/xml/Amendmet_Bungeni_2010-10-28adm2.xml").toURI()));
        test(Paths.get(AknV2Test.class.getResource("/xml/Amendmet_Bungeni_2010-10-28adm3.xml").toURI()));
    }

    @Test
    public void testAmendmentList() throws Exception {
        test(Paths.get(AknV2Test.class.getResource("/xml/AmendmentList_Bungeni_2010-10-28v1.xml").toURI()));
        test(Paths.get(AknV2Test.class.getResource("/xml/AmendmentList_Bungeni_2010-10-28v2.xml").toURI()));
    }

    @Test
    public void testBill() throws Exception {

        test(Paths.get(AknV2Test.class.getResource("/xml/Bill_Bungeni_2009-01-27.xml").toURI()));
        test(Paths.get(AknV2Test.class.getResource("/xml/Bill_Bungeni_2009-01-27-documentCollection.xml").toURI()));

        test(Paths.get(AknV2Test.class.getResource("/xml/Bill_Bungeni_2010-07-10-mail.xml").toURI()));
        test(Paths.get(AknV2Test.class.getResource("/xml/Bill_Bungeni_2010-07-10-memorandum.xml").toURI()));
//   ----     test(Paths.get(AknV2Test.class.getResource("/xml/Bill_Bungeni_2010-10-10.xml").toURI()));
        test(Paths.get(AknV2Test.class.getResource("/xml/Bill_Bungeni_2010-10-10-memorandum.xml").toURI()));
        test(Paths.get(AknV2Test.class.getResource("/xml/Bill_Bungeni_2010-11-19-coverpage.xml").toURI()));
        test(Paths.get(AknV2Test.class.getResource("/xml/Bill_Bungeni_2011-06-30v1.xml").toURI()));
        test(Paths.get(AknV2Test.class.getResource("/xml/Bill_Bungeni_2011-06-30v1-documentCollection.xml").toURI()));

        test(Paths.get(AknV2Test.class.getResource("/xml/Bill_Bungeni_2011-06-30v2.xml").toURI()));
        test(Paths.get(AknV2Test.class.getResource("/xml/Bill_Bungeni_2011-06-30v2-documentCollection.xml").toURI()));
        test(Paths.get(AknV2Test.class.getResource("/xml/Bill_Bungeni_2011-12-19-collect.xml").toURI()));

        test(Paths.get(AknV2Test.class.getResource("/xml/Bill_cl_2005-03-14.xml").toURI()));
        test(Paths.get(AknV2Test.class.getResource("/xml/Bill_Kenya_2006-02-10.xml").toURI()));


        test(Paths.get(AknV2Test.class.getResource("/xml/Bill_Kenya_2006-12-19.xml").toURI()));
        test(Paths.get(AknV2Test.class.getResource("/xml/Bill_SouthAfrica_2010-10-10.xml").toURI()));
        test(Paths.get(AknV2Test.class.getResource("/xml/Bill_SouthAfrica_2010-10-10-memorandum.xml").toURI()));

        test(Paths.get(AknV2Test.class.getResource("/xml/Bill_SudAfrica_2003-09-04.xml").toURI()));
        test(Paths.get(AknV2Test.class.getResource("/xml/Bill_SudAfrica_2003-09-04@20051231.xml").toURI()));
        test(Paths.get(AknV2Test.class.getResource("/xml/Bill_UN_2012.xml").toURI()));
        test(Paths.get(AknV2Test.class.getResource("/xml/Bill_UN_2012_ES.xml").toURI()));
        test(Paths.get(AknV2Test.class.getResource("/xml/Bill_Uruguay_2011-09-27.xml").toURI()));
        test(Paths.get(AknV2Test.class.getResource("/xml/Bill_US_2010-12-06.xml").toURI()));
    }

    @Test
    public void testDebate() throws Exception {
        test(Paths.get(AknV2Test.class.getResource("/xml/Debate_Bungeni_1995-10-31.xml").toURI()));
        test(Paths.get(AknV2Test.class.getResource("/xml/Debate_Bungeni_2010-11-12.xml").toURI()));
        test(Paths.get(AknV2Test.class.getResource("/xml/Debate_Bungeni_2010-12-07.xml").toURI()));
        test(Paths.get(AknV2Test.class.getResource("/xml/Debate_Bungeni_2011-06-10.xml").toURI()));
        test(Paths.get(AknV2Test.class.getResource("/xml/Debate_Bungeni_2011-07-03.xml").toURI()));
        test(Paths.get(AknV2Test.class.getResource("/xml/Debate_mexico_debate_2010-01-26.xml").toURI()));
    }

    @Test
    public void testOfficialGazette() throws Exception {
        test(Paths.get(AknV2Test.class.getResource("/xml/officialGazette_Bungeni_2011-12-23v1.xml").toURI()));
        test(Paths.get(AknV2Test.class.getResource("/xml/officialGazette_Bungeni_2011-12-23v2.xml").toURI()));
    }

    @Test
    public void testReport() throws Exception {
        test(Paths.get(AknV2Test.class.getResource("/xml/Report_SudAfrica_2005-08-29.xml").toURI()));
        test(Paths.get(AknV2Test.class.getResource("/xml/Report_SudAfrica_2007-03-22.xml").toURI()));
        test(Paths.get(AknV2Test.class.getResource("/xml/Report_SudAfrica_2008-11-01.xml").toURI()));
        test(Paths.get(AknV2Test.class.getResource("/xml/Report_SudAfrica_APPENDIX2_Of_Report_2007-03-22.xml").toURI()));

    }

    @Test
    public void testOther() throws Exception {
        test(Paths.get(AknV2Test.class.getResource("/xml/constitution_final-Kenya.xml").toURI()));
        test(Paths.get(AknV2Test.class.getResource("/xml/OrderOfWeek_Bungeni_2008-03-19.xml").toURI()));
        test(Paths.get(AknV2Test.class.getResource("/xml/Questions_Bungeni_2010.xml").toURI()));
        test(Paths.get(AknV2Test.class.getResource("/xml/Table_APPENDIX1_2003.xml").toURI()));
        test(Paths.get(AknV2Test.class.getResource("/xml/Table_APPENDIX1_2007.xml").toURI()));
        test(Paths.get(AknV2Test.class.getResource("/xml/Table_Traffic_Ke_Schedule10_1954.xml").toURI()));
    }

    private void test(Path path) throws IOException {

        LOGGER.info("Read File [{}]", path);

        MappedByteBuffer out = null;
        try (FileChannel channel = FileChannel.open(path, StandardOpenOption.READ)) {

            // Mapping a file into memory
            out = channel.map(FileChannel.MapMode.READ_ONLY, 0, channel.size());

            AkomaNtoso<?> akomaNtoso = XML_READER_FACTORY.read(out);

            XmlChannelWriter writer = new XmlChannelWriterV2();
            writer.setChannel(FileChannel.open(Paths.get("c:/tmp/test-001.xml"), EnumSet.of(StandardOpenOption.CREATE, StandardOpenOption.TRUNCATE_EXISTING, StandardOpenOption.WRITE)));
            akomaNtoso.write(writer);
            writer.flush();

        } finally {
            if (out != null) {
                Buffers.releaseDirectByteBuffer(out);
            }
        }

        compare(Files.newInputStream(path), new FileInputStream("c:/tmp/test-001.xml"));
    }

    public void compare(InputStream controlXml, InputStream testXml) {

        Diff myDiff = DiffBuilder
                .compare(controlXml)
                .withTest(testXml)
                .withComparisonController(ComparisonControllers.StopWhenDifferent)
                .checkForIdentical()
                .ignoreComments()
                .ignoreWhitespace()
                .normalizeWhitespace()
                .build();

        Iterator<Difference> iter = myDiff.getDifferences().iterator();
        int size = 0;
        while (iter.hasNext()) {
            System.out.println(iter.next().toString());
            size++;
        }

        Assert.assertEquals(0, size);


    }


}
