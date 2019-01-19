package application;

import fxml.InfoBox;
import fxml.MainDocumentController;
import javafx.application.Application;
import static javafx.application.Application.launch;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import model.DataModel;

public class Main extends Application {

	private AnchorPane root;
//jjjjjjj
	@Override
	public void start(Stage primaryStage) throws Exception {

		FXMLLoader mainDocumentLoader = new FXMLLoader();
		mainDocumentLoader.setLocation(getClass().getResource("/fxml/mainDocument.fxml"));
		root = mainDocumentLoader.load();
		MainDocumentController mainDocumentController = mainDocumentLoader.getController();

		Label label = new Label("Test Label");
		InfoBox infoBox = new InfoBox(label);
		infoBox.getChildren().add(label);
		infoBox.setPrefWidth(200);
		infoBox.setPrefHeight(40);
		infoBox.setId("infoBox");
		

		infoBox.setStyle("-fx-background-color: #eeeeee;");

		System.out.println(primaryStage.getWidth());
//		infoBox.setLayoutY(();

infoBox.setVisible(false);
		root.getChildren().addAll(infoBox);

//				FXMLLoader loader = new FXMLLoader();
//		loader.setLocation(getClass().getResource("/fxml/mainDocument.fxml"));
//		MainDocumentController mainDocumentController = new MainDocumentController();
//		loader.setController(mainDocumentController);
//		try {
//			root = loader.load();
//		} catch (IOException e) {
//			e.printStackTrace();
//		}
		DataModel model = new DataModel();
		mainDocumentController.initModel(model);
//		infoBox.initModel(model);
		model.setInfoBox(infoBox);

		Scene scene = new Scene(root);
		primaryStage.setScene(scene);
		primaryStage.show();

 	}

	// annoying Eclipse launch workaround:
	public static void main(String[] args) {
		launch(args);
	}
}
