
package model;

import fxml.MainDocumentController;
import fxml.TabController;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.beans.Observable;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.scene.layout.AnchorPane;

public class DataModel {
	
	 ObservableList<Tab> tabList = FXCollections.observableArrayList(person -> 
        new Observable[] {});
	
	public void loadTab(TabPane tabs){
		
		tabList.parallelStream().forEach(t->tabs.getTabs().add(t));
//tabs.getTabs().add(new Tab("new tab"));
	}
	
	{
		
//		Tab tab;
//		try {
//			tab = new Tab("adfadsfadf");     
//			FXMLLoader tabLoader = new FXMLLoader(getClass().getResource("/fxml/tab.fxml"));
//		TabController tabController = tabLoader.getController(); 
//			tab.setContent(tabLoader.load());
//			tabList.add(tab);
//		} catch (IOException ex) {
//			Logger.getLogger(DataModel.class.getName()).log(Level.SEVERE, null, ex);
//		}
 		
		Tab tab = null;
		FXMLLoader tabLoader = new FXMLLoader(getClass().getResource("/fxml/mainDocument.fxml"));
		try {
			tab.setContent(tabLoader.load());
		} catch (IOException ex) {}
        MainDocumentController mainDocumentController = tabLoader.getController();
		  tabList.add(tab);
		
//		FXMLLoader loader = new FXMLLoader();
//		loader.setLocation(getClass().getResource("/fxml/tab.fxml"));
//		
//		loader.setController(new TabController());
//		try {
//			Tab tab = new Tab("asdf");
//			AnchorPane parent = loader.load();
//			tab.setContent(parent);
//			tabList.add(tab);
//		} catch (IOException e) {
//			e.printStackTrace();
//		}
	

//		Tab tab = new Tab("new tab attempt 3");
//		tab.setContent(new  Button("adsasdf"));
//		tabList.add(tab);
				  
				  
	}

}
