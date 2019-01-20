package application;

import java.io.BufferedReader;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.attribute.FileAttribute;
import java.util.Arrays;
import java.util.stream.Stream;

public class NIO {

	public void readFile(String location) throws IOException {
		Path path = Paths.get(location);
//		Files.createFile(path);
		File adsf = new File("resource");
	}

	private void fileToStream(String filePath) {
		try (Stream<String> lines = Files.lines(Paths.get(filePath),
				Charset.defaultCharset())) {

			lines.flatMap(line -> Arrays.stream(line.split(";")))
					.distinct().forEach(System.out::println);
		} catch (Exception e) {}
	}

	public String fileToString(String filePath) {
		BufferedReader reader;
		String line;
		StringBuilder result = new StringBuilder();
		try {
			reader = new BufferedReader(new FileReader(new File(filePath)));
			while ((line = reader.readLine()) != null) {
				result.append(line + "\n");
			}
		} catch (FileNotFoundException ex) {
			//Logger.getLogger(ReadFile.class.getName()).log(Level.SEVERE, null, ex);
			System.out.println("File was not found");
		} catch (IOException e) {
			System.out.println("IO Exception happened");
		}
		return result.toString();
	}

	private byte[] fileToByte(String fileName) {
		ByteArrayOutputStream bos = null;
		try {
			File f = new File(fileName);
			FileInputStream fis = new FileInputStream(f);
			byte[] buffer = new byte[1024];
			bos = new ByteArrayOutputStream();
			for (int len; (len = fis.read(buffer)) != -1;) {
				bos.write(buffer, 0, len);
			}
		} catch (FileNotFoundException e) {
			System.err.println(e.getMessage());
		} catch (IOException e2) {
			System.err.println(e2.getMessage());
		}
		return bos != null ? bos.toByteArray() : null;
	}

}
