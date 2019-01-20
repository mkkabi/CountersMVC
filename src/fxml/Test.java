package fxml;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Test {

	public static void main(String[] args) {
		Test test = new Test();
		
		test.writeTextToFile("some text to write to file", "testFileName.txt");
		test.readFile("testFileName.txt");
	}
	
	public static String readFile(String uri){
		try(BufferedReader br = new BufferedReader(new FileReader(new File(uri)))){
			return br.readLine();
		} catch (FileNotFoundException ex) {
			System.out.println(ex.toString());
		} catch (IOException ex) {
			System.out.println(ex.toString());
		}
		return null;
	}
	
	public static void writeTextToFile(String text, String uri){
		Path path = Paths.get(uri);
		try {
			Files.createFile(path);
		} catch (IOException ex) {
			System.out.println(ex.toString());
		}
		try(BufferedWriter bw = new BufferedWriter(new FileWriter(new File("uri")))){
			bw.write(text);
			bw.newLine();
			bw.flush();
			
		} catch (IOException ex) {
			System.out.println(ex.toString()+" from application.NIO");
		}
	}
	
	public void someFoo(){
				String filePath = "counterdata.csv";

		try (Stream<String> lines = Files.lines(Paths.get(filePath), Charset.defaultCharset())) {
			ArrayList<ArrayList<String>> list = new ArrayList();
			//lines.flatMap(line -> Arrays.stream(line.split(";"))).forEach(System.out::println);
			ArrayList<String> list2 = new ArrayList();
//			list2 = lines.collect(Collectors.toList()).stream().map(s->Arrays.asList(s.split(";"))).collect(Collectors.toList());
//			list2 = lines.collect(Collectors.toCollection(ArrayList::new));
//			System.out.println(lines.flatMap(s -> Arrays.stream(s.split(";"))).collect(Collectors.toList()));
			ArrayList<String> l1 = lines.collect(Collectors.toCollection(ArrayList::new));
			l1.stream().map(t -> Arrays.asList(t.split(";"))).collect(Collectors.toList()).getClass();
			
			l1.stream().map(t -> Arrays.asList(t.split(";")));
			
			String s = "asdf;dsgfsdfg;qwerqewr";
			ArrayList<String> test = new ArrayList();
			test.addAll(Arrays.asList(s.split(";")));
			System.out.println();

			System.out.println(test.getClass());

//			list = l1.stream().map(t->Arrays.asList(t.split(";"))).collect(Collectors.toList());
			//		Stream<String> lines2 = Files.lines(Paths.get("counterdata.csv"), Charset.defaultCharset());
			//		lines.forEach(t->System.out.println(t));
		} catch (Exception e) {
			System.out.println(e.toString());
		}

		System.out.println("test main");
	}

}
