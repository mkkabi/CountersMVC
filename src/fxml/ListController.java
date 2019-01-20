package fxml;

import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;
import java.util.function.Consumer;
import java.util.function.Predicate;
import javafx.beans.binding.Bindings;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.Initializable;
import javafx.scene.control.ContextMenu;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.control.MenuItem;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.util.Callback;
import model.DataModel;
import model.ElectricityCounter;
import model.GasCounter;
import model.WaterCounter;

public class ListController<T> {

	private final Image IMAGE_WATER = new Image("/resources/images/water.png");
	private final Image IMAGE_GAS = new Image("/resources/images/gas.png");
	private final Image IMAGE_ELECTRICITY = new Image("/resources/images/electricity.png");

	private ObservableList itemsObservableList;
	private final ListView listView;
	private ArrayList itemsArrayList;
	private final DataModel model;
	private ImageView imageView;

	public ListController(ListView c, ArrayList arr, DataModel dm) {
		model = dm;
		itemsObservableList = FXCollections.observableArrayList();
		itemsArrayList = arr;
		listView = c;
	}

	public void initList() {
		itemsObservableList.addAll(itemsArrayList);
		listView.setItems(itemsObservableList);

		listView.setCellFactory(listView -> new ListCell<T>() {
			private ImageView imageView = new ImageView();

			@Override
			public void updateItem(T item, boolean empty) {
				super.updateItem(item, empty);
				imageView.setFitHeight(30.0);
				imageView.setFitWidth(30.0);
				ListCell<T> cell = new ListCell<>();
				ContextMenu contextMenu = new ContextMenu();
				MenuItem deleteItem = new MenuItem();
				deleteItem.textProperty().bind(Bindings.format("Delete \"%s\"", item));
				deleteItem.setOnAction(event -> {
					removeItem(item);
					System.out.println("removed from list " + item.toString());
				});
				contextMenu.getItems().addAll(deleteItem);

				if (empty) {
					setContextMenu(null);
					setText(null);
					setGraphic(null);
				} else {
					if (item instanceof WaterCounter)
						imageView.setImage(IMAGE_WATER);
					if (item instanceof GasCounter)
						imageView.setImage(IMAGE_GAS);
					if(item instanceof ElectricityCounter)
						imageView.setImage(IMAGE_ELECTRICITY);
					
					setContextMenu(contextMenu);

					imageView.setId("counterImage");
					setText(item.toString());
					setGraphic(imageView);
				}
			}
		});
	}

	public void removeItem(T t) {
		itemsObservableList.remove(t);
		itemsArrayList.remove(t);
		listView.refresh();
		model.saveCurrentState();
		model.showInfoMessage("removed from list " + t.toString());
	}

	public void addNewItem(T t) {
		itemsArrayList.add(t);
		itemsObservableList.add(t);
		listView.refresh();
		model.saveCurrentState();
	}

	public void setSelectionModel(Consumer c) {
		listView.getSelectionModel().selectedItemProperty().addListener((ov, old_val, new_val) -> {
			c.accept(new_val);
		});
	}
}
