package fxml;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Consumer;
import javafx.beans.binding.Bindings;
import javafx.beans.property.StringProperty;
import javafx.collections.FXCollections;
import javafx.scene.control.ContextMenu;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.control.MenuItem;

public class _notUsing_ListViewContMenu extends ListView {

	public <T> void initListView(ListView<T> listView, List<T> counters, Consumer c) {
//		listView.getItems().addAll(counters);
//listView.setItems(FXCollections.observableList(counters));

		listView.setCellFactory(lv -> {
			ListCell<T> cell = new ListCell<>();
			ContextMenu contextMenu = new ContextMenu();

			MenuItem editItem = new MenuItem();
			editItem.textProperty().bind(Bindings.format("Edit \"%s\"", cell.itemProperty()));
			editItem.setOnAction(event -> {
				//	String item = cell.getItem();
				// code to edit item...
			});
			MenuItem deleteItem = new MenuItem();
			deleteItem.textProperty().bind(Bindings.format("Delete \"%s\"", cell.itemProperty()));
			deleteItem.setOnAction(event -> {
				System.out.println("removed counter "+ counters.remove(cell.getItem()));
				listView.getItems().remove(cell.getItem());
				listView.refresh();
				c.accept(cell.getItem().toString());
				
			});
			contextMenu.getItems().addAll(editItem, deleteItem);

//			cell.textProperty().set(cell.itemProperty().toString());
//			cell.textProperty().bind(cell.itemProperty());
//			cell.textProperty().bind(cell.getItem());
			cell.emptyProperty().addListener((obs, wasEmpty, isNowEmpty) -> {
				if (isNowEmpty) {
					cell.setContextMenu(null);
					cell.setText(null);
				} else {
					cell.setContextMenu(contextMenu);
					cell.setText(cell.getItem().toString());
				}
			});
			return cell;
		});
	}
}
