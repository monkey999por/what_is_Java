package whatis.xml;

import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.net.URISyntaxException;
import java.net.URL;

public class XmlDriver {
    public static void run() throws URISyntaxException {

        URL url = XmlDriver.class.getResource("/xml/globalvars.xml");
        File file = new File(url.toURI());
        String absolutePath = file.getAbsolutePath();
        System.out.println(absolutePath);

        try {
            System.out.println("s3c xml start");
            // XMLを読み込んで操作


            var test = new Globalvars(absolutePath);
            test.addRow("dataset_A", "col_A1");
            test.setValue("dataset_A", "col_A1", 1, "test calue ddd");
            String userProfile = System.getenv("userprofile");
            System.out.println(userProfile);
            test.save(userProfile + "/Downloads/globalvars.xml");

        } catch (Exception e) {
            e.printStackTrace();
        }

        // SAXで操作　各要素の読み込みごとにイベントが発火するスタイル
        // https://atmarkit.itmedia.co.jp/ait/articles/0105/18/news003_3.html
        System.out.println("SAX start");
        try {
            SAXParserFactory factory = SAXParserFactory.newInstance();
            SAXParser parser = factory.newSAXParser();
            DefaultHandler handler = new InputDataSAXHandler();
            parser.parse(new File(absolutePath), handler);

        } catch (SAXException e) {
        } catch (ParserConfigurationException | IOException e) {
        }

    }
}