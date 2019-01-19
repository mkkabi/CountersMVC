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

public class ListController<T> {

	private ImageView imageView;

	private final Image IMAGE_RUBY = new Image("https://upload.wikimedia.org/wikipedia/commons/f/f1/Ruby_logo_64x64.png");
	private final Image IMAGE_APPLE = new Image("http://findicons.com/files/icons/832/social_and_web/64/apple.png");
	private final Image IMAGE_VISTA = new Image("http://antaki.ca/bloom/img/windows_64x64.png");
	private final Image IMAGE_TWITTER = new Image("http://files.softicons.com/download/social-media-icons/fresh-social-media-icons-by-creative-nerds/png/64x64/twitter-bird.png");
	private final Image IMAGE_WATER = new Image("/resources/images/edit.png");

	private Image[] listOfImages = {IMAGE_RUBY, IMAGE_APPLE, IMAGE_VISTA, IMAGE_TWITTER};

	private ObservableList itemsObservableList;
	private final ListView listView;
	private ArrayList itemsArrayList;
	private final DataModel model;

	public ListController(ListView c, ArrayList arr, DataModel dm) {
		model = dm;
		itemsObservableList = FXCollections.observableArrayList();
		itemsArrayList = arr;
		listView = c;
	}

	public void initList2() {
		listView.setCellFactory(listView -> new ListCell<T>() {
			private ImageView imageView = new ImageView();

			@Override
			public void updateItem(T friend, boolean empty) {
				super.updateItem(friend, empty);
				if (empty) {
					setText(null);
					setGraphic(null);
				} else {
					Image image = new Image("/resources/images/edit.png");
					imageView.setImage(image);
					setText(friend.toString());
					setGraphic(imageView);
				}
			}
		});
	}

	public void initList() {

		itemsObservableList.addAll(itemsArrayList);
		listView.setItems(itemsObservableList);
		listView.getSelectionModel().selectedItemProperty().addListener((ov, oldV, newV) -> {
			//counterData.setText(new_val.toString());
		});

		listView.setCellFactory(lv -> {
			imageView = new ImageView();
			imageView.setFitHeight(20.0);
			imageView.setFitWidth(20.0);

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
				removeItem(cell.getItem());
			});
			contextMenu.getItems().addAll(editItem, deleteItem);

			cell.emptyProperty().addListener((obs, wasEmpty, isNowEmpty) -> {
				if (isNowEmpty) {
					cell.setContextMenu(null);
					cell.setText(null);
				} else {
					cell.setContextMenu(contextMenu);
					cell.setText("      "+cell.getItem().toString());
					imageView.setImage(IMAGE_WATER);
					imageView.setId("counterImage");
					cell.setGraphic(imageView);
				}
			});
			return cell;
		});
	}

	public void removeItem(T t) {
		itemsObservableList.remove(t);
		itemsArrayList.remove(t);
		listView.refresh();
		model.saveCurrentState();
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
