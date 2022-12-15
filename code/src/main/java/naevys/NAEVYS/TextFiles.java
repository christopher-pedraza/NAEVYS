package naevys.NAEVYS;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class TextFiles {
	public Header[] readFile(String fileName) {
		try (BufferedReader br = new BufferedReader(new FileReader(fileName))) {
			String line = br.readLine();

			// Mientras que la linea leida no sea nula (ultima linea del archivo de texto),
			// se iterara
			while (line != null) {
				line = line.replaceAll("\\s+", "");

				// Si la linea tiene una longitud mayor a 0 (no esta vacia)
				if (line.length() > 0) {
					// Si la linea no comienza con un
					if (line.charAt(0) != Constants.TF.COMMENT_SIGN) {
						String[] lineData = line.split(",");
						processLine(lineData);
					}
				}
				// Leer la siguiente linea
				line = br.readLine();
			}

		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return null;
	}

	private Header processLine(String[] lineData) {
		if (lineData.length == Constants.TF.INPUT_LINE_SIZE) {
			String colName = lineData[0];
			int colIndex = Integer.parseInt(lineData[1]);
			return new Header(colName, colIndex);
		} else if (lineData.length == Constants.TF.OUTPUT_LINE_SIZE) {
			String colName = lineData[0];
			int colIndex = Integer.parseInt(lineData[1]) - 1;
			char id = lineData[2].charAt(0);
			String value = lineData[3];
			return new Header(colName, colIndex, id, value);
		}

		return null;
	}
}
