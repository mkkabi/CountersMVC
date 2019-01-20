package fxml;

import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
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
	private final String[][] dataArray = {
		{"date", "prev", "current", "amount", "rate", "pay"},
		{"jeep", "camaro", "corvette", "asdf", "asdf", "asdf"},
		{"accord", "camry", "mustang", "asdf", "asdf", "adsf adsf"}
	};

//    @Override
//    public void start(Stage stage) throws Exception {
//        TableView<ObservableList<String>> tableView = createTableView(dataArray);
//        stage.setScene(new Scene(tableView));
//        stage.show();
//    }
	private ObservableList<ObservableList<String>> buildData(String[][] dataArray) {
		ObservableList<ObservableList<String>> data = FXCollections.observableArrayList();

		for (String[] row : dataArray) {
			data.add(FXCollections.observableArrayList(row));
		}

		return data;
	}

	public TableView<ObservableList<String>> createTableView() throws IOException {

		ArrayList<ArrayList<String>> list = new ArrayList();
		Stream<String> lines = Files.lines(Paths.get("counterdata.csv"), Charset.defaultCharset());
		lines.forEach(t->System.out.println(t));
		
		

		TableView<ObservableList<String>> tableView = new TableView<>();
		tableView.setItems(buildData(dataArray));

		for (int i = 0; i < dataArray[0].length; i++) {
			final int curCol = i;
			final TableColumn<ObservableList<String>, String> column = new TableColumn<>(
					"Col " + (curCol + 1)
			);
			column.setCellValueFactory(
					param -> new ReadOnlyObjectWrapper<>(param.getValue().get(curCol))
			);
			tableView.getColumns().add(column);
		}
		return tableView;
	}

}
