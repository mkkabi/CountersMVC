package fxml;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TabPane;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import model.DataModel;

/**
 * FXML Controller class
 *
 * @author qwe
 */
public class MainDocumentController {

	@FXML
	private MenuItem newHousehold;
	@FXML
	private MenuItem about;
	@FXML
	private TabPane tabs;
	@FXML
	private AnchorPane addHousePopUp;
	@FXML
	private Button saveHousehold;
	@FXML
	private TextField newHouseholdName;
	@FXML
	private ImageView closeAddHousePopUp;
	@FXML
	private AnchorPane dialogBox;
	@FXML
	private Label infoLabel;

	private DataModel model;
	private String name;
	
	public void initModel(DataModel model) {
        if (this.model != null) {
           // throw new IllegalStateException("Model can only be initialized once");
			  System.out.println("Model is no NULL");
        }
        this.model = model;
		  
		  model.loadTab(tabs);
    }
	
	public void setName(){
		this.name = "name name";
		System.out.println("name set");
	}
}
