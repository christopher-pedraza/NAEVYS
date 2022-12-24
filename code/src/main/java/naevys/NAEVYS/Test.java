package naevys.NAEVYS;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

public class Test {

	public static void main(String[] args) {
		Properties prop = new Properties();
		String fileName = "app.config";
		try (FileInputStream fis = new FileInputStream(fileName)) {
		    prop.load(fis);
		} catch (FileNotFoundException ex) {
		} catch (IOException ex) {
		}
		System.out.println(prop.getProperty("app2.name"));
		System.out.println(prop.getProperty("app.version"));

	}

}
