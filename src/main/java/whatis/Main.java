package whatis;

import whatis.xml.XmlDriver;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.net.URISyntaxException;
import java.util.Properties;

public class Main {

	public static void main(String[] args) throws IOException, URISyntaxException {
//		FileLogic.fileOperation();
		XmlDriver.run();
	}
}