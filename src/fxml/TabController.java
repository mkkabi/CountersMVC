package fxml;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.Initializable;
import javafx.scene.layout.AnchorPane;

import model.DataModel;

public class TabController extends AnchorPane{

	private DataModel model;
	private String name;
	
	public void initModel(DataModel model) {
        if (this.model != null) {
           // throw new IllegalStateException("Model can only be initialized once");
			  System.out.println("Model is no NULL");
        }
        this.model = model;
    }
	
	public void setName(){
		this.name = "name name";
		System.out.println("name set");
	}

}
