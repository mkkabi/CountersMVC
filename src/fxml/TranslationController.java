package fxml;

import java.util.Timer;
import java.util.TimerTask;
import javafx.animation.TranslateTransition;
import javafx.concurrent.Task;
import javafx.scene.Node;
import javafx.util.Duration;

public class TranslationController {

	private static final int MESSAGE_DURATION = 2500;
	private static TranslateTransition tt;

	public TranslationController() {
		tt = new TranslateTransition();
	}

	public static void translateObjBottomUp(Node node) {
		tt = new TranslateTransition(Duration.millis(500), node);
		node.setLayoutX((node.getParent().getScene().getWidth() - 250) / 2);
		node.setVisible(true);
		tt.setFromY(node.getParent().getScene().getHeight());
		tt.setToY(node.getParent().getScene().getHeight() - (node.getParent().getScene().getHeight() / 4));
		tt.setRate(1);
		tt.play();

		Timer timer = new Timer();
		timer.schedule(new TimerTask() {
			@Override
			public void run() {
				node.setVisible(false);
				timer.cancel();
			}
		}, MESSAGE_DURATION);

	}
	
	public static void translateObjTopDown(Node node) {
		tt = new TranslateTransition(Duration.millis(500), node);
		node.setLayoutX((node.getParent().getScene().getWidth() - 250) / 2);
		node.setVisible(true);
		tt.setFromY(0);
		tt.setToY(node.getParent().getScene().getHeight() / 4);
		tt.setRate(1);
		tt.play();

		Timer timer = new Timer();
		timer.schedule(new TimerTask() {
			@Override
			public void run() {
				node.setVisible(false);
				timer.cancel();
			}
		}, MESSAGE_DURATION);
	}
}
