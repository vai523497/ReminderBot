/**
 * 
 */
package com.springBootExample.component;

import java.io.FileInputStream;
import java.io.InputStream;
import java.util.Properties;

import org.springframework.stereotype.Component;

/**
 * @author VAIBHAV-PC
 *
 */
@Component
public class FBCHATPropeties {
	public static InputStream inputStream;
	public static Properties fbConfigs;
	static {
		try {
			fbConfigs = new Properties();
			inputStream = new FileInputStream("D:\\project_own\\ReminderBot\\springBootExample\\FBChat.properties");

			fbConfigs.load(inputStream);

		} catch (Exception e) {
			System.out.print("Exception while reading properties" + e);
		}
	}

	public static String getProperty(String key) {
		return FBCHATPropeties.fbConfigs.getProperty(key);
	}
	
	

}
