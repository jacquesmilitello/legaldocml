package io.legaldocml.module.akn.v2;

import io.legaldocml.akn.AkomaNtoso;
import io.legaldocml.io.XmlReaderFactory;
import io.legaldocml.io.XmlReaderFactoryProvider;
import io.legaldocml.io.impl.Buffers;
import io.legaldocml.io.impl.XmlChannelWriter;
import io.legaldocml.test.PathForTest;
import org.junit.Test;

import java.io.FileInputStream;
import java.io.IOException;
import java.nio.MappedByteBuffer;
import java.nio.channels.FileChannel;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.EnumSet;

import static io.legaldocml.XmlUnitHelper.compare;
import static java.nio.file.Files.newInputStream;

public class AknV2Test {

    private static final XmlReaderFactory XML_READER_FACTORY = XmlReaderFactoryProvider.newXmlReaderFactory(2);

    @Test
    public void testAct() throws Exception {
        test("/xml/v2/Act_constitution_final-Kenya.xml");
        test("/xml/v2/Act_Instrument_Scotland_Subsidiarity_2010.xml");
        test("/xml/v2/Act_Kenya_1980-01-01@1989-12-15.xml");
        test("/xml/v2/Act_Kenya_1989-12-06.xml");
        test("/xml/v2/Act_Kenya_1997-08-22_3@2003-12-19.xml");
        test("/xml/v2/Act_Kenya_1997-08-22_3_2003-12-19.xml");
        test("/xml/v2/Act_Kenya_2003-12-10-short.xml");
        test("/xml/v2/Act_Panama_decreto_223_2010.xml");
        test("/xml/v2/Act_Salvador_Ley_604_1993.xml");
        test("/xml/v2/Act_Salvador_Ley_912_2005.xml");
        test("/xml/v2/Act_USA_2011-11-29-short.xml");
    }

    @Test
    public void testAmendment() throws Exception {
        test("/xml/v2/Amendemet_Bungeni_2011-07-20.xml");
        test("/xml/v2/Amendment_mexico_enmienda_2010-10-07.xml");
        test("/xml/v2/Amendmet_Bungeni_2010-10-28adm1.xml");
        test("/xml/v2/Amendmet_Bungeni_2010-10-28adm2.xml");
        test("/xml/v2/Amendmet_Bungeni_2010-10-28adm3.xml");
    }

    @Test
    public void testAmendmentList() throws Exception {
        test("/xml/v2/AmendmentList_Bungeni_2010-10-28v1.xml");
        test("/xml/v2/AmendmentList_Bungeni_2010-10-28v2.xml");
    }

    @Test
    public void testBill() throws Exception {

        test("/xml/v2/Bill_Bungeni_2009-01-27.xml");
        test("/xml/v2/Bill_Bungeni_2009-01-27-documentCollection.xml");

        test("/xml/v2/Bill_Bungeni_2010-07-10-mail.xml");
        test("/xml/v2/Bill_Bungeni_2010-07-10-memorandum.xml");
//   ----     test("/xml/v2/Bill_Bungeni_2010-10-10.xml");
        test("/xml/v2/Bill_Bungeni_2010-10-10-memorandum.xml");
        test("/xml/v2/Bill_Bungeni_2010-11-19-coverpage.xml");
        test("/xml/v2/Bill_Bungeni_2011-06-30v1.xml");
        test("/xml/v2/Bill_Bungeni_2011-06-30v1-documentCollection.xml");

        test("/xml/v2/Bill_Bungeni_2011-06-30v2.xml");
        test("/xml/v2/Bill_Bungeni_2011-06-30v2-documentCollection.xml");
        test("/xml/v2/Bill_Bungeni_2011-12-19-collect.xml");

        test("/xml/v2/Bill_cl_2005-03-14.xml");
        test("/xml/v2/Bill_Kenya_2006-02-10.xml");


        test("/xml/v2/Bill_Kenya_2006-12-19.xml");
        test("/xml/v2/Bill_SouthAfrica_2010-10-10.xml");
        test("/xml/v2/Bill_SouthAfrica_2010-10-10-memorandum.xml");

        test("/xml/v2/Bill_SudAfrica_2003-09-04.xml");
        test("/xml/v2/Bill_SudAfrica_2003-09-04@20051231.xml");
        test("/xml/v2/Bill_UN_2012.xml");
        test("/xml/v2/Bill_UN_2012_ES.xml");
        test("/xml/v2/Bill_Uruguay_2011-09-27.xml");
        test("/xml/v2/Bill_US_2010-12-06.xml");
    }

    @Test
    public void testDebate() throws Exception {
        test("/xml/v2/Debate_Bungeni_1995-10-31.xml");
        test("/xml/v2/Debate_Bungeni_2010-11-12.xml");
        test("/xml/v2/Debate_Bungeni_2010-12-07.xml");
        test("/xml/v2/Debate_Bungeni_2011-06-10.xml");
        test("/xml/v2/Debate_Bungeni_2011-07-03.xml");
        test("/xml/v2/Debate_mexico_debate_2010-01-26.xml");
    }

    @Test
    public void testOfficialGazette() throws Exception {
        test("/xml/v2/officialGazette_Bungeni_2011-12-23v1.xml");
        test("/xml/v2/officialGazette_Bungeni_2011-12-23v2.xml");
    }

    @Test
    public void testReport() throws Exception {
        test("/xml/v2/Report_SudAfrica_2005-08-29.xml");
        test("/xml/v2/Report_SudAfrica_2007-03-22.xml");
        test("/xml/v2/Report_SudAfrica_2008-11-01.xml");
        test("/xml/v2/Report_SudAfrica_APPENDIX2_Of_Report_2007-03-22.xml");

    }

    @Test
    public void testOther() throws Exception {
        test("/xml/v2/constitution_final-Kenya.xml");
        test("/xml/v2/OrderOfWeek_Bungeni_2008-03-19.xml");
        test("/xml/v2/Questions_Bungeni_2010.xml");
        test("/xml/v2/Table_APPENDIX1_2003.xml");
        test("/xml/v2/Table_APPENDIX1_2007.xml");
        test("/xml/v2/Table_Traffic_Ke_Schedule10_1954.xml");
    }

    private void test(String resource) throws IOException {

        Path path = PathForTest.path(resource);

        MappedByteBuffer out = null;
        try (FileChannel channel = FileChannel.open(path, StandardOpenOption.READ)) {

            // Mapping a file into memory
            out = channel.map(FileChannel.MapMode.READ_ONLY, 0, channel.size());

            AkomaNtoso<?> akomaNtoso = XML_READER_FACTORY.read(out);

            XmlChannelWriter writer = new XmlChannelWriterV2();
            writer.setChannel(FileChannel.open(Paths.get(System.getProperty("java.io.tmpdir"), "aknv2-test-001.xml"), EnumSet.of(StandardOpenOption.CREATE, StandardOpenOption.TRUNCATE_EXISTING, StandardOpenOption.WRITE)));
            akomaNtoso.write(writer);
            writer.flush();

        } finally {
            if (out != null) {
                Buffers.releaseDirectByteBuffer(out);
            }
        }

        compare(newInputStream(path), new FileInputStream(Paths.get(System.getProperty("java.io.tmpdir"), "aknv2-test-001.xml").toFile()));
    }


}
