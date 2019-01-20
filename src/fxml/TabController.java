package fxml;

import java.io.IOException;
import java.net.URL;
import java.util.Objects;
import java.util.Optional;
import java.util.ResourceBundle;
import java.util.function.Consumer;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.animation.TranslateTransition;
import javafx.beans.binding.Bindings;
import javafx.beans.property.ReadOnlyObjectWrapper;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.ContextMenu;
import javafx.scene.control.Label;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.control.MenuItem;
import javafx.scene.control.SelectionMode;
import javafx.scene.control.Tab;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.util.Duration;

import model.DataModel;
import model.Household;
import model.WaterCounter;
import model.Counter;
import model.ElectricityCounter;
import model.GasCounter;

public class TabController<T extends Counter> extends Tab implements Initializable {


	@FXML
	HBox tableHBox;
	@FXML
	private ListView countersList;
	@FXML
	private ComboBox counterTypes;
	@FXML
	private AnchorPane tabAnchorPane, addCounterPane, counterDataPane;
	@FXML
	private Button calculateButton, addCounterButton;
	@FXML
	private Label tabName;
	@FXML
	private VBox contextMenu;
	private TranslateTransition tabContextMenuTranslation;
	private final Household house;
	private Counter currentCounter;
	private ObservableList<Counter> countersObservable;
	private ListController<Counter> countersController;

	
	public TabController(Household h) {
		house = h;
		countersObservable = FXCollections.observableArrayList();
	}
	private DataModel model;

	public void initModel(DataModel model) {
		if (this.model != null) {
			throw new IllegalStateException("Model can only be initialized once");
		}
		this.model = model;
	}

	

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		
/////////////////////TABLEVIEW////////////////////////////////
		
		
		try {
			TableViewDynamic tvd = new TableViewDynamic();
			tableHBox.getChildren().add(tvd.createTableView());
		} catch (IOException ex) {System.out.println(ex.toString());
		
		}
				
		
//END////////////////////TABLEVIEW////////////////////////////////
		
		
		tabName.setText(house.getName());

		tabContextMenuTranslation = new TranslateTransition(Duration.millis(500), contextMenu);
		tabContextMenuTranslation.setFromX(contextMenu.getLayoutX());
		tabContextMenuTranslation.setToX(contextMenu.getLayoutX() + contextMenu.getPrefWidth());
		tabContextMenuTranslation.setFromY(contextMenu.getLayoutY());
		tabContextMenuTranslation.setToY(contextMenu.getLayoutY() - contextMenu.getPrefHeight());

		countersController = new ListController(countersList, house.getCounters(), model);
		countersController.initList();
		application.NIO nio = new application.NIO();
		countersController.setSelectionModel(t -> {Counter c = (Counter) t;});
		

		
		
		counterTypes.getItems().addAll(
				"Water Counter",
				"Gas Counter",
				"Electricity Counter"
		);

		addCounterButton.setOnMouseClicked(t -> {
			openAddCounterPane();
			closeContextMenu();
		});

	}
				

	@FXML
	public void deleteTab() {
		Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
		alert.setTitle("Confirmation");
		alert.setContentText("do you really want to delete Household " + house.getName() + "?");
		Optional<ButtonType> result = alert.showAndWait();
		if (result.get() == ButtonType.OK) {
			model.removeHousehold(house);

			closeTab();
			model.showInfoMessage("Household " + house.getName() + " removed");
		}
	}

	@FXML
	private void closeTab() {
		EventHandler<Event> handler = this.getOnClosed();
		if (null != handler) {
			handler.handle(null);
		} else {
			this.getTabPane().getTabs().remove(this);
		}
	}

	@FXML
	public void openEditCounterPopUp() {
		System.out.println(tabAnchorPane.getWidth());
		System.out.println(tabAnchorPane.getHeight());

//		addHousePopUp.setLayoutX(mainPane.getWidth() / 2 - addHousePopUp.getWidth() / 2);
//		addHousePopUp.setLayoutY((mainPane.getHeight() / 2 - addHousePopUp.getHeight() / 2));
//		addHousePopUp.setVisible(true);
	}

	@FXML
	private void openContextMenu() {
		tabContextMenuTranslation.setRate(1);
		tabContextMenuTranslation.play();
	}

	@FXML
	private void closeContextMenu() {
		tabContextMenuTranslation.setRate(-1);
		tabContextMenuTranslation.play();
	}

	@FXML
	private void openAddCounterPane() {
		addCounterPane.setLayoutX((tabAnchorPane.getWidth() - addCounterPane.getPrefWidth()) / 2);
		addCounterPane.setVisible(true);
	}

	@FXML
	private void closeAddCounterPane() {
		addCounterPane.setVisible(false);
	}

	@FXML
	TextField newCounterName, newCounterRate;

	@FXML
	private void addNewCounter() {
		Counter counter = null;
 
			String name = Objects.requireNonNull(newCounterName.getText());
			Double rate = Objects.requireNonNull(Double.parseDouble(newCounterRate.getText()));
			String counterType = Objects.requireNonNull(counterTypes.getSelectionModel().getSelectedItem().toString());

				switch (counterType) {
					case "Water Counter":
						System.out.println("creating water counter");
						counter = new WaterCounter(name);
						counter.setRate(rate);
						break;
					case "Gas Counter":
						System.out.println("creating Gas counter");
						counter = new GasCounter(name);
						counter.setRate(rate);
						break;
					case "Electricity Counter":
						System.out.println("creating Electricity counter");
						counter = new ElectricityCounter(name);
						counter.setRate(rate);
						break;
					default:
						model.showInfoMessage("Select counter type!");
				}
				countersController.addNewItem(counter);
				closeAddCounterPane();
				System.out.println("saved");
 
 

	}

	private <T extends Counter> void displaycounterDataPane(T c) {
//		counterData.setText(c.toString());
	}

	private void deleteHousehodCounter(T t) {
		house.getCounters().remove(t);
		countersObservable.remove(t);
		countersList.refresh();
		model.saveCurrentState();
	}

	@FXML
	private void showMessage() {
		model.showInfoMessage("Test message from tabController");
	}

}
