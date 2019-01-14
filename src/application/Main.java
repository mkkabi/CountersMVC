
package application;

import fxml.MainDocumentController;
import fxml.TabController;
import javafx.application.Application;
import static javafx.application.Application.launch;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import model.DataModel;

public class Main extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception {

        AnchorPane root = new AnchorPane();
		  
        FXMLLoader mainDocumentLoader = new FXMLLoader(getClass().getResource("/fxml/mainDocument.fxml"));
        root.getChildren().add(mainDocumentLoader.load());
        MainDocumentController mainDocumentController = mainDocumentLoader.getController();
		  
//FXMLLoader mainDocumentLoader = new FXMLLoader(getClass().getResource("/fxml/tab.fxml"));
//        root.getChildren().add(mainDocumentLoader.load());
//        TabController mainDocumentController = mainDocumentLoader.getController();
		  
 
        DataModel model = new DataModel();
        mainDocumentController.initModel(model);
 

        Scene scene = new Scene(root);
        primaryStage.setScene(scene);
        primaryStage.show();
    }
    
    // annoying Eclipse launch workaround:
    public static void main(String[] args) { launch(args); }
}