package com.billdiary.ui;

import org.apache.log4j.Logger;

import com.billdiary.config.SpringFxmlLoader;
import com.billdiary.screenResolution.ScreenController;
import com.billdiary.utility.Constants;
import com.billdiary.utility.URLS;

import javafx.animation.FadeTransition;
import javafx.application.Application;
import javafx.beans.property.ReadOnlyObjectProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.concurrent.Task;
import javafx.concurrent.Worker;
import javafx.geometry.Pos;
import javafx.geometry.Rectangle2D;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.ProgressBar;
import javafx.scene.effect.DropShadow;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.stage.Screen;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javafx.util.Duration;

public class MainController extends Application{
	
	
	final static Logger LOGGER = Logger.getLogger(MainController.class);
	private static SpringFxmlLoader loader;
	private Pane splashLayout;
    private ProgressBar loadProgress;
    private Label progressText;
    private Stage mainStage;
    private static final int SPLASH_WIDTH = 720;
    private static final int SPLASH_HEIGHT = 227;
    public static final String SPLASH_IMAGE ="";

    /**
     * main Method thats starts the application
     * @param args
     */
    public static void main(String[] args) {
		LOGGER.debug("Entering Class MainController : method : main.");		
		launch(args); 
	}
    
    /**
     * Overridden init method
     */
    @Override
    public void init() {
        ImageView splash = new ImageView(new Image(
                getClass().getResourceAsStream("/icon/BillDiary.png")
        ));
        loadProgress = new ProgressBar();
        loadProgress.setPrefWidth(SPLASH_WIDTH - 20);
        progressText = new Label("Will find friends for peanuts . . .");
        splashLayout = new VBox();
        splashLayout.getChildren().addAll(splash, loadProgress, progressText);
        progressText.setAlignment(Pos.CENTER);
        splashLayout.setStyle(
                "-fx-padding: 5; " +
                "-fx-background-color: cornsilk; " +
                "-fx-border-width:5; " +
                "-fx-border-color: " +
                    "linear-gradient(" +
                        "to bottom, " +
                        "chocolate, " +
                        "derive(chocolate, 50%)" +
                    ");"
        );
        splashLayout.setEffect(new DropShadow());
    }

	@Override
	public void start(Stage stage) throws Exception {
		final Task<ObservableList<String>> splashfriendTask = new Task<ObservableList<String>>() {
            @Override
            protected ObservableList<String> call() throws InterruptedException {
                ObservableList<String> foundFriends =
                        FXCollections.<String>observableArrayList();
                updateMessage("Initializing.");
                for(int i = 0; i < 4; i++) {
                	Thread.sleep(4);
                	updateProgress(i + 1, 10);
                	foundFriends.add("A");
                	 loader=SpringFxmlLoader.getInstance();
                	updateMessage("Initializing..");
                }
                updateMessage("Initializing...");
                Thread.sleep(4);
                updateMessage("All friends found.");
                return foundFriends;
            }
         };
		
         showSplashScreen(stage,splashfriendTask,() -> showMainStage(splashfriendTask.valueProperty()));
         new Thread(splashfriendTask).start();
		//showMainStage();
	}
	
	private void showSplashScreen(final Stage initStage,Task<?> task,InitCompletionHandler initCompletionHandler) {
		progressText.textProperty().bind(task.messageProperty());
        loadProgress.progressProperty().bind(task.progressProperty());
        task.stateProperty().addListener((observableValue, oldState, newState) -> {
            if (newState == Worker.State.SUCCEEDED) {
                loadProgress.progressProperty().unbind();
                loadProgress.setProgress(1);
                initStage.toFront();
                FadeTransition fadeSplash = new FadeTransition(Duration.seconds(1.2), splashLayout);
                fadeSplash.setFromValue(1.0);
                fadeSplash.setToValue(0.0);
                fadeSplash.setOnFinished(actionEvent -> initStage.hide());
                fadeSplash.play();
                initCompletionHandler.complete();
            } 
        });

        Scene splashScene = new Scene(splashLayout, Color.TRANSPARENT);
        final Rectangle2D bounds = Screen.getPrimary().getBounds();
        initStage.setScene(splashScene);
        initStage.setX(bounds.getMinX() + bounds.getWidth() / 2 - SPLASH_WIDTH / 2);
        initStage.setY(bounds.getMinY() + bounds.getHeight() / 2 - SPLASH_HEIGHT / 2);
        initStage.initStyle(StageStyle.TRANSPARENT);
        initStage.setAlwaysOnTop(true);
        initStage.show();			
	}
	public interface InitCompletionHandler {
        void complete();
    }
	/**
	 * Main method thatopens login page
	 * @param friends
	 */
	private void showMainStage(ReadOnlyObjectProperty<ObservableList<String>> friends) {
		Parent root = (Parent) loader.load(URLS.LOGIN_PAGE);
		mainStage = new Stage(StageStyle.DECORATED);
		Scene scene = new Scene(root,ScreenController.getScreenWidth(), ScreenController.getScreenHeight()); 
		mainStage.setTitle(Constants.APPLICATION_TITLE);
		mainStage.setScene(scene);
		mainStage.show();	
	}
	
	
	
	
}
