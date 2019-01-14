package model;

import fxml.MainDocumentController;
import fxml.TabController;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
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

//	 ObservableList<Tab> tabList = FXCollections.observableArrayList(person -> 
//        new Observable[] {});
	List<Tab> tabList = new ArrayList();

	public void loadTab(TabPane tabs) {
		tabList.parallelStream().forEach(t -> tabs.getTabs().add(t));
	}

	
	{
		for (int i = 0; i < 3; i++) {
			FXMLLoader tabLoader = new FXMLLoader(getClass().getResource("/fxml/tab.fxml"));
			try {
				Tab tab = new Tab("tab name " + i);
				tab.setContent(tabLoader.load());
				tabList.add(tab);
				TabController mainDocumentController = tabLoader.getController();
			} catch (IOException ex) {
			}
		}
	}

	
}
