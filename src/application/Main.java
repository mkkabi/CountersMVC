package application;

import fxml.MainDocumentController;
import fxml.TabController;
import java.io.IOException;
import javafx.application.Application;
import static javafx.application.Application.launch;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import model.DataModel;

public class Main extends Application {

	private AnchorPane root;

	@Override
	public void start(Stage primaryStage) throws Exception {

		FXMLLoader mainDocumentLoader = new FXMLLoader();
		mainDocumentLoader.setLocation(getClass().getResource("/fxml/mainDocument.fxml"));
		root = mainDocumentLoader.load();
		MainDocumentController mainDocumentController = mainDocumentLoader.getController();

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

		Scene scene = new Scene(root);
		primaryStage.setScene(scene);
		primaryStage.show();
	}

	// annoying Eclipse launch workaround:
	public static void main(String[] args) {
		launch(args);
	}
}
