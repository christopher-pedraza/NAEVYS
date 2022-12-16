package naevys.NAEVYS;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class TextFiles {
	public Header[] readFile(String fileName) {
		int fileSize = getFileSize(fileName);
		int currentLine = 0;

		try (BufferedReader br = new BufferedReader(new FileReader(fileName))) {
			Header[] headers = new Header[fileSize];

			// Mientras que la linea leida no sea nula (ultima linea del archivo de texto),
			// se iterara
			String line = br.readLine();
			while (line != null) {
				line = line.replaceAll("\\s+", "");

				// Si la linea tiene una longitud mayor a 0 (no esta vacia)
				if (line.length() > 0) {
					// Si la linea no comienza con un signo de comentario
					if (line.charAt(0) != Constants.TF.COMMENT_SIGN) {
						String[] lineData = line.split(",");
						headers[currentLine] = processLine(lineData);
						currentLine++;
					}
				}
				// Leer la siguiente linea
				line = br.readLine();
			}
			br.close();

			return headers;
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return null;
	}

	private int getFileSize(String fileName) {
		int fileSize = 0;
		try (BufferedReader br = new BufferedReader(new FileReader(fileName))) {
			// Determinar el tamaño del archivo
			// Mientras que la linea leida no sea nula (ultima linea del archivo de texto),
			// se iterara
			String line = br.readLine();
			while (line != null) {
				// Si la linea tiene una longitud mayor a 0 (no esta vacia)
				if (line.length() > 0) {
					// Si la linea no comienza con un signo de comentario
					if (line.charAt(0) != Constants.TF.COMMENT_SIGN) {
						fileSize++;
					}
				}
				// Leer la siguiente linea
				line = br.readLine();
			}
			br.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return fileSize;
	}

	private Header processLine(String[] lineData) {
		if (lineData.length == Constants.TF.NORMAL_LINE_SIZE) {
			String colName = lineData[0];
			int colIndex = Integer.parseInt(lineData[1]);
			char id = lineData[2].charAt(0);
			String value = lineData[3];
			return new Header(colName, colIndex, id, value);
		} else if (lineData.length == Constants.TF.REFERENCE_LINE_SIZE) {
			String colName = lineData[0];
			int colIndex = Integer.parseInt(lineData[1]);
			char id = lineData[2].charAt(0);
			String value = lineData[3];
			int valueIndex = Integer.parseInt(lineData[4]);
			return new Header(colName, colIndex, id, value);
		}

		return null;
	}
}
