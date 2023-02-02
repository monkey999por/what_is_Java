package whatis.xml;

/**
 * inputのxmlの構造を平たくして保持するもの
 * {@link InputDataSAXHandler}によって設定され{@link InputDataList}に全データが保存される
 *
 */
public class InputData {
	public String u_sheet;
	public String u_case;
	public String u_dataset;
	public String u_column;
	public String u_data;
	public int u_row;

	public InputData(String u_sheet, String u_case, String u_dataset, String u_column, String u_data, int u_row) {
		this.u_sheet = u_sheet;
		this.u_case = u_case;
		this.u_dataset = u_dataset;
		this.u_column = u_column;
		this.u_data = u_data;
		this.u_row = u_row;
	}

	@Override
	public String toString() {
		return "{\r\n  u_sheet : " + u_sheet + "\r\n" +
				"  u_case : " + u_case + "\r\n" +
				"  u_dataset : " + u_dataset + "\r\n" +
				"  u_column : " + u_column + "\r\n" +
				"  u_data : " + u_data + "\r\n" +
				"  u_row : " + u_row + "\r\n}";
	}
}
