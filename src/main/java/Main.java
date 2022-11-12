package javaTest;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.Properties;

public class Main {

	public static void main(String[] args) {
		System.out.println("Start");
		createFile();
		System.out.print("End");

	}

	static void createFile() {
		//Windowsの環境変数 USERPROFILEを取得
		String USERPROFILE = System.getenv("UserProfile");
		
		//既存ファイルを削除して空のファイルを再作成する.
		//ディレクトリがなければ作る
		File file = new java.io.File(USERPROFILE + "\\Downloads\\out_temp\\test.properties");
		file.mkdirs();
		if (file.exists())
			file.delete();
		
		try {
			file.createNewFile();
		} catch (IOException e) {
			e.printStackTrace();
		}

		//プロパティを作成して書き込む。
		try (OutputStream out = new FileOutputStream(file)) {
			Properties prop = new Properties();
			prop.setProperty("tanaka", "taro");
			prop.setProperty("yamada", "hanako");
			prop.store(out, null);			
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	}

}