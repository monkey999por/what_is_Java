package whatis.xml;

import java.io.File;
import java.io.FileInputStream;
import java.util.Objects;

import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.TransformerFactoryConfigurationError;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

/**
 * glovalvers.xmlをNodeとして操作する。
 * @author nogam
 */
public class Globalvars {
	final private Document xml;

	/**
	 * @param path path
	 * @throws Exception Exception
	 */
	public Globalvars(String path) throws Exception {
		this.xml = DocumentBuilderFactory.newInstance()
				.newDocumentBuilder()
				.parse(new FileInputStream(path));
	}

	/**
	 * @param id id
	 * @return idで指定したDatasetを返却
	 */
	private Element getDataset(String id) {
		return this.searchNodeByAttr(this.xml.getDocumentElement().getChildNodes(), "id", id);
	}

	/**
	 * @param datasetId datasetId
	 * @return datasetIdで指定したDataset Nodeが配下にもつRowsを返却
	 */
	private Element getRows(String datasetId) {
		// こっちの方が単純だけどglobalvarsの構造に依存するのであんま良くない
		//return (Element) this.getDataset(datasetId).getElementsByTagName("Rows").item(0);
		return this.searchNodeByTagName(this.getDataset(datasetId).getChildNodes(), "Rows");
	}

	/**
	 * @param datasetId datasetId
	 * @param rowIndex rowIndex
	 * @return datasetIdで指定したデータセットから、rowIndexで指定したrow Nodeを返却
	 */
	private Element getTargetRow(String datasetId, int rowIndex) {
		NodeList rowList  = this.getRows(datasetId).getChildNodes();
		int currentRow = 0;
		for (int i = 0; i < rowList.getLength(); i++) {
			if (rowList.item(i).getNodeType() == Node.ELEMENT_NODE) {
				if (rowIndex == currentRow) {
					return (Element) rowList.item(i);
				}
				currentRow++;
			}
		}
		return null;
	}

	/**
	 * @param  datasetId datasetId
	 * @param columnId columnId
	 * @param rowIndex rowIndex
	 * @return datasetIdで指定したデータセット、columnIdで指定したカラムから、rowIndexで指定したCol Nodeを返却
	 */
	private Element getCol(String datasetId, String columnId, int rowIndex) {
		return this.searchNodeByAttr(Objects.requireNonNull(this.getTargetRow(datasetId, rowIndex)).getChildNodes(), "id", columnId);
	}

	/**
	 * @param nodeList nodeList
	 * @param tagName tagName
	 * @return nodeListから対象のタグ名を探して最初に見つかったElementを返却
	 */
	private Element searchNodeByTagName(NodeList nodeList, String tagName) {
		for (int i = 0; i < nodeList.getLength(); i++) {
			Node node = nodeList.item(i);
			if (node.getNodeType() == Node.ELEMENT_NODE &&
					tagName.equals(node.getNodeName())) {
				return (Element) node;
			}
		}
		return null;
	}

	/**
	 * @param nodeList nodeList
	 * @param attrName attrName
	 * @param attrValue attrValue
	 * @return nodeListから対象の属性と属性値を探して最初に見つかったElementを返却
	 */
	private Element searchNodeByAttr(NodeList nodeList, String attrName, String attrValue) {
		for (int i = 0; i < nodeList.getLength(); i++) {
			Node node = nodeList.item(i);
			if (node.getNodeType() == Node.ELEMENT_NODE) {
				if (node.hasAttributes() && node.getAttributes().getNamedItem(attrName)
						.getTextContent().equals(attrValue)) {
					return (Element) node;
				}
			}
		}
		return null;
	}

	/**
	 * @param datasetId datasetId
	 * @param columnId columnId
	 * @param rowIndex rowIndex
	 * @param value value
	 * @throws Exception Exception
	 */
	public void setValue(String datasetId, String columnId, int rowIndex, String value) throws Exception {
		Element target = this.getCol(datasetId, columnId, rowIndex);
		target.setTextContent(value);
	}

	// 行を削除する場合とか　行を追加する場合も作る
	/**
	 * Rowの最後に新しいRowを追加
	 * @param datasetId datasetId
	 * @param columnId columnId
	 */
	public void addRow(String datasetId, String columnId) {
		//this.getRows(datasetId).
	}

	/**
	 * @param outPath outPath
	 * @throws TransformerFactoryConfigurationError
	 * @throws TransformerException
	 */
	public void save(String outPath) throws TransformerFactoryConfigurationError, TransformerException {
		Transformer transformer = TransformerFactory.newInstance().newTransformer();
		transformer.setOutputProperty("indent", "no");
		transformer.setOutputProperty("encoding", "UTF-8");
		transformer.transform(new DOMSource(this.xml),
				new StreamResult(new File(outPath)));
	}
}
