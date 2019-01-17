package fxml;

import java.net.URL;
import java.util.Objects;
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
import javafx.scene.control.ContextMenu;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.MenuItem;
import javafx.scene.control.Tab;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import javafx.util.Duration;

import model.DataModel;
import model.Household;
import model.WaterCounter;
import model.Counter;

public class TabController extends Tab implements Initializable {

	@FXML
	private TextArea counterData;
	@FXML
	private ListView householdCounters;
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

	public TabController(Household h) {
		house = h;
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
		tabName.setText(house.getName());

		tabContextMenuTranslation = new TranslateTransition(Duration.millis(500), contextMenu);
		tabContextMenuTranslation.setFromX(contextMenu.getLayoutX());
		tabContextMenuTranslation.setToX(contextMenu.getLayoutX() + contextMenu.getPrefWidth());
		tabContextMenuTranslation.setFromY(contextMenu.getLayoutY());
		tabContextMenuTranslation.setToY(contextMenu.getLayoutY() - contextMenu.getPrefHeight());

		householdCounters.setItems(FXCollections.observableList(house.getCounters()));
		householdCounters.getSelectionModel().selectedItemProperty().addListener(
				(ObservableValue observable, Object oldValue, Object newValue) -> {
					displaycounterDataPane((WaterCounter) newValue);
				});

		counterTypes.getItems().addAll(
				"Water Counter",
				"Gas Counter",
				"Electricity Counter"
		);
		
		addCounterButton.setOnMouseClicked(t->{
			openAddCounterPane();
			closeContextMenu();
		});
		
		
		final ContextMenu randomListContextMenu = new ContextMenu();
		 MenuItem replaceCardMenuItem = new MenuItem("Replace");
		 replaceCardMenuItem.setOnAction(t->System.out.println("menu clicked"));
    randomListContextMenu.getItems().add(replaceCardMenuItem);
		
		householdCounters.addEventHandler(MouseEvent.MOUSE_CLICKED, new EventHandler<MouseEvent>() {
        @Override
        public void handle(MouseEvent event) {
            if (event.getButton().equals(MouseButton.SECONDARY)) {
                randomListContextMenu.show(householdCounters, event.getScreenX(), event.getScreenY());
            }
        }
    });
		 
	}
	
 
    
	
	/*
//	Context MENU for List and ets
	
	private void initRandomCardListView() {
    populateRandomList();
    final ContextMenu randomListContextMenu = new ContextMenu();
    MenuItem replaceCardMenuItem = new MenuItem("Replace");
    replaceCardMenuItem.setOnAction(new EventHandler<ActionEvent>() {
        @Override
        public void handle(ActionEvent event) {
            replaceRandomCard();
        }
    });
    randomListContextMenu.getItems().add(replaceCardMenuItem);

    randomCardList.addEventHandler(MouseEvent.MOUSE_CLICKED, new EventHandler<MouseEvent>() {
        @Override
        public void handle(MouseEvent event) {
            if (event.getButton().equals(MouseButton.SECONDARY)) {
                randomListContextMenu.show(randomCardList, event.getScreenX(), event.getScreenY());
            }
        }
    });
}

private void replaceRandomCard() {
    System.out.println("jobs done");
    System.out.println("card selected: " + randomCardList.selectionModelProperty().get().getSelectedItem().toString());
    System.out.println("card index: " + randomCardList.getSelectionModel().getSelectedIndex());
    System.out.println("card index: " + randomCardList.getSelectionModel().getSelectedItem().toString());
}
	
	*/

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
	private void openAddCounterPane() {
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
		try {
			String name = Objects.requireNonNull(newCounterName.getText());
			Double rate = Objects.requireNonNull(Double.parseDouble(newCounterRate.getText()));
			String counterType = "";
			try {
				counterType = counterTypes.getSelectionModel().getSelectedItem().toString();
			} catch (Exception e) {
				System.out.println(e);
			}

			System.out.println(counterTypes.getSelectionModel().getSelectedItem().toString());

			if (true) {
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
					default:
						System.out.println("default");
				}
closeAddCounterPane();
			}

		} catch (Exception ee) {
			System.out.println(ee.toString());
		}
		householdCounters.setItems(FXCollections.observableList(house.getCounters()));
		householdCounters.refresh();
		
	}

	private <T extends Counter> void displaycounterDataPane(T c) {
//		setCounterDataPaneVisible();
		counterData.setText(c.toString());
		
	}
	
}
