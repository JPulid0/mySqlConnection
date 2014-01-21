package com.jpulido.util;

import java.net.URL;
import java.util.Properties;

public class ResourceLocator {
	public static URL find(String path) {
		URL url = null;

		System.out.println("Find resource through Context Classloader");
		ClassLoader contextClassLoader = Thread.currentThread()
				.getContextClassLoader();
		if (contextClassLoader != null) {
			url = contextClassLoader.getResource(path);
		}
		if (url != null) {
			return url;
		}
		System.out.println("Find resource through Class Classloader");
		url = ResourceLocator.class.getClassLoader().getResource(path);
		if (url != null) {
			return url;
		}
		System.out.println("Find resource through System Classloader");
		url = ClassLoader.getSystemClassLoader().getResource(path);
		if (url != null) {
			return url;
		}
		return null;
	}

	public static Properties findAsProperties(String path) {
		Properties props;
		URL url;
		
		props = null;
		url = find(path);
		if (url != null) {
			try {
				props = new Properties();
				props.load(url.openStream());
			} catch (Exception ex) {
				ex.printStackTrace();
			}
		}
		
		return props;
	}
}
