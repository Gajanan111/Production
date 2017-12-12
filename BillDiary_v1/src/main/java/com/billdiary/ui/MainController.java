package com.billdiary.ui;



import org.apache.log4j.Logger;

import com.billdiary.config.SpringFxmlLoader;
import com.billdiary.dao.CreateSchema;
import com.billdiary.utility.Constants;
import com.billdiary.utility.DAOConstants;
import com.billdiary.utility.URLS;

import javafx.application.Application;
import javafx.geometry.Rectangle2D;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Screen;
import javafx.stage.Stage;

public class MainController extends Application{
	
	final static Logger LOGGER = Logger.getLogger(MainController.class);
	private static final SpringFxmlLoader loader =SpringFxmlLoader.getInstance();
	
	
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		LOGGER.debug("Entering Class MainController : method : main.");		
		launch(args); 
		
	}

	@Override
	public void start(Stage stage) throws Exception {
		//Below fxml file will be loaded by SpringFxmlLoader 
		try {
			CreateSchema c=new CreateSchema();
			int i=c.executeNativeSQLQuery(DAOConstants.CREATE_USER_TABLE);
		//	System.out.println(i);

		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		Parent root = (Parent) loader.load(URLS.LOGIN_PAGE);
		//Parent root = FXMLLoader.load(getClass().getClassLoader().getResource("fxml/Login.fxml"));
		//Stage stage = new Stage();
		Scene scene = new Scene(root, 800, 600); 
        stage.setTitle(Constants.APPLICATION_TITLE);
        stage.setScene(scene);
        stage.show();	
       
	}
}
