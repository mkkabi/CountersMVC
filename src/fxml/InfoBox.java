
package fxml;

import javafx.animation.TranslateTransition;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Screen;
import javafx.util.Duration;
import model.DataModel;

public class InfoBox extends AnchorPane{
	private static Label label;
	private DataModel model; 
	
	
	public InfoBox(Label label){
		//this.getChildren().add(label);
		this.label = label;
	}
	
 
	public void initModel(DataModel model){
		this.model = model;

	}
	
	public void setText(String message){
		label.setText(message);
//		dialogBoxTranslation.setRate(1);
//		dialogBoxTranslation.play();
	}
	
//	public void getLabel(){
//		
//	}
}
