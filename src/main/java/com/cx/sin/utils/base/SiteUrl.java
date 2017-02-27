package com.cx.sin.utils.base;

import java.io.IOException;
import java.util.Properties;

public class SiteUrl {
	private static Properties uploadProperties = new Properties();
	private static Properties sMsgProperties = new Properties();
	static{
		try {
			uploadProperties.load(SiteUrl.class.getClassLoader().getResourceAsStream("upload.properties"));
			sMsgProperties.load(SiteUrl.class.getClassLoader().getResourceAsStream("sMsg.properties"));
		}
		catch (IOException e) {
			e.printStackTrace();
		}
	}

	public static String readUrl(String key) {
		return (String)uploadProperties.get(key);
	}
	
	public static String readSMsg(String key) {
		return (String)sMsgProperties.get(key);
	}
	
}
