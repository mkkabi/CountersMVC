package fxml;

import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Test {

	public static void main(String[] args) {
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
