package com.billdiary.ui;



///import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import com.billdiary.config.SpringFxmlLoader;

import com.billdiary.model.User1;
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
	
	public static BorderPane root=new BorderPane();
	public static StackPane MainStage= new StackPane();
	
	@Autowired
	public LayoutController layoutController;
	
	
	public static BorderPane getRoot() {
		return root;
	}

	public void setRoot(BorderPane root) {
		this.root = root;
	}
//	final static Logger LOGGER = Logger.getLogger(LoginController.class);
	
	// Reference to the main application
    @SuppressWarnings("unused")
    private MainController mainController;
	
	
	public void setMainController(MainController mainController) {
		this.mainController = mainController;
	}

	@Autowired
	private LoginService loginService;
	
	
	
	@Autowired
	private User1 user;
	
	@FXML private Text actiontarget;
	@FXML private TextField textField;
	@FXML private PasswordField passwordField;
	
	
	
	public LoginController()
	{
		
	}
    
    @FXML protected void handleSignInButtonAction(ActionEvent event) {
    	
    //	LOGGER.debug("In method LoginController:handleSignInButtonAction Entry ");
    	String userName=textField.getText();
    	String password=passwordField.getText();
    	System.out.println("dffsdfds");
    	if(userName!=null && password!=null)
    	{
    		System.out.println("dfdsfd");
    		user.setUserName(userName);
    		user.setPassword(password);
    		if(loginService.doLogin(user))
    		{
    			actiontarget.setText("Login Successfull");
    			((Node)(event.getSource())).getScene().getWindow().hide();
    			SpringFxmlLoader loader=SpringFxmlLoader.getInstance();
    			MainStage= (StackPane) loader.load(URLS.HOME_PAGE);
    			
    			layoutController.loadWindow(MainStage, Constants.APPLICATION_TITLE,Constants.WINDOW_WIDTH,Constants.WINDOW_HEIGHT);
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
