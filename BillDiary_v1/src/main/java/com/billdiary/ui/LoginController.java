package com.billdiary.ui;



///import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import com.billdiary.config.SpringFxmlLoader;

import com.billdiary.model.User;
import com.billdiary.screenResolution.ScreenController;
import com.billdiary.service.LoginService;
import com.billdiary.utility.Constants;
import com.billdiary.utility.URLS;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.StackPane;
import javafx.scene.text.Text;
import javafx.stage.Stage;


@Controller("LoginController")
public class LoginController {
	
	public static StackPane MainStage= new StackPane();
	
	@Autowired
	public LayoutController layoutController;
	
	@Autowired
	private LoginService loginService;

	@Autowired
	private User user;
	
	

    /*@SuppressWarnings("unused")
    private MainController mainController;
	
	
	public void setMainController(MainController mainController) {
		this.mainController = mainController;
	}*/

	@FXML private Text actiontarget;
	@FXML private TextField textField;
	@FXML private PasswordField passwordField;

    
    @FXML protected void handleSignInButtonAction(ActionEvent event) {
    	String userName=textField.getText();
    	String password=passwordField.getText();
    	System.out.println("dffsdfds");
    	if(userName!=null && password!=null)
    	{
    		user.setUserName(userName);
    		user.setPassword(password);
    		if(loginService.doLogin(user))
    		{
    			actiontarget.setText("Login Successfull");
    			((Node)(event.getSource())).getScene().getWindow().hide();
    			SpringFxmlLoader loader=SpringFxmlLoader.getInstance();
    			MainStage= (StackPane) loader.load(URLS.HOME_PAGE);
    			layoutController.loadWindow(MainStage, Constants.APPLICATION_TITLE,ScreenController.getScreenWidth(), ScreenController.getScreenHeight());
    		}
    		else {
    			actiontarget.setText("Login failed");
    		}	
    	}else
    	{
    		actiontarget.setText("UserName & Password cannot be valid");
    	}
    	//LOGGER.debug("In method LoginController:handleSignInButtonAction Exit ");     
    }
 
	
    
	
}
