package fxml;

import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.stream.Stream;
import javafx.application.Application;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableColumn.CellEditEvent;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.stage.Stage;

public class TableViewEditable extends Application {

	private TableView<CounterDataModel> table = new TableView<CounterDataModel>();

	private final ObservableList<CounterDataModel> data
			= FXCollections.observableArrayList(
			new CounterDataModel("12.01.2018","120","10","110","1.25","250")
			);

	final HBox hb = new HBox();

	public static void main(String[] args) {
		launch(args);
	}

	@Override
	public void start(Stage stage) {
		
		
		try (Stream<String> lines = Files.lines(Paths.get("file.csv"), Charset.defaultCharset())) {
			
			ArrayList<CounterDataModel> counterDataLines = new ArrayList();
			ObservableList<ArrayList<String>> obsList;
 			
		} catch (Exception e) {
			System.out.println(e.toString() + " from " + this.getClass());
		}
		
 
		Scene scene = new Scene(new Group());
		stage.setTitle("Table View Sample");
		stage.setWidth(450);
		stage.setHeight(550);

		final Label label = new Label("Address Book");
		label.setFont(new Font("Arial", 20));

		table.setEditable(true);

		TableColumn previousCol = new TableColumn("Previous");
		previousCol.setMinWidth(100);
		previousCol.setCellValueFactory(
				new PropertyValueFactory<ArrayList<ArrayList>, String>("previous"));
		previousCol.setCellFactory(TextFieldTableCell.forTableColumn());
		previousCol.setOnEditCommit(
				new EventHandler<CellEditEvent<CounterDataModel, String>>() {
					@Override
					public void handle(CellEditEvent<CounterDataModel, String> t) {
						((CounterDataModel) t.getTableView().getItems().get(
								t.getTablePosition().getRow())).setPrevious(t.getNewValue());
					}
				}
		);

		table.setItems(data);
		table.getColumns().addAll(previousCol);

//		final TextField addPrevious =  new TextField();
//		addPrevious.setPromptText("Previous");
//		addPrevious.setMaxWidth(previousCol.getPrefWidth());
//
//		final Button addButton = new Button("Add");
//		addButton.setOnAction(new EventHandler<ActionEvent>() {
//			@Override
//			public void handle(ActionEvent e) {
//				data.add(new CounterDataModel(Double.parseDouble(addPrevious.getText())));
//				addPrevious.clear();
//
//			}
//		});

//		hb.getChildren().addAll(addPrevious, addButton);
//		hb.setSpacing(3);

		final VBox vbox = new VBox();
		vbox.setSpacing(5);
		vbox.setPadding(new Insets(10, 0, 0, 10));
		vbox.getChildren().addAll(label, table, hb);

		((Group) scene.getRoot()).getChildren().addAll(vbox);

		stage.setScene(scene);
		stage.show();
	}

	public static class CounterDataModel {
	
	SimpleStringProperty date, previous, current, amount, rate, pay;

	public  CounterDataModel(String date, String previous, String current, String amount, String rate, String pay) {
		this.date = new SimpleStringProperty(date);
		this.previous = new SimpleStringProperty(previous);
		this.current = new SimpleStringProperty(current);
		this.amount = new SimpleStringProperty(amount);
		this.rate = new SimpleStringProperty(rate);
		this.pay = new SimpleStringProperty(pay);
	}

	public String getPrevious() {
		return previous.get();
	}
	
	public void setPrevious(String prev) {
			previous.set(prev);
		}
	
	public String getCurrent() {
		return current.get();
	}
	
	public void setCurrent(String cur) {
			current.set(cur);
		}
	
	public String getAmount() {
		return amount.get();
	}
	
	public void setAmount(String a) {
			amount.set(a);
		}
	
	public String getRate() {
		return rate.get();
	}
	
	public void setRate(String r) {
			previous.set(r);
		}
	
	public String getPay() {
		return pay.get();
	}
	
	public void setPay(String p) {
			pay.set(p);
		}
}
}
