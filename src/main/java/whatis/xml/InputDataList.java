package whatis.xml;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class InputDataList implements Iterable<InputData> {

	private List<InputData> list = new ArrayList<InputData>();

	public void add(InputData item) {
		this.list.add(item);
	}

	@Override
	public Iterator<InputData> iterator() {
		return list.iterator();
	}

	public InputDataList getBySheet(String sheetName) {

		InputDataList ret = new InputDataList();
		for (InputData globalSet : this.list) {
			if (sheetName.equals(globalSet.u_sheet)) {
				ret.add(globalSet);
			}
		}
		return ret;
	}

	public InputDataList getBySheetCase(String sheetName, String caseName) {
		InputDataList filterSheetList = this.getBySheet(sheetName);
		InputDataList ret = new InputDataList();
		for (InputData globalSet : filterSheetList) {
			if (caseName.equals(globalSet.u_case)) {
				ret.add(globalSet);
			}
		}
		return ret;
	}

}
