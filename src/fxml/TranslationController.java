package fxml;

import java.util.Timer;
import java.util.TimerTask;
import javafx.animation.TranslateTransition;
import javafx.concurrent.Task;
import javafx.scene.Node;
import javafx.util.Duration;

public class TranslationController {

	private TranslateTransition tt;

	public TranslationController() {
		tt = new TranslateTransition();
	}

	public void translateObj(Node node) {
		tt = new TranslateTransition(Duration.millis(800), node);
//		tt.setFromX(node.getLayoutX());
//		tt.setToX(node.getLayoutX() + 40 + 150);

		System.out.println("Y layout for transition controller node " + node.getLayoutY());
		node.setVisible(true);
				node.setLayoutX((node.getParent().getScene().getWidth() - 200) / 2);

		tt.setFromY(node.getParent().getScene().getHeight());
		System.out.println("parent height " + node.getParent().getScene().getHeight());
//		tt.setToY(node.getParent().getScene().getHeight()/2);
		tt.setToY(node.getParent().getScene().getHeight()/2);
//		node.setLayoutY(150);
		System.out.println("Y layout for transition controller node " + node.getLayoutY());
		tt.setRate(1);
		tt.play();

		Timer timer = new Timer();
			timer.schedule(new TimerTask() {
				@Override
				public void run() {
					node.setVisible(false);
					timer.cancel();
				}
			}, 2500);
			
			
	}
}
