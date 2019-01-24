package fxml;

import java.io.FileReader;
import java.io.IOException;
import java.io.Reader;
import java.io.StringReader;
import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVRecord;

public class CSVReader {

	public static void main(String[] args) throws IOException {
		//		Iterable<CSVRecord> records = CSVFormat.EXCEL.parse(in);
		//		Iterable<CSVRecord> records = CSVFormat.RFC4180.withHeader("date", "prev", "current", "amount", "rate", "pay").parse(in);   //Header defined manually
		/*
		 Reader in = new FileReader("file.csv");
		 Iterable<CSVRecord> records = CSVFormat.RFC4180.withFirstRecordAsHeader().parse(in); // header auto defined
		 for (CSVRecord record : records) {
		 String pay = record.get("current");
		 System.out.println(pay);
		 }
		 */
		Reader in = new FileReader("file.csv");
		// Headers defined with ENUM
		Iterable<CSVRecord> records = CSVFormat.RFC4180.withHeader(Headers.class).parse(in);

		for (CSVRecord record : records) {
			String date = record.get(Headers.DATE);
			String prev = record.get(Headers.PREV);
			String current = record.get(Headers.CURRENT);
			System.out.println(date);
		}
	}
}

enum Headers {

	DATE("date"),
	PREV("prev"),
	CURRENT("current"),
	AMOUNT("amount"),
	RATE("rate"),
	PAY("pay");

	private final String name;

	Headers(String name) {
		this.name = name;
	}

}
