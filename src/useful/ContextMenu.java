
package useful;

public class ContextMenu {

			// Context menu for List View
		/*
		final ContextMenu countersListContextMenu = new ContextMenu();
		MenuItem deleteCounterMenuItem = new MenuItem("Delete");
		MenuItem editCounterMenuItem = new MenuItem("Edit");
		deleteCounterMenuItem.setOnAction(t -> deleteHousehodCounter());
		editCounterMenuItem.setOnAction(t -> {System.out.println(t.getSource().toString());});
		countersListContextMenu.getItems().add(deleteCounterMenuItem);
		countersListContextMenu.getItems().add(editCounterMenuItem);

		householdCounters.addEventHandler(MouseEvent.MOUSE_CLICKED, new EventHandler<MouseEvent>() {
			@Override
			public void handle(MouseEvent event) {
				if (event.getButton().equals(MouseButton.SECONDARY)) {
					countersListContextMenu.show(householdCounters, event.getScreenX(), event.getScreenY());
				}
			}
		});
*/
	
	
		/*
//	Context MENU for List and ets
	
	private void initRandomCardListView() {
    populateRandomList();
    final ContextMenu randomListContextMenu = new ContextMenu();
    MenuItem replaceCardMenuItem = new MenuItem("Replace");
    replaceCardMenuItem.setOnAction(new EventHandler<ActionEvent>() {
        @Override
        public void handle(ActionEvent event) {
            replaceRandomCard();
        }
    });
    randomListContextMenu.getItems().add(replaceCardMenuItem);

    randomCardList.addEventHandler(MouseEvent.MOUSE_CLICKED, new EventHandler<MouseEvent>() {
        @Override
        public void handle(MouseEvent event) {
            if (event.getButton().equals(MouseButton.SECONDARY)) {
                randomListContextMenu.show(randomCardList, event.getScreenX(), event.getScreenY());
            }
        }
    });
}

private void replaceRandomCard() {
    System.out.println("jobs done");
    System.out.println("card selected: " + randomCardList.selectionModelProperty().get().getSelectedItem().toString());
    System.out.println("card index: " + randomCardList.getSelectionModel().getSelectedIndex());
    System.out.println("card index: " + randomCardList.getSelectionModel().getSelectedItem().toString());
}
	
	 */
}
