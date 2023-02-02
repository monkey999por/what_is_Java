package whatis.xml;


import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

public class InputDataSAXHandler extends DefaultHandler {
    String u_sheet;
    String u_case;
    String u_dataset;
    String u_column;
    String u_data;
    int u_row;

    String targetTag;
    public InputDataList inputDataList = new InputDataList();

    @Override
    public void startElement(String uri, String localName, String qName, Attributes attributes) throws SAXException {
        // case sheetを登録
//		this.u_sheet = qName.equals("sheet") ? attributes.getValue(Setting.get("sheet_tag_attr_name")) : this.u_sheet;
//		this.u_case = qName.equals("case") ? attributes.getValue(Setting.get("case_tag_attr_name")) : this.u_case;
//
//		// ターゲットを検知  characters()の前準備
//		this.targetTag = qName;
        System.out.printf("url: %s", "".equals(uri) ? "blank" : uri);
        System.out.println();
        System.out.printf("localName: %s", "".equals(localName) ? "blank" : localName);
        System.out.println();
        System.out.printf("qName: %s", "".equals(qName) ? "blank" : qName);
        System.out.println();
        for (int i = 0; i < attributes.getLength(); i++) {
            System.out.printf("attributes.value: %s", attributes.getValue(i));
            System.out.println();
            System.out.printf("attributes.QName: %s", attributes.getQName(i));
            System.out.println();
        }
        System.out.println();
    }

    @Override
    public void characters(char[] ch, int start, int length) throws SAXException {

//		if (this.targetTag.equals("Dataset")) {
//			this.u_dataset = new String(ch, start, length);
//		}
//		if (this.targetTag.equals("Column")) {
//			this.u_column = new String(ch, start, length);
//		}
//		if (this.targetTag.equals("Data")) {
//			this.u_data = new String(ch, start, length);
//		}
//		if (this.targetTag.equals("Row")) {
//			this.u_row = Integer.parseInt(new String(ch, start, length));
//		}
    }

    @Override
    public void endElement(String uri, String localName, String qName) throws SAXException {

        this.targetTag = "";
        //  varsの終了タグごとに１オブジェクト作成
//		if (qName.equals("vars")) {
//			this.inputDataList.add(
//					new InputData(this.u_sheet, this.u_case, this.u_dataset, this.u_column, this.u_data, this.u_row));
//
//			// 値のクリア
//			this.u_dataset = "";
//			this.u_column = "";
//			this.u_data = "";
//			this.u_row = 0;
//		}
    }
}
