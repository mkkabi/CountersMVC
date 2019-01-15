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
import model.Household;

public class MainDocumentController {

	@FXML
	private MenuItem addHouseMenuItem;
	@FXML
	ImageView closeAddHousePopUp;
	@FXML
	private Button saveHousehold;
	@FXML
	private AnchorPane addHousePopUp;
	@FXML
	private TextField newHouseholdName;
	@FXML
	private TabPane tabs;

	private DataModel model;

	public void initModel(DataModel model) {
		if (this.model != null) {
			System.out.println("Model is no NULL");
			throw new IllegalStateException("Model can only be initialized once");
		}

		this.model = model;
		model.loadTab(tabs);

		/*== Add Household popUP ==*/
 /*
		@Todo
		need to set popUp window layout dynamically
		 */
		addHousePopUp.setVisible(false);
		closeAddHousePopUp.setOnMouseClicked(t -> addHousePopUp.setVisible(false));


	}

	@FXML
	public void saveHousehold(){
		if (newHouseholdName.getText().length() > 2) {
				model.loadTab(tabs, newHouseholdName.getText());
				addHousePopUp.setVisible(false);
			} else {
				// info message pop up
				System.out.println("enter household name");
			}
	}
	@FXML
	public void openHouseholdPopUp() {
		addHousePopUp.setLayoutX(730 / 2 - addHousePopUp.getWidth() / 2);
		addHousePopUp.setLayoutY((460 / 2 - addHousePopUp.getHeight() / 2));
		addHousePopUp.setVisible(true);
	}
}
