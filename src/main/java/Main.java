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
		//Windows�̊��ϐ� USERPROFILE���擾
		String USERPROFILE = System.getenv("UserProfile");
		
		//�����t�@�C�����폜���ċ�̃t�@�C�����č쐬����.
		//�f�B���N�g�����Ȃ���΍��
		File file = new java.io.File(USERPROFILE + "\\Downloads\\out_temp\\test.properties");
		file.mkdirs();
		if (file.exists())
			file.delete();
		
		try {
			file.createNewFile();
		} catch (IOException e) {
			e.printStackTrace();
		}

		//�v���p�e�B���쐬���ď������ށB
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