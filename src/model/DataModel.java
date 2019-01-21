package model;

import fxml.InfoBox;
import fxml.TabController;
import fxml.TranslationController;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.LinkedHashSet;
import java.util.Set;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.scene.layout.AnchorPane;

public class DataModel {

	private static final Set<Household> households = new LinkedHashSet();
	application.Serializer<Household> ser = new application.Serializer();
	InfoBox infoBox;

	public void addTab(TabPane tabPane, String s) {
		Household h = new Household(s);
		households.add(h);
		application.NIO.createDir(s);
		ser.saveObjects(model.Household.SAVE_FILE, households);
		loadTab(tabPane, h);
	}

	//		loader.setController(tabController);
	//		try {
//			tabController = loader.load();
//			householdTab.setContent(tabController);
//			tabPane.getTabs().add(householdTab);
//		} catch (IOException e) {
//			System.out.println(e.toString()
//		}
//	}
	public void loadTab(TabPane tabPane, Household hh) {
		FXMLLoader loader = new FXMLLoader();
		loader.setLocation(getClass().getResource("/fxml/tab.fxml"));
		TabController tabController = new TabController(hh);
		tabController.initModel(this);
		loader.setController(tabController);
		tabController.setText(hh.getName());
		try {
			tabController.setContent(loader.load());
			tabPane.getTabs().add(tabController);
		} catch (IOException e) {
			System.out.println(e.toString());
		}
	}

	public void restoreTabFromSave(TabPane tabPane) {
		try {
			ser.restoreObjects(Household.SAVE_FILE, households, t -> loadTab(tabPane, (Household) t));
		} catch (FileNotFoundException f) {
			System.out.println(f.toString());
		}
	}

	public void removeHousehold(Household house) {
		households.remove(house);
		ser.saveObjects(model.Household.SAVE_FILE, households);
	}

	public void saveCurrentState() {
		ser.saveObjects(model.Household.SAVE_FILE, households);
	}

//	public void loadTab(TabPane tabPane, Household h) {
//
//		FXMLLoader loader = new FXMLLoader();
//		loader.setLocation(getClass().getResource("/fxml/tab.fxml"));
//		TabController tabController = new TabController(h);
//		loader.setController(tabController);
//		Tab householdTab = new Tab(h.getName());
//		try {
//			tabController = loader.load();
//			householdTab.setContent(tabController);
//			tabPane.getTabs().add(householdTab);
//		} catch (IOException e) {
//			e.printStackTrace();
//		}
//	}
	public void writeCalculationToFile() {

	}

	public void setInfoBox(InfoBox infoBox) {
		this.infoBox = infoBox;
	}

	public void showInfoMessage(String message) {
		infoBox.setText(message);
//		tc.translateObjBottomUp(infoBox);
		TranslationController.translateObjTopDown(infoBox);
	}
	
	public static void saveCalculation(String uri, String text){
		
		application.NIO.appendLine(uri, text);
	}
}
