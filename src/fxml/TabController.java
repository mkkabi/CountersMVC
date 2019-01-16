package fxml;

import java.net.URL;
import java.util.Optional;
import java.util.ResourceBundle;
import javafx.animation.TranslateTransition;
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
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.Tab;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import javafx.util.Duration;

import model.DataModel;
import model.Household;
import model.WaterCounter;
import model.Counter;


public class TabController extends Tab implements Initializable {

	@FXML
	ListView householdCounters;
	@FXML
	ComboBox counterTypes;
	@FXML
	private AnchorPane tabAnchorPane, addCounterPane, counterDataPane;
	@FXML
	Button calculateButton;
	@FXML
	Label tabName;
	@FXML
	VBox contextMenu;
	private ObservableList<Counter> counters;
	private TranslateTransition tabContextMenuTranslation;
	private final Household house;

	public TabController(Household h) {
		house = h;
		counters = FXCollections.observableArrayList();
		counters.addAll(house.getCounters());	
	}
	private DataModel model;

	public void initModel(DataModel model) {
		if (this.model != null) {
			// throw new IllegalStateException("Model can only be initialized once");
			System.out.println("Model is no NULL");
		}
		this.model = model;

	}

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		tabName.setText(house.getName());

		tabContextMenuTranslation = new TranslateTransition(Duration.millis(500), contextMenu);
		tabContextMenuTranslation.setFromX(contextMenu.getLayoutX());
		tabContextMenuTranslation.setToX(contextMenu.getLayoutX() + contextMenu.getPrefWidth());
		tabContextMenuTranslation.setFromY(contextMenu.getLayoutY());
		tabContextMenuTranslation.setToY(contextMenu.getLayoutY() - contextMenu.getPrefHeight());
		
		householdCounters.setItems(counters);
		householdCounters.getSelectionModel().selectedItemProperty().addListener(
				  (ObservableValue observable, Object oldValue, Object newValue) -> {
					  WaterCounter currentCounter = (WaterCounter) newValue;
//					  Household.setCurrentCounter(currentCounter);
//					  counterData.setText(currentCounter.text);
				  });

		counterTypes.getItems().addAll(
				"Water Counter",
				"Gas Counter",
				"Electricity Counter"
		);

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
	public void openCounterEditPopUp() {
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
	private void setCounterDataPaneVisible() {
		counterDataPane.setVisible(true);
	}

	@FXML
	private void setAddCounterPaneVisible() {
		addCounterPane.setVisible(true);
	}

	@FXML
	TextField newCounterName, newCounterRate;

	@FXML
	private void addNewCounter() {
		try {
			String[] errors = new String[3];
			String name = newCounterName.getText();
			Double rate = Double.parseDouble(newCounterRate.getText());
			String counterType = "";
			try {
				counterType = counterTypes.getSelectionModel().getSelectedItem().toString();
			} catch (Exception e) {
				System.out.println(e);
			}

			System.out.println(counterTypes.getSelectionModel().getSelectedItem().toString());
//		name = (newCounterName.getText()==null)?"NA":"enter Name";

			errors[0] = (name.length() > 3) ? null : "enter name";
			errors[1] = (rate > 0) ? null : "enter rate, only numbers allowed";
			errors[2] = (counterType.length() > 4) ? null : "choose counter Type";

			if (errors[0] == null && errors[1] == null && errors[2] == null) {
				switch (counterType) {
					case "Water Counter":
						System.out.println("creating water counter");
						WaterCounter waterC = new WaterCounter(name);
						waterC.setRate(rate);
						house.addCounter(waterC);
						model.saveCurrentState();
						break;
					case "Gas Counter":
						System.out.println("creating Gas counter");
						break;
					case "Electricity Counter":
						System.out.println("creating Electricity counter");
						break;
				}
			} else {
				for (String s : errors) {
					System.out.println(s);
				}
			}
		} catch (Exception ee) {
			System.out.println(ee.toString());
		}
	}

}
