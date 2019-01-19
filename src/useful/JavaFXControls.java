/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package useful;

//List of import statements needed to reference the controls
import javafx.application.Application;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.scene.Scene;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;
import javafx.scene.control.Label;
import javafx.scene.control.ComboBox;
import javafx.scene.control.ListView;
import javafx.collections.ObservableList;
import javafx.collections.FXCollections;
import javafx.scene.control.SelectionMode;

public class JavaFXControls extends Application {
//Main entry point into the JavaFX application

	@Override
	public void start(Stage primaryStage) {
//Use HBOX layout panes to space out the controls
//in a single row
		HBox comboBox = new HBox();
		HBox listBox = new HBox();
		HBox controlBox = new HBox();
//An Observable list to populate the ListView with items
		ObservableList countries = FXCollections.observableArrayList(
				"England", "Germany", "France", "Israel", "South Africa", "U.S.A.", "Australia");
		ListView list = new ListView(countries);
//Set the width of the ListView to be 100 pixels
		list.setPrefWidth(100);
//Allow multiple selections from the Listview
		list.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);
//Create a naming label to highlight the selected item from the ListView
		Label listLabel = new Label("Selected List Item: ");
//Create a label to hold the value of the selected item of the ListView
		final Label listSelection = new Label();
		listSelection.setPrefWidth(200);
//Set up a changelistener to listen for the items being selected in the ListView
		list.getSelectionModel().selectedItemProperty().addListener(
				new ChangeListener() {
					public void changed(ObservableValue ov, Object old_val, Object new_val) {
//Set the label with the selected item
						listSelection.setText(new_val.toString());
					}
				});
//Add the ListView and two labels to the HBOX layout pane
		listBox.getChildren().add(list);
		listBox.getChildren().add(listLabel);
		listBox.getChildren().add(listSelection);
//An Observable list to populate the ComboBOx with options
		ObservableList fruits = FXCollections.observableArrayList(
				"Apple", "Banana", "Pear", "Strawberry", "Peach", "Orange", "Plum",
				"Melon", "Cherry", "Blackberry", "Melon", "Cherry", "Blackberry");
		ComboBox fruit = new ComboBox(fruits);
//Set the dropdown list to 13 so all the options can be seen at one time
		fruit.setVisibleRowCount(13);
//Create a naming label to highlight the selected option from the ComboBOx
		Label comboLabel = new Label("Selected Combo Item: ");
//Create a label to hold the value of the selected option of the ComboBox
		final Label comboSelection = new Label();
		fruit.getSelectionModel().selectedItemProperty().addListener(
				new ChangeListener() {
					public void changed(ObservableValue ov, Object old_val, Object new_val) {
						//Set the label with the selected option
						comboSelection.setText(new_val.toString());
					}
				});
//Add the ComboBox and two labels to the HBOX layout pane
		comboBox.getChildren().add(fruit);
		comboBox.getChildren().add(comboLabel);
		comboBox.getChildren().add(comboSelection);
//Add the two HBOXes to another HBOX to space out the controls
		controlBox.getChildren().add(listBox);
		controlBox.getChildren().add(comboBox);
//Add the main HBOX layout pane to the scene
		Scene scene = new Scene(controlBox, 800, 250);
//Show the form
		primaryStage.setTitle("Hello World!");
		primaryStage.setScene(scene);
		primaryStage.show();
	}

	/**
	 * @param args the command line arguments
	 */
	public static void main(String[] args) {
		launch(args);
	}
}
