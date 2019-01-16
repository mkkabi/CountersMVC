package fxml;

import java.net.URL;
import java.util.Optional;
import java.util.ResourceBundle;
import javafx.animation.TranslateTransition;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.control.Tab;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import javafx.util.Duration;

import model.DataModel;
import model.Household;

public class TabController extends Tab implements Initializable {

	@FXML
	private AnchorPane tabAnchorPane;
	@FXML
	Button calculateButton;
	@FXML
	Label tabName;
	@FXML
	VBox contextMenu;
	private TranslateTransition tabContextMenuTranslation;
	private final Household house;

	public TabController(Household h) {
		house = h;
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
		tabContextMenuTranslation.setToX(contextMenu.getLayoutX()+contextMenu.getPrefWidth());
 		tabContextMenuTranslation.setFromY(contextMenu.getLayoutY());
		tabContextMenuTranslation.setToY(contextMenu.getLayoutY()-contextMenu.getPrefHeight());
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
}
