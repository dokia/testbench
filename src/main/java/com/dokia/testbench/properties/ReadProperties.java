package com.dokia.testbench.properties;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class ReadProperties {

	private String name;
	private String gender;
	private String age;
	private String city;
	private String mail;
	private String github;
	
	public ReadProperties() {
		init();
	}
	
	private void init() {
		Properties prop = new Properties();
		InputStream in = getClass().getResourceAsStream("/properties/test.properties");
		try {
			prop.load(in);
			readConfig(prop);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	private void readConfig(Properties prop) {
		name = prop.getProperty("name", "Unknown");
		gender = prop.getProperty("gender", "male");
		age = prop.getProperty("age", "0");
		city = prop.getProperty("city", "Unknown");
		mail = prop.getProperty("mail", "Unknown");
		github = prop.getProperty("github", "Unknown");
	}
	
	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		sb.append("name: " + name + "\n");
		sb.append("gender: " + gender + "\n");
		sb.append("age: " + age + "\n");
		sb.append("city: " + city + "\n");
		sb.append("mail: " + mail + "\n");
		sb.append("github: " + github);
		
		return sb.toString();
	}
	
	public static void main(String[] args) {
		ReadProperties p = new ReadProperties();
		System.out.println(p);
	}

}
