package fxml;

import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.function.Consumer;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import javafx.beans.InvalidationListener;
import javafx.beans.property.ReadOnlyObjectProperty;
import javafx.beans.property.ReadOnlyObjectWrapper;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.*;
import javafx.scene.control.*;
import static javafx.scene.control.TableColumn.DEFAULT_CELL_FACTORY;
import javafx.util.Callback;

public class TableViewDynamic {

	TableView<ObservableList<String>> tableView;

	public TableViewDynamic(TableView<ObservableList<String>> tableView) {
		this.tableView = tableView;
	}

	public TableView<ObservableList<String>> getTableView() {
		return this.tableView;
	}

	private ObservableList<ObservableList<String>> buildData(String filePath) {
		ObservableList<ObservableList<String>> data = FXCollections.observableArrayList();
		try (Stream<String> lines = Files.lines(Paths.get(filePath), Charset.defaultCharset())) {
			lines.map(t -> t.split(";")).forEach(t -> data.add(FXCollections.observableArrayList(t)));
		} catch (Exception e) {
			System.out.println(e.toString() + " from " + this.getClass());
		}
		return data;
	}

	public void createTableView(String filePath) {
		tableView.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);
		ObservableList<ObservableList<String>> data = buildData(filePath);
		ObservableList<String> firstLine = data.remove(0);
		tableView.setItems(data);

		ObservableList<TableColumn<ObservableList<String>, ?>> columns = tableView.getColumns();

		for (int i = 0; i < columns.size(); i++) {
			final int curCol = i;
			final TableColumn<ObservableList<String>, String> column =(TableColumn<ObservableList<String>, String>) columns.get(i);
			column.setCellValueFactory(p -> new ReadOnlyObjectWrapper<>(p.getValue().get(curCol)));
			
//			String style = (i%2==0)?"-fx-background-color: #E8E9F1":"-fx-background-color: #DBBEF1";
//			column.setStyle(style);
			
		}

 
	}
}
