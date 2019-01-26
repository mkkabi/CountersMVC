package fxml;

import java.util.ArrayList;
import java.util.Arrays;
import useful.*;
import javafx.application.Application;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableColumn.CellEditEvent;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.stage.Stage;

public class TableViewEditable extends Application {

	private TableView<Person> table = new TableView<Person>();
	
	private final ObservableList<Person> data
			  = FXCollections.observableArrayList(
						 new Person("Jacob"),
						 new Person("Isabella"),
						 new Person("Ethan"),
						 new Person("Emma"),
						 new Person("Michael"));
	
	private ArrayList<ArrayList<String>> list = new ArrayList();
	

	final HBox hb = new HBox();

	public static void main(String[] args) {
		launch(args);
	}

	@Override
	public void start(Stage stage) {
		list.add((ArrayList<String>) Arrays.asList(" ", "d", "3", "4"));
		list.add((ArrayList<String>) Arrays.asList(" ", "d", "3", "4"));
		list.add((ArrayList<String>) Arrays.asList(" ", "d", "3", "4"));
		
		Scene scene = new Scene(new Group());
		stage.setTitle("Table View Sample");
		stage.setWidth(450);
		stage.setHeight(550);

		final Label label = new Label("Address Book");
		label.setFont(new Font("Arial", 20));

		table.setEditable(true);

		TableColumn firstNameCol = new TableColumn("First Name");
		firstNameCol.setMinWidth(100);
		firstNameCol.setCellValueFactory(
				  new PropertyValueFactory<ArrayList<ArrayList>, String>("firstName"));
		firstNameCol.setCellFactory(TextFieldTableCell.forTableColumn());
		firstNameCol.setOnEditCommit(
				  new EventHandler<CellEditEvent<Person, String>>() {
			@Override
			public void handle(CellEditEvent<Person, String> t) {
				((Person) t.getTableView().getItems().get(
						  t.getTablePosition().getRow())).setFirstName(t.getNewValue());
			}
		}
		);

		table.setItems(data);
		table.getColumns().addAll(firstNameCol);

		final TextField addFirstName = new TextField();
		addFirstName.setPromptText("First Name");
		addFirstName.setMaxWidth(firstNameCol.getPrefWidth());

		final Button addButton = new Button("Add");
		addButton.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent e) {
				data.add(new Person(addFirstName.getText()));
				addFirstName.clear();

			}
		});

		hb.getChildren().addAll(addFirstName, addButton);
		hb.setSpacing(3);

		final VBox vbox = new VBox();
		vbox.setSpacing(5);
		vbox.setPadding(new Insets(10, 0, 0, 10));
		vbox.getChildren().addAll(label, table, hb);

		((Group) scene.getRoot()).getChildren().addAll(vbox);

		stage.setScene(scene);
		stage.show();
	}

	public static class Person {

		private final SimpleStringProperty firstName;

		private Person(String fName) {
			this.firstName = new SimpleStringProperty(fName);
		}

		public String getFirstName() {
			return firstName.get();
		}

		public void setFirstName(String fName) {
			firstName.set(fName);
		}

	}
}
