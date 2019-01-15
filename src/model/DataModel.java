package model;

import fxml.MainDocumentController;
import fxml.TabController;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;
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

	private static Set<Household> households = new LinkedHashSet();
	private static Counter currentCounter;

	public void loadTab(TabPane tabs) {
//		Tab tab;
//		for (int i = 0; i < 3; i++) {
//			FXMLLoader tabLoader = new FXMLLoader(getClass().getResource("/fxml/tab.fxml"));
//			try {
//				tab = new Tab("" + i);
//				tab.setContent(tabLoader.load());
//				tabs.getTabs().add(tab);
//				TabController mainDocumentController = tabLoader.getController();
//			} catch (IOException ex) {
//				System.out.println(ex.toString());
//			}
//		}
	}

	public void loadTab(TabPane tabs, String s) {
		Tab tab;
		application.Serializer<Household> ser = new application.Serializer();
		households.add(new Household(s));
		ser.saveObjects(model.Household.SAVE_FILE, households);
		FXMLLoader tabLoader = new FXMLLoader(getClass().getResource("/fxml/tab.fxml"));
		try {
			tab = new Tab(s);
			tab.setContent(tabLoader.load());
			tabs.getTabs().add(tab);
			TabController mainDocumentController = tabLoader.getController();
		} catch (IOException ex) {
			System.out.println(ex.toString());
		}
	}

	public void createTabDynamically(TabPane tabs) {
		application.Serializer ser = new application.Serializer<>();

//		try {
//			ser.restoreObjects("houses.ser", Household.households, t -> addTab(tabs, (Household) t));
//		} catch (FileNotFoundException ex) {
//			System.out.println(ex.toString());
//		}
	}
}
