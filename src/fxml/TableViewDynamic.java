package fxml;

import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import javafx.application.Application;
import javafx.beans.property.ReadOnlyObjectWrapper;
import javafx.collections.*;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Stage;

public class TableViewDynamic {

//	TableView tableView;
//
//	public TableViewDynamic(TableView t) {
//		tableView = t;
//	}
	

	public List<String[]> parseFileToArray() {
		String filePath = "counterdata.csv";
		List<String[]> list = new ArrayList();
		try (Stream<String> lines = Files.lines(Paths.get(filePath), Charset.defaultCharset())) {
//			= lines.map(t->Arrays.asList(t.split(";"))).collect(Collectors.toList());
			lines.forEach(t -> list.add(t.split(";")));
 
		} catch (Exception e) {
			System.out.println(e.toString()+" from "+this.getClass());
		}
 
		return list;
	}
	
	private ObservableList<ObservableList<String>> buildData2() {
		ObservableList<ObservableList<String>> data = FXCollections.observableArrayList();
		List<String[]> list = parseFileToArray();
		for (int i = 0; i < list.size(); i++) {
			data.add(FXCollections.observableArrayList(list.get(i)));
		}
		return data;
	}
	
	
	public TableView<ObservableList<String>> createTableView2() throws IOException {
		TableView<ObservableList<String>> tableView = new TableView<>();
		ObservableList<ObservableList<String>> data = buildData2();
		ObservableList<String> firstLine = data.remove(0);
		tableView.setItems(data);
		for (int i = 0; i < firstLine.size(); i++) {
			final int curCol = i;
			final TableColumn<ObservableList<String>, String> column = new TableColumn<>(
					  firstLine.get(i)
			);
			column.setCellValueFactory(
					  param -> new ReadOnlyObjectWrapper<>(param.getValue().get(curCol))
			);
			tableView.getColumns().add(column);
		}
		return tableView;
	}
	
	
	
	private final String[][] dataArray = {
		{"date", "prev", "current", "amount", "rate", "pay"},
		{"jeep", "camaro", "corvette", "asdf", "asdf", "asdf"},
		{"accord", "camry", "mustang", "asdf", "asdf", "adsf adsf"}
	};

	private ObservableList<ObservableList<String>> buildData(String[][] dataArray) {
		ObservableList<ObservableList<String>> data = FXCollections.observableArrayList();
//
//		for (String[] row : dataArray) {
//			data.add(FXCollections.observableArrayList(row));
//		}
		for (int i = 1; i < dataArray.length; i++) {
			data.add(FXCollections.observableArrayList(dataArray[i]));
		}
		return data;
	}

	public TableView<ObservableList<String>> createTableView() throws IOException {
		TableView<ObservableList<String>> tableView = new TableView<>();
		tableView.setItems(buildData(dataArray));
		for (int i = 0; i < dataArray[0].length; i++) {
			final int curCol = i;
			final TableColumn<ObservableList<String>, String> column = new TableColumn<>(
					  dataArray[0][i]
			);
			column.setCellValueFactory(
					  param -> new ReadOnlyObjectWrapper<>(param.getValue().get(curCol))
			);
			tableView.getColumns().add(column);
		}
		return tableView;
	}

}
