package com.store.util;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class Reader {

	private static InputStream fileInputStream;
	private static Properties prop;

	public static Properties getAllProperties() {

		prop = new Properties();

		try {
			String propFilePath = System.getProperty("user.dir") + "/src/test/resources/config/config.properties";
			fileInputStream = new FileInputStream(propFilePath);
			prop.load(fileInputStream);

		} catch (IOException ex) {
			ex.printStackTrace();
		}
		return prop;
	}

	public static void cleanUp() {
		if (fileInputStream != null) {
			try {
				fileInputStream.close();
			} catch (IOException ex) {
				ex.printStackTrace();
			}
		}
	}
}
